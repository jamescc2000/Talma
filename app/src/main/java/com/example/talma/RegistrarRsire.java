package com.example.talma;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.talma.Adapters.AdaptadorListaServicios;
import com.example.talma.Modelos.ModeloServicio;

import java.util.List;

public class RegistrarRsire extends AppCompatActivity {


    private Spinner sp_aeropuertos;
    private Spinner sp_aeronaves;
    private Spinner sp_pagos;
    Button btn_siguiente;

    private EditText et_nombre;
    Button btn_agregar, btn_finalizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_rsire);

        sp_aeropuertos = (Spinner) findViewById(R.id.sp_aeropuertos);
        sp_aeronaves = (Spinner) findViewById(R.id.sp_aeronaves);
        sp_pagos = (Spinner) findViewById(R.id.sp_pagos);
        btn_siguiente = (Button) findViewById(R.id.btn_siguiente);

        String [] opciones_aeropuertos = {"Jorge Cahvez","Alfredo Rodríguez Ballón","Alfredo Mendívil Duarte","Armando Revoredo Iglesias", "Chiclayo", "Alejandro Velasco Astete de Cusco"};
        ArrayAdapter<String> adapter_aeropuetos = new ArrayAdapter<String>(RegistrarRsire.this, R.layout.spinner,opciones_aeropuertos);
        sp_aeropuertos.setAdapter(adapter_aeropuetos);

        String [] opciones_aeronaves = {"Avión de carga", "Avion comercial"};
        ArrayAdapter<String> adapter_aeronaves = new ArrayAdapter<String>(RegistrarRsire.this, R.layout.spinner,opciones_aeronaves);
        sp_aeronaves.setAdapter(adapter_aeronaves);

        String [] opciones_pagos = {"Contado", "Credito"};
        ArrayAdapter<String> adapter_pagos = new ArrayAdapter<String>(RegistrarRsire.this, R.layout.spinner,opciones_pagos);
        sp_pagos.setAdapter(adapter_pagos);

        btn_siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(RegistrarRsire.this, RegistrarServicios.class);
                startActivity(intent);
            }
        });


    }
}