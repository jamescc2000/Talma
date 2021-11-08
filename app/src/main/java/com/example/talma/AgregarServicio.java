package com.example.talma;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.talma.Modelos.ModeloServicio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AgregarServicio extends AppCompatActivity {

    private Spinner sp_servicios;
    private Button btn_guardar_servicio;
    private EditText et_codigo, et_hora_desde_llegada, et_hora_hasta_llegada, et_cantidad_llegada, et_hora_desde_salida, et_hora_hasta_salida, et_cantidad_salida;
    private TextView tv_cantidad_total;
    List<ModeloServicio> listaServicios = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_servicio);

        sp_servicios = (Spinner) findViewById(R.id.sp_servicios);
        et_codigo = (EditText) findViewById(R.id.et_codigo);
        et_hora_desde_llegada = (EditText) findViewById(R.id.et_hora_desde_llegada);
        et_hora_hasta_llegada = (EditText) findViewById(R.id.et_hora_hasta_llegada);
        et_cantidad_llegada = (EditText) findViewById(R.id.et_cantidad_llegada);
        et_hora_desde_salida = (EditText) findViewById(R.id.et_hora_desde_salida);
        et_hora_hasta_salida = (EditText) findViewById(R.id.et_hora_hasta_salida);
        et_cantidad_salida = (EditText) findViewById(R.id.et_cantidad_salida);
        tv_cantidad_total = (TextView) findViewById(R.id.tv_cantidad_total);
        btn_guardar_servicio = (Button) findViewById(R.id.btn_guardar_servicio);

        String [] opciones_servicios = {"Montacarga","Tractor","Estibador"};
        ArrayAdapter<String> adapter_servicios = new ArrayAdapter<String>(AgregarServicio.this, R.layout.spinner,opciones_servicios);
        sp_servicios.setAdapter(adapter_servicios);


        btn_guardar_servicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               /* listaServicios.add(new ModeloServicio(
                        sp_servicios.getSelectedItem().toString(),
                        et_codigo.getText().toString(),
                        et_hora_desde_llegada.getText().toString(),
                        et_hora_hasta_llegada.getText().toString(),
                        et_cantidad_llegada.getText().toString(),
                        et_hora_desde_salida.getText().toString(),
                        et_hora_hasta_salida.getText().toString(),
                        et_cantidad_salida.getText().toString()));*/

                listaServicios.add(new ModeloServicio(
                        "Tractor",
                        "DSD4F6S",
                        "10:11",
                        "11:00",
                        "10:11",
                        "15:54",
                        "4",
                        "6"));

                Intent intent = new Intent(AgregarServicio.this, RegistrarServicios.class);
                intent.putExtra("listaServicios", (Serializable) listaServicios);
                startActivity(intent);

            }
        });

    }
}