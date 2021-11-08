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
import android.widget.Spinner;
import android.widget.TextView;

import com.example.talma.Adapters.AdaptadorListaServicios;
import com.example.talma.Fragmentos_empleados.RsireFragment;
import com.example.talma.Modelos.ModeloServicio;

import java.util.ArrayList;
import java.util.List;

public class RegistrarServicios extends AppCompatActivity {

    RecyclerView recyclerView;
    AdaptadorListaServicios adapterListaServicios;
    List<ModeloServicio> servicios = new ArrayList<>();
    List<ModeloServicio> listaServicios = new ArrayList<>();
    Button btn_agregar, btn_finalizar;

    private Spinner sp_servicios;
    private EditText et_codigo, et_hora_desde_llegada, et_hora_hasta_llegada, et_cantidad_llegada, et_hora_desde_salida, et_hora_hasta_salida, et_cantidad_salida;
    private TextView tv_cantidad_total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_servicios);

        sp_servicios = (Spinner) findViewById(R.id.sp_servicios);
        et_codigo = (EditText) findViewById(R.id.et_codigo);
        et_hora_desde_llegada = (EditText) findViewById(R.id.et_hora_desde_llegada);
        et_hora_hasta_llegada = (EditText) findViewById(R.id.et_hora_hasta_llegada);
        et_cantidad_llegada = (EditText) findViewById(R.id.et_cantidad_llegada);
        et_hora_desde_salida = (EditText) findViewById(R.id.et_hora_desde_salida);
        et_hora_hasta_salida = (EditText) findViewById(R.id.et_hora_hasta_salida);
        et_cantidad_salida = (EditText) findViewById(R.id.et_cantidad_salida);
        tv_cantidad_total = (TextView) findViewById(R.id.tv_cantidad_total);

        String [] opciones_servicios = {"Montacarga","Tractor","Estibador"};
        ArrayAdapter<String> adapter_servicios = new ArrayAdapter<String>(RegistrarServicios.this, R.layout.spinner,opciones_servicios);
        sp_servicios.setAdapter(adapter_servicios);


        btn_agregar = (Button) findViewById(R.id.btn_agregar);
        btn_finalizar = (Button) findViewById(R.id.btn_finalizar);

        recyclerView = findViewById(R.id.rvListaServicios);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(RegistrarServicios.this));


        btn_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listaServicios.add(new ModeloServicio(
                        sp_servicios.getSelectedItem().toString(),
                        et_codigo.getText().toString(),
                        et_hora_desde_llegada.getText().toString(),
                        et_hora_hasta_llegada.getText().toString(),
                        et_hora_desde_salida.getText().toString(),
                        et_hora_hasta_salida.getText().toString(),
                        et_cantidad_llegada.getText().toString(),
                        et_cantidad_salida.getText().toString()));

            }
        });

        btn_finalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        adapterListaServicios = new AdaptadorListaServicios(RegistrarServicios.this, listaServicios);
        recyclerView.setAdapter(adapterListaServicios);


    }
}