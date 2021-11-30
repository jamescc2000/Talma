package com.example.talma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.talma.Adapters.AdaptadorListaServicios;
import com.example.talma.Adapters.AdaptadorReclamo;
import com.example.talma.Dashboard_cliente;
import com.example.talma.Dashboard_empleados;
import com.example.talma.Modelos.ModeloReclamo;
import com.example.talma.Modelos.ModeloServicio;
import com.example.talma.R;
import com.example.talma.RsirEmpleados.RegistrarRsire;
import com.example.talma.RsirEmpleados.RevisarServicios;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.sql.Time;
import java.time.Clock;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RealizarReclam extends AppCompatActivity {

    private EditText et_codigo_rsir, et_motivo, et_codigo_servicio, et_nombre_servicio;
    private Button btn_enviar;

    TextView tv_hora_desde_llegada, tv_hora_hasta_llegada, tv_cantidad_llegada, tv_hora_desde_salida, tv_hora_hasta_salida, tv_cantidad_salida;

    ActionBar actionBar;
    private ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    DatabaseReference bd_servicio;


    String codigo_rsir, codigo_servicio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realizar_reclamo);
        actionBar = getSupportActionBar();
        actionBar.setTitle("Registro de Reclamo");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        tv_hora_desde_llegada = (TextView) findViewById(R.id.tv_hora_desde_llegada);
        tv_hora_hasta_llegada = (TextView) findViewById(R.id.tv_hora_hasta_llegada);
        tv_cantidad_llegada = (TextView) findViewById(R.id.tv_cantidad_llegada);
        tv_hora_desde_salida = (TextView) findViewById(R.id.tv_hora_desde_salida);
        tv_hora_hasta_salida = (TextView) findViewById(R.id.tv_hora_hasta_salida);
        tv_cantidad_salida = (TextView) findViewById(R.id.tv_cantidad_salida);
        et_codigo_rsir = (EditText) findViewById(R.id.et_codigo_rsir);
        et_codigo_servicio = (EditText) findViewById(R.id.et_codigo_servicio);
        et_nombre_servicio = (EditText) findViewById(R.id.et_nombre_servicio);
        et_motivo = (EditText) findViewById(R.id.et_motivo);
        btn_enviar = (Button) findViewById(R.id.btn_enviar);

        firebaseAuth = FirebaseAuth.getInstance();
        bd_servicio = FirebaseDatabase.getInstance().getReference("servicios");
        progressDialog = new ProgressDialog(RealizarReclam.this);

        Intent intent = getIntent();
        codigo_rsir = intent.getStringExtra("codigoRsir");
        codigo_servicio = intent.getStringExtra("codigoServicio");

        et_codigo_rsir.setText(codigo_rsir);
        et_codigo_servicio.setText(codigo_servicio);

        ObtenerServicios();

        btn_enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(et_motivo.getText().toString())){
                    Toast.makeText(RealizarReclam.this, "Por favor, primero complete todos los campos", Toast.LENGTH_SHORT).show();
                }
                else {
                    actualizarEstadoServicio();
                    RegistrarReclamo();
                }
            }
        });

    }

    private void RegistrarReclamo(){
        progressDialog.setTitle("Registrando");
        progressDialog.setMessage("Espere por favor...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        FirebaseUser user = firebaseAuth.getCurrentUser();
        //Aqui van los datos que queremos registrar
        assert user != null; //Afirmamos que el usuario no sea nulo

        String codigo_reclamo_string = "R_"+ codigo_servicio;
        String uid_string = user.getUid();
        String rsir_string = et_codigo_rsir.getText().toString();
        String fecha_string = obtnerFechaActual();
        String estado_string = "pendiente";
        String motivo_string = et_motivo.getText().toString();

        /*Creamos un Hashmap para mandar los datos a firebase*/
        Map<String, Object> datosReclamo = new HashMap<>();
        datosReclamo.put("codigoReclamo", codigo_reclamo_string);
        datosReclamo.put("uid", uid_string);
        datosReclamo.put("codigoRsir", rsir_string);
        datosReclamo.put("codigoServicio", codigo_servicio);
        datosReclamo.put("fechaRegistro", fecha_string);
        datosReclamo.put("tipoReclamo", "servicio");
        datosReclamo.put("estado", estado_string);
        datosReclamo.put("motivo", motivo_string);



        //Inicializamos la instancia a la base de datos
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference reference = database.getReference("reclamo"); // Este es el nombre de la tabla
        reference.child(codigo_reclamo_string).setValue(datosReclamo).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                progressDialog.dismiss(); // El progresss se cierra

                Toast.makeText(RealizarReclam.this, "Registro exitoso", Toast.LENGTH_SHORT).show();


                //Una vez registrado, volvemos
                Intent intent = new Intent(RealizarReclam.this, ValidarServicios.class);
                intent.putExtra("codigoRsir",codigo_rsir);
                startActivity(intent);
                finish();
               //prepararNotificacion(codigo_reclamo_string);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                progressDialog.dismiss(); // El progresss se cierra
                Toast.makeText(RealizarReclam.this, "Algo ha salido mal, vuelva a intentarlo", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void ObtenerServicios(){

        Query servicioQuery = bd_servicio.orderByChild("codigoServicio").equalTo(codigo_servicio).limitToFirst(1);

        servicioQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot ds: snapshot.getChildren()){

                    //Obtenemos los datos
                    String nombre_string = ""+ ds.child("nombre").getValue();
                    String hora_desde_llegada_string = ""+ ds.child("horaDesdeLlegada").getValue();
                    String hora_hasta_llegada_string = ""+ ds.child("horaHastaLlegada").getValue();
                    String cantidad_llegada_string = ""+ ds.child("cantidadLlegada").getValue();
                    String hora_desde_salida_string = ""+ ds.child("horaDesdeSalida").getValue();
                    String hora_hasta_salida_string = ""+ ds.child("horaHastaSalida").getValue();
                    String cantidad_salida_string = ""+ ds.child("cantidadSalida").getValue();

                    //Colocamos los datos
                    et_nombre_servicio.setText(nombre_string);
                    tv_hora_desde_llegada.setText(hora_desde_llegada_string);
                    tv_hora_hasta_llegada.setText(hora_hasta_llegada_string);
                    tv_cantidad_llegada.setText(cantidad_llegada_string);
                    tv_hora_desde_salida.setText(hora_desde_salida_string);
                    tv_hora_hasta_salida.setText(hora_hasta_salida_string);
                    tv_cantidad_salida.setText(cantidad_salida_string);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    private void actualizarEstadoServicio(){

        HashMap<String, Object> resultado = new HashMap<>();
        resultado.put("estado", "reclamado");

        bd_servicio.child(codigo_servicio).updateChildren(resultado)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(RealizarReclam.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


    }

    private String obtnerFechaActual(){

        Calendar cal = Calendar.getInstance();
        cal.getTimeZone();
        int ano = cal.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONTH);
        mes = mes + 1;
        int dia = cal.get(Calendar.DAY_OF_MONTH);

        return makeDateString(dia, mes, ano);
    }

    private void prepararNotificacion(String reclamo_id){
        //Cuando el cliente realize un reclamo, se enviará esta notificación
        String Topico = "/topics/GESTOR_RECLAMO";
        String n_titulo = "Nuevo reclamo "+reclamo_id;
        String tipo = "Reclamo";
        //preparando el json ("qué" enviar y a "quién")
        JSONObject notificacionjo = new JSONObject();
        JSONObject notificacionBodyjo = new JSONObject();
        try{
            notificacionBodyjo.put("tipo",tipo);
            notificacionBodyjo.put("clienteUid",FirebaseAuth.getInstance().getUid());
            notificacionBodyjo.put("empleadoUid","sadasdasd");
            notificacionBodyjo.put("n_titulo",n_titulo);
            notificacionBodyjo.put("reclamo_id",reclamo_id);


            notificacionjo.put("to",Topico);
            notificacionjo.put("data",reclamo_id);


        }
        catch (Exception e){
            Toast.makeText(this,""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        enviarNotificacion(notificacionjo, reclamo_id);
    }

    private void enviarNotificacion(JSONObject notificacionjo, String reclamo_id) {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("https://fcm.googleapis.com/fcm/send", notificacionjo, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-type", "application/json");
                headers.put("Authorization", "key=AAAAe6WsjPQ:APA91bFw_DIRzSmwHUk-PXF1gqptAyDKRfUVaYtM8vIv0Up6PqcGnurpF-cA7qho-F5-vD5GMiQ1NmlaUWl4c8ruuhHxwxzWHeRpixSSPLR5w1BYHOTWME0zLrzwhe97nCG31_InIbVD");

                return headers;
            }

        };

        Volley.newRequestQueue(this).add(jsonObjectRequest);
    }


    private String makeDateString(int dayOfMonth, int month, int year){
        return dayOfMonth + "/" + month + "/" + year;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}