package com.example.talma.RsirEmpleados;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.talma.Adapters.AdaptadorReclamo;
import com.example.talma.Adapters.AdaptadorRsir;
import com.example.talma.Modelos.ModeloRSIR;
import com.example.talma.Modelos.ModeloReclamo;
import com.example.talma.R;
import com.example.talma.ValidarRsir;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Atencion_reclamos extends AppCompatActivity {

    RecyclerView rv_atencion_reclamos;
    AdaptadorReclamo adaptadorReclamo;
    List<ModeloReclamo> reclamosList = new ArrayList<>();

    ActionBar actionBar;

    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    DatabaseReference bd_reclamos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atencion_reclamos);

        actionBar = getSupportActionBar();
        actionBar.setTitle("Atendecion de Reclamos");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        rv_atencion_reclamos = (RecyclerView) findViewById(R.id.rv_atencion_reclamos);
        rv_atencion_reclamos.setHasFixedSize(true);
        rv_atencion_reclamos.setLayoutManager(new LinearLayoutManager(Atencion_reclamos.this));

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        bd_reclamos = FirebaseDatabase.getInstance().getReference("reclamo");

        ObternerReclamos();


    }


    private void ObternerReclamos(){

        reclamosList.clear();

        Query reclamorQuery = bd_reclamos.orderByChild("estado").equalTo("pendiente");

        reclamorQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot ds: snapshot.getChildren()) {

                        //Obtenemos los datos
                        String codigo_rsir_string = ""+ ds.child("codigoRsir").getValue();
                        String uid_string = ""+ ds.child("uid").getValue();
                        String codigo_servicio_string = ""+ ds.child("codigoServicio").getValue();
                        String codigo_reclamo_string = ""+ ds.child("codigoReclamo").getValue();
                        String estado_string = ""+ ds.child("estado").getValue();
                        String fecha_registro_string = ""+ ds.child("fechaRegistro").getValue();
                        String motivo_string = ""+ ds.child("motivo").getValue();

                        //Colocamos los datos
                        reclamosList.add(new ModeloReclamo(codigo_reclamo_string, uid_string, codigo_rsir_string, codigo_servicio_string, fecha_registro_string, estado_string, motivo_string ));

                    adaptadorReclamo = new AdaptadorReclamo(Atencion_reclamos.this, reclamosList, "cliente");
                        rv_atencion_reclamos.setAdapter(adaptadorReclamo);


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}