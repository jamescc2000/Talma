package com.example.talma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.talma.Modelos.ModeloRSIR;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class PantallaCarga extends AppCompatActivity {

    ActionBar actionBar;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    DatabaseReference BASE_DATOS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_carga);

        actionBar = getSupportActionBar();
        actionBar.hide();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        int caso = 0;


        //Tiempo que durara la pantalla de cargar, en segundos
        final int duracion = 2500;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Esto se ejecutara despues de la pantalla de carga

                if(firebaseUser != null){

                    BASE_DATOS = FirebaseDatabase.getInstance().getReference("clientes");
                    Query user2Query = BASE_DATOS.orderByChild("uid").equalTo(firebaseUser.getUid());

                    user2Query.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            Intent intent = new Intent(PantallaCarga.this, Dashboard_cliente.class);
                            startActivity(intent);
                            finish();

                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                    BASE_DATOS = FirebaseDatabase.getInstance().getReference("empleados");
                    Query userQuery = BASE_DATOS.orderByChild("uid").equalTo(firebaseUser.getUid());

                    userQuery.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            Intent intent = new Intent(PantallaCarga.this, Dashboard_empleados.class);
                            startActivity(intent);
                            finish();

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });



                }else if (firebaseUser == null){

                    Intent intent = new Intent(PantallaCarga.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                }

            }
        }, duracion);
    }
}