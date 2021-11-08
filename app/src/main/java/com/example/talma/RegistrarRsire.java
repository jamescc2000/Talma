package com.example.talma;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.talma.Adapters.AdaptadorListaServicios;
import com.example.talma.Fragmentos_empleados.RsireFragment;
import com.example.talma.Modelos.ModeloServicio;

import java.util.ArrayList;
import java.util.List;

public class RegistrarRsire extends AppCompatActivity {


    private Spinner sp_aeropuertos;
    private Spinner sp_aeronaves;
    private Spinner sp_pagos;
    Button btn_siguiente, btn_agregar, btn_finalizar;

    private EditText et_nombre;

    LinearLayout ll_registrar_rsire;

    RecyclerView recyclerView;
    AdaptadorListaServicios adapterListaServicios;
    List<ModeloServicio> servicios = new ArrayList<>();
    List<ModeloServicio> listaServicios = new ArrayList<>();
    LinearLayout ll_agregar_servicio;


    private Spinner sp_servicios;
    private EditText et_codigo, et_hora_desde_llegada, et_hora_hasta_llegada, et_cantidad_llegada, et_hora_desde_salida, et_hora_hasta_salida, et_cantidad_salida;
    private TextView tv_cantidad_total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_rsire);

        sp_aeropuertos = (Spinner) findViewById(R.id.sp_aeropuertos);
        sp_aeronaves = (Spinner) findViewById(R.id.sp_aeronaves);
        sp_pagos = (Spinner) findViewById(R.id.sp_pagos);
        btn_siguiente = (Button) findViewById(R.id.btn_siguiente);
        btn_finalizar = (Button) findViewById(R.id.btn_finalizar);
        btn_agregar = (Button) findViewById(R.id.btn_agregar);
        sp_servicios = (Spinner) findViewById(R.id.sp_servicios);
        et_codigo = (EditText) findViewById(R.id.et_codigo);
        et_hora_desde_llegada = (EditText) findViewById(R.id.et_hora_desde_llegada);
        et_hora_hasta_llegada = (EditText) findViewById(R.id.et_hora_hasta_llegada);
        et_cantidad_llegada = (EditText) findViewById(R.id.et_cantidad_llegada);
        et_hora_desde_salida = (EditText) findViewById(R.id.et_hora_desde_salida);
        et_hora_hasta_salida = (EditText) findViewById(R.id.et_hora_hasta_salida);
        et_cantidad_salida = (EditText) findViewById(R.id.et_cantidad_salida);
        tv_cantidad_total = (TextView) findViewById(R.id.tv_cantidad_total);
        ll_agregar_servicio = (LinearLayout) findViewById(R.id.ll_agregar_servicio);

        String [] opciones_servicios = {"Montacarga","Tractor","Estibador"};
        ArrayAdapter<String> adapter_servicios = new ArrayAdapter<String>(RegistrarRsire.this, R.layout.spinner,opciones_servicios);
        sp_servicios.setAdapter(adapter_servicios);
        ll_registrar_rsire = (LinearLayout) findViewById(R.id.ll_registrar_rsire);

        String [] opciones_aeropuertos = {"Jorge Cahvez","Alfredo Rodríguez Ballón","Alfredo Mendívil Duarte","Armando Revoredo Iglesias", "Chiclayo", "Alejandro Velasco Astete de Cusco"};
        ArrayAdapter<String> adapter_aeropuetos = new ArrayAdapter<String>(RegistrarRsire.this, R.layout.spinner,opciones_aeropuertos);
        sp_aeropuertos.setAdapter(adapter_aeropuetos);

        String [] opciones_aeronaves = {"Avión de carga", "Avion comercial"};
        ArrayAdapter<String> adapter_aeronaves = new ArrayAdapter<String>(RegistrarRsire.this, R.layout.spinner,opciones_aeronaves);
        sp_aeronaves.setAdapter(adapter_aeronaves);

        String [] opciones_pagos = {"Contado", "Credito"};
        ArrayAdapter<String> adapter_pagos = new ArrayAdapter<String>(RegistrarRsire.this, R.layout.spinner,opciones_pagos);
        sp_pagos.setAdapter(adapter_pagos);

        recyclerView = findViewById(R.id.rvListaServicios);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(RegistrarRsire.this));

        btn_siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ll_registrar_rsire.getVisibility() == View.GONE){

                    ll_registrar_rsire.setVisibility(View.VISIBLE);
                    btn_siguiente.setText("GUARDAR");

                }else if (ll_registrar_rsire.getVisibility() == View.VISIBLE){

                    ll_registrar_rsire.setVisibility(View.GONE);
                    btn_siguiente.setText("EDITAR RSIR");
                }


            }
        });

        btn_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ll_agregar_servicio.getVisibility() == View.GONE){
                    ll_agregar_servicio.setVisibility(View.VISIBLE);
                    btn_agregar.setText("Guardar");
                }else if (ll_agregar_servicio.getVisibility() == View.VISIBLE){
                    listaServicios.add(new ModeloServicio(
                            sp_servicios.getSelectedItem().toString(),
                            et_codigo.getText().toString(),
                            et_hora_desde_llegada.getText().toString(),
                            et_hora_hasta_llegada.getText().toString(),
                            et_hora_desde_salida.getText().toString(),
                            et_hora_hasta_salida.getText().toString(),
                            et_cantidad_llegada.getText().toString(),
                            et_cantidad_salida.getText().toString()));
                    btn_agregar.setText("Agregar servicio");
                    ll_agregar_servicio.setVisibility(View.GONE);
                }


            }
        });

        btn_finalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrarRsire.this, Dashboard_empleados.class);
                startActivity(intent);
            }
        });

        adapterListaServicios = new AdaptadorListaServicios(RegistrarRsire.this, listaServicios);
        recyclerView.setAdapter(adapterListaServicios);

    }
}