package com.example.talma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.talma.Adapters.AdaptadorListaServicios;
import com.example.talma.Modelos.ModeloFactura;
import com.example.talma.Modelos.ModeloServicio;
import com.example.talma.RsirEmpleados.RegistrarRsire;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Nueva_factura extends AppCompatActivity {

    private EditText et_RSIR,et_encargado_facturacion;
    private Button btn_fecha_emision;

    //AdaptadorListaDatosFactura adaptadorListaDatosFactura;

    List<ModeloFactura> ListaDatosFactura = new ArrayList <>();

    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    DatabaseReference bd_servicio;
    DatePickerDialog datePickerDialog;
    ProgressDialog progressDialog;

    String codigo_rsir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_factura);

        et_RSIR = (EditText) findViewById(R.id.et_RSIR);
        et_encargado_facturacion = (EditText) findViewById(R.id.et_encargado_facturacion);
        btn_fecha_emision = (Button) findViewById(R.id.btn_fecha_emision);

        firebaseAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        progressDialog = new ProgressDialog(Nueva_factura.this);

        bd_servicio = FirebaseDatabase.getInstance().getReference("servicios");
        Intent intent = getIntent();
        codigo_rsir = intent.getStringExtra("codigoRsir");

        btn_fecha_emision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String fecha = makeDateString(dayOfMonth, month, year);
                        btn_fecha_emision.setText(fecha);
                    }
                };
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int moth = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int style  = AlertDialog.THEME_HOLO_LIGHT;
                datePickerDialog = new DatePickerDialog(Nueva_factura.this, style, dateSetListener, year, moth, day);
                datePickerDialog.show();
            }
        });


    }

    private void RegistrarFactura (){
        progressDialog.setTitle("Registrando");
        progressDialog.setMessage("Espere por favor...");
        progressDialog.setCancelable(false);
        progressDialog.show();

    }

    public void RegistarClientes(String Email, String Password){

        progressDialog.setTitle("Registrando");
        progressDialog.setMessage("Espere por favor...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        assert user != null; //Afirmamos que el usuario no sea nulo

        String uid_String = user.getUid();
        String RSIR_String = et_RSIR.getText().toString();
        String encargado_facturacion_String = et_encargado_facturacion.getText().toString();
        String fecha_emision_String = btn_fecha_emision.getText().toString();

        /*Creamos un Hashmap para mandar los datos a firebase*/
        Map<String, Object> datosNuevaFactura = new HashMap<>();

        datosNuevaFactura.put("uid", uid_String);
        datosNuevaFactura.put("RSIR", RSIR_String);
        datosNuevaFactura.put("EncargadoFacturacion", encargado_facturacion_String);
        datosNuevaFactura.put("FechaEmision", fecha_emision_String);

        bd_servicio.child(uid_String).setValue(datosNuevaFactura).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                progressDialog.dismiss(); // El progresss se cierra

                Toast.makeText(Nueva_factura.this, "Registro exitoso", Toast.LENGTH_SHORT).show();


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                progressDialog.dismiss(); // El progresss se cierra
                Toast.makeText(Nueva_factura.this, "Algo ha salido mal, vuelva a intentarlo", Toast.LENGTH_SHORT).show();

            }
        });


    }

    private String makeDateString(int dayOfMonth, int month, int year){
        return dayOfMonth + "/" + month + "/" + year;
    }

    /*private void ObtenerServicios () {

        bd_servicios.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()){
                    bd_facturas.child()
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        })
    }*/

    private void ObtenerServicios(){

        Query servicioQuery = bd_servicio.orderByChild("codigoRSIR").equalTo(codigo_rsir);

        servicioQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot ds: snapshot.getChildren()){

                    //Obtenemos los datos
                    Float  peso_float = ds.getValue(Float.class) ;
                    String fecha_salida = ""+ ds.child("fecha_salida").getValue();
                    String fecha_llegada = ""+ ds.child("fecha_llegada").getValue();

                    try {

                        SimpleDateFormat sdf_salida = new SimpleDateFormat("dd/mm/yyyy");
                        SimpleDateFormat sdf_llegada = new SimpleDateFormat("dd/mm/yyyy");

                        Date date_salida = sdf_salida.parse(fecha_salida);
                        Date date_llegada = sdf_salida.parse(fecha_llegada);

                        long dif = date_salida.getTime() - date_llegada.getTime();

                        TimeUnit time = TimeUnit.DAYS;
                        long diferencia =  time.convert(dif,TimeUnit.MILLISECONDS);

                        //Colocamos los datos
                        ListaDatosFactura.add(new ModeloFactura(diferencia,peso_float));

                        //adaptadorListaDatosFactura = new AdaptadorListaDatos (Nueva_factura.this, ListaDatosFactura,"","");
                        //adaptadorListaDatosFactura.notifyDataSetChanged();


                    } catch (Exception e){

                    }



                    /*Colocamos los datos
                    listaServicios.add(new ModeloServicio(user.getUid(), codigo_rsir,nombre_string, codigo_servicio_string, hora_desde_llegada_string, hora_hasta_llegada_string,
                            hora_desde_salida_string, hora_hasta_salida_string, cantidad_llegada_string, cantidad_salida_string, estado_string));

                    adaptadorListaServicios = new AdaptadorListaServicios(ValidarServicios.this, listaServicios, "revisar", "cliente");
                    adaptadorListaServicios.notifyDataSetChanged();
                    rv_lista_servicios.setAdapter(adaptadorListaServicios);*/

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}