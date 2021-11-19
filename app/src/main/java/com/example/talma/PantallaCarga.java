package com.example.talma;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class PantallaCarga extends AppCompatActivity {

    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_carga);

        actionBar = getSupportActionBar();
        actionBar.hide();

        //Tiempo que durara la pantalla de cargar, en segundos
        final int duracion = 2500;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Esto se ejecutara despues de la pantalla de carga
                Intent intent = new Intent(PantallaCarga.this, Dashboard_empleados.class);
                startActivity(intent);
                finish();
            }
        }, duracion);
    }
}