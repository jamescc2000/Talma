package com.example.talma.RsirEmpleados;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.talma.Modelos.ModeloRSIR;
import com.example.talma.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.Locale;

public class RevisarServicios extends AppCompatActivity {

    ActionBar actionBar;

    TextView tv_codigo_rsir, tv_aeropuerto, tv_compañia, tv_origen, tv_destino, tv_aeronaves, tv_matricula, tv_area, tv_a_cargo_de;
    TextView tv_fecha_llegada, tv_hora_llegada, tv_nvuelo_llegada, tv_pea_llegada;
    TextView tv_fecha_salida, tv_hora_salida, tv_nvuelo_salida, tv_pea_salida;

    DatabaseReference bd_rsir;

    String codigo_rsir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revisar_servicios);

        actionBar = getSupportActionBar();
        actionBar.setTitle("Revisar Servicios");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        tv_codigo_rsir = (TextView) findViewById(R.id.tv_codigo_rsir);
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

        Intent intent = getIntent();
        codigo_rsir = intent.getStringExtra("codigoRsir");

        bd_rsir = FirebaseDatabase.getInstance().getReference("rsir");

        Toast.makeText(RevisarServicios.this, codigo_rsir, Toast.LENGTH_SHORT).show();

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

                    //Colocamos los datos
                    tv_codigo_rsir.setText(codigo_rsir_string);
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

    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

}