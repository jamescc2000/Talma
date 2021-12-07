package com.example.talma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.talma.Adapters.AdaptadorListaServicios;
import com.example.talma.Modelos.ModeloServicio;
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidarServicios extends AppCompatActivity {

    RecyclerView rv_lista_servicios;
    AdaptadorListaServicios adaptadorListaServicios;
    ActionBar actionBar;
    private ProgressDialog progressDialog;

    TextView tv_codigo_rsir, tv_estado_rsir,tv_aeropuerto, tv_compañia, tv_origen, tv_destino, tv_aeronaves, tv_matricula, tv_area, tv_a_cargo_de;
    TextView tv_fecha_llegada, tv_hora_llegada, tv_nvuelo_llegada, tv_pea_llegada;
    TextView tv_fecha_salida, tv_hora_salida, tv_nvuelo_salida, tv_pea_salida;
    EditText et_motivo;
    Button btn_reclamar_datos_generales, btn_confirmar_pedido, btn_reclamar_datos_generales_gris;
    LinearLayout ly_reclamo;

    List<ModeloServicio> listaServicios = new ArrayList<>();

    FirebaseAuth firebaseAuth;
    FirebaseUser user;

    String codigo_rsir, estado_rsir;

    DatabaseReference bd_rsir;
    DatabaseReference bd_servicios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validar_servicios);

        actionBar = getSupportActionBar();
        actionBar.setTitle("Validar Servicios");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        tv_codigo_rsir = (TextView) findViewById(R.id.tv_codigo_rsir);
        tv_estado_rsir = (TextView) findViewById(R.id.tv_estado_rsir);
        tv_aeropuerto = (TextView) findViewById(R.id.tv_aeropuerto);
        tv_compañia = (TextView) findViewById(R.id.tv_compañia);
        tv_origen = (TextView) findViewById(R.id.tv_origen);
        tv_destino = (TextView) findViewById(R.id.tv_destino);
        tv_aeronaves = (TextView) findViewById(R.id.tv_aeronaves);
        tv_matricula = (TextView) findViewById(R.id.tv_matricula);
        tv_area = (TextView) findViewById(R.id.tv_area);
        tv_a_cargo_de = (TextView) findViewById(R.id.tv_a_cargo_de);
        tv_fecha_llegada = (TextView) findViewById(R.id.tv_fecha_llegada);
        tv_hora_llegada = (TextView) findViewById(R.id.tv_hora_llegada);
        tv_nvuelo_llegada = (TextView) findViewById(R.id.tv_nvuelo_llegada);
        tv_pea_llegada = (TextView) findViewById(R.id.tv_pea_llegada);
        tv_fecha_salida = (TextView) findViewById(R.id.tv_fecha_salida);
        tv_hora_salida = (TextView) findViewById(R.id.tv_hora_salida);
        tv_nvuelo_salida = (TextView) findViewById(R.id.tv_nvuelo_salida);
        tv_pea_salida = (TextView) findViewById(R.id.tv_pea_salida);
        et_motivo = (EditText) findViewById(R.id.et_motivo);
        btn_reclamar_datos_generales = (Button) findViewById(R.id.btn_reclamar_datos_generales);
        btn_reclamar_datos_generales_gris = (Button) findViewById(R.id.btn_reclamar_datos_generales_gris);
        btn_confirmar_pedido = (Button) findViewById(R.id.btn_confirmar_pedido);
        ly_reclamo = (LinearLayout) findViewById(R.id.ly_reclamo);
        rv_lista_servicios = findViewById(R.id.rv_lista_servicios);

        rv_lista_servicios.setHasFixedSize(true);
        rv_lista_servicios.setLayoutManager(new LinearLayoutManager(ValidarServicios.this));

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        progressDialog = new ProgressDialog(ValidarServicios.this);

        Intent intent = getIntent();
        codigo_rsir = intent.getStringExtra("codigoRsir");

        bd_servicios = FirebaseDatabase.getInstance().getReference("servicios");
        bd_rsir = FirebaseDatabase.getInstance().getReference("rsir");

        Query rsirQuery = bd_rsir.orderByChild("codigoRsir").equalTo(codigo_rsir).limitToFirst(1);

        rsirQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot ds: snapshot.getChildren()){

                    String codigo_rsir_string = ""+ ds.child("codigoRsir").getValue();
                    String aeropuerto_string = ""+ ds.child("aeropuerto").getValue();
                    String compañia_string = ""+ ds.child("compania").getValue();
                    String origen_string = ""+ ds.child("origen").getValue();
                    String destino_string = ""+ ds.child("destino").getValue();
                    String aeronave_string = ""+ ds.child("aeronave").getValue();
                    String matricula_string = ""+ ds.child("matricula").getValue();
                    String area_string = ""+ ds.child("area").getValue();
                    String a_cargo_de_string = ""+ ds.child("aCargoDe").getValue();
                    String fecha_llegada_string = ""+ ds.child("fechaLlegada").getValue();
                    String hora_llegada_string = ""+ ds.child("horaLlegada").getValue();
                    String nvuelo_llegada_string = ""+ ds.child("nvueloLlegada").getValue();
                    String pea_llegada_string = ""+ ds.child("peaLlegada").getValue();
                    String fecha_salida_string = ""+ ds.child("fechaSalida").getValue();
                    String hora_salida_string = ""+ ds.child("horaSalida").getValue();
                    String nvuelo_salida_string = ""+ ds.child("nvueloSalida").getValue();
                    String pea_salida_string = ""+ ds.child("peaSalida").getValue();
                    String estado_string = ""+ ds.child("estado").getValue();

                    estado_rsir = estado_string;

                    //Colocamos los datos
                    tv_codigo_rsir.setText(codigo_rsir_string);
                    tv_estado_rsir.setText(estado_string);
                    tv_aeropuerto.setText(aeropuerto_string);
                    tv_compañia.setText(compañia_string);
                    tv_origen.setText(origen_string);
                    tv_destino.setText(destino_string);
                    tv_aeronaves.setText(aeronave_string);
                    tv_matricula.setText(matricula_string);
                    tv_area.setText(area_string);
                    tv_a_cargo_de.setText(a_cargo_de_string);
                    tv_fecha_llegada.setText(fecha_llegada_string);
                    tv_hora_llegada.setText(hora_llegada_string);
                    tv_nvuelo_llegada.setText(nvuelo_llegada_string);
                    tv_pea_llegada.setText(pea_llegada_string);
                    tv_fecha_salida.setText(fecha_salida_string);
                    tv_hora_salida.setText(hora_salida_string);
                    tv_nvuelo_salida.setText(nvuelo_salida_string);
                    tv_pea_salida.setText(pea_salida_string);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ObtenerServicios();


        btn_reclamar_datos_generales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(estado_rsir.equals("reclamado parcial")){

                    btn_reclamar_datos_generales.setVisibility(View.GONE);
                    btn_reclamar_datos_generales_gris.setVisibility(View.VISIBLE);

                }else {

                    if (ly_reclamo.getVisibility() == View.GONE){

                        ly_reclamo.setVisibility(View.VISIBLE);
                        btn_reclamar_datos_generales.setText("Enviar");

                    }else if (ly_reclamo.getVisibility() == View.VISIBLE){

                        actualizarEstadoRSIR();
                        RegistrarReclamo();

                        ly_reclamo.setVisibility(View.GONE);
                        btn_reclamar_datos_generales.setText("Reclamar Datos Generales");
                    }

                }

            }
        });

        btn_confirmar_pedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                actualizarEstadoPedido();

            }
        });

    }

    private void RegistrarReclamo(){

        //Aqui van los datos que queremos registrar
        assert user != null; //Afirmamos que el usuario no sea nulo

        String codigo_reclamo_string = "R_"+ codigo_rsir;
        String uid_string = user.getUid();
        String rsir_string = codigo_rsir;
        String fecha_string = obtnerFechaActual();
        String estado_string = "pendiente";
        String motivo_string = et_motivo.getText().toString();

        /*Creamos un Hashmap para mandar los datos a firebase*/
        Map<String, Object> datosReclamo = new HashMap<>();

        datosReclamo.put("codigoReclamo", codigo_reclamo_string);
        datosReclamo.put("uid", uid_string);
        datosReclamo.put("codigoRsir", rsir_string);
        datosReclamo.put("codigoServicio", "");
        datosReclamo.put("fechaRegistro", fecha_string);
        datosReclamo.put("tipoReclamo", "datos generales");
        datosReclamo.put("estado", estado_string);
        datosReclamo.put("motivo", motivo_string);


        //Inicializamos la instancia a la base de datos
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference reference = database.getReference("reclamo"); // Este es el nombre de la tabla
        reference.child(codigo_reclamo_string).setValue(datosReclamo).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                Toast.makeText(ValidarServicios.this, "Enviado", Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(ValidarServicios.this, "Algo ha salido mal, vuelva a intentarlo", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void actualizarEstadoRSIR(){

        HashMap<String, Object> resultado = new HashMap<>();
        resultado.put("estado", "reclamado parcial");

        bd_rsir.child(codigo_rsir).updateChildren(resultado)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(ValidarServicios.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


    }

    private void actualizarEstadoPedido(){

        progressDialog.setTitle("Confirmando");
        progressDialog.setMessage("Espere por favor...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        //Aqui van los datos que queremos registrar
        assert user != null; //Afirmamos que el usuario no sea nulo

        for(int i=0; i < listaServicios.size(); i++){

                HashMap<String, Object> resultado = new HashMap<>();
                resultado.put("estado", "validado");

                bd_servicios.child(listaServicios.get(i).getCodigo_servicio()).updateChildren(resultado)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {



                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                Toast.makeText(ValidarServicios.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

        }

        HashMap<String, Object> resultado = new HashMap<>();
        resultado.put("estado", "validado");

        bd_rsir.child(codigo_rsir).updateChildren(resultado)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(ValidarServicios.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        progressDialog.dismiss(); // El progresss se cierra
        Toast.makeText(ValidarServicios.this, "Confirmacion exitosa", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(ValidarServicios.this, Dashboard_cliente.class));

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

    private String makeDateString(int dayOfMonth, int month, int year){
        return dayOfMonth + "/" + month + "/" + year;
    }

    private void ObtenerServicios(){

        Query servicioQuery = bd_servicios.orderByChild("codigoRSIR").equalTo(codigo_rsir);

        servicioQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot ds: snapshot.getChildren()){

                    //Obtenemos los datos
                    String codigo_servicio_string = ""+ ds.child("codigoServicio").getValue();
                    String tipo_string = ""+ ds.child("tipo").getValue();
                    String subtipo_string = ""+ ds.child("subtipo").getValue();
                    String nombre_string = ""+ ds.child("nombre").getValue();
                    String hora_desde_llegada_string = ""+ ds.child("horaDesdeLlegada").getValue();
                    String hora_hasta_llegada_string = ""+ ds.child("horaHastaLlegada").getValue();
                    String cantidad_llegada_string = ""+ ds.child("cantidadLlegada").getValue();
                    String hora_desde_salida_string = ""+ ds.child("horaDesdeSalida").getValue();
                    String hora_hasta_salida_string = ""+ ds.child("horaHastaSalida").getValue();
                    String cantidad_salida_string = ""+ ds.child("cantidadSalida").getValue();
                    String estado_string = ""+ ds.child("estado").getValue();

                    //Colocamos los datos
                    listaServicios.add(new ModeloServicio(user.getUid(), codigo_rsir, tipo_string, subtipo_string,nombre_string, codigo_servicio_string, hora_desde_llegada_string, hora_hasta_llegada_string,
                            hora_desde_salida_string, hora_hasta_salida_string, cantidad_llegada_string, cantidad_salida_string, estado_string));

                    adaptadorListaServicios = new AdaptadorListaServicios(ValidarServicios.this, listaServicios, "revisar", "cliente");
                    adaptadorListaServicios.notifyDataSetChanged();
                    rv_lista_servicios.setAdapter(adaptadorListaServicios);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

}