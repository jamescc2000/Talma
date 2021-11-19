package com.example.talma.RsirEmpleados;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.talma.Adapters.AdaptadorRsir;
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class RevisarRsir extends AppCompatActivity {

    RecyclerView recyclerView;
    AdaptadorRsir adaptadorRsir;
    List<ModeloRSIR> rsirsList;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    DatabaseReference bd_rsir;
    ActionBar actionBar;

    public RevisarRsir() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revisar_rsir);

        actionBar = getSupportActionBar();
        actionBar.setTitle("Revisar RSIR");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.rv_servicios);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(RevisarRsir.this));

        rsirsList = new ArrayList<>();

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        bd_rsir = FirebaseDatabase.getInstance().getReference("rsir");


        //Obtener todos los rsir
        ObtenerTodosRSIR();

    }

    private void ObtenerTodosRSIR() {

        bd_rsir.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot ds: snapshot.getChildren()){

                    ModeloRSIR rsir = ds.getValue(ModeloRSIR.class);

                    if(rsir.getUid().equals(user.getUid())){

                        //Obtenemos los datos
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

                        //Colocamos los datos
                        rsirsList.add(new ModeloRSIR(user.getUid(),codigo_rsir_string, aeropuerto_string, compañia_string, origen_string, destino_string,
                                aeronave_string, matricula_string, area_string, a_cargo_de_string, fecha_llegada_string, hora_llegada_string,
                                nvuelo_llegada_string, pea_llegada_string, fecha_salida_string, hora_salida_string, nvuelo_salida_string, pea_salida_string,estado_string));

                        adaptadorRsir = new AdaptadorRsir(RevisarRsir.this, rsirsList,"empleado");
                        adaptadorRsir.notifyDataSetChanged();
                        recyclerView.setAdapter(adaptadorRsir);

                    }
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