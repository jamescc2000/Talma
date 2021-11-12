package com.example.talma.RsirEmpleados;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
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
import com.example.talma.Dashboard_empleados;
import com.example.talma.Modelos.ModeloServicio;
import com.example.talma.R;

import java.util.ArrayList;
import java.util.List;

public class RegistrarRsire extends AppCompatActivity {


    private Spinner sp_aeropuertos;
    private Spinner sp_aeronaves;
    private Spinner sp_area;
    Button btn_siguiente, btn_agregar, btn_finalizar;

    private EditText et_nombre;

    LinearLayout ll_registrar_rsire;
    CardView cv_datos_rsire;

    RecyclerView recyclerView;
    AdaptadorListaServicios adapterListaServicios;
    List<ModeloServicio> servicios = new ArrayList<>();
    List<ModeloServicio> listaServicios = new ArrayList<>();
    LinearLayout ll_agregar_servicio;


    private Spinner sp_servicios;
    private EditText et_codigo, et_hora_desde_llegada, et_hora_hasta_llegada, et_cantidad_llegada, et_hora_desde_salida, et_hora_hasta_salida, et_cantidad_salida;
    private EditText et_compañia, et_origen, et_destino, et_matricula, et_a_cargo_de,et_fecha_llegada, et_hora_llegada, et_nvuelo_llegada, et_pea_llegada;
    private EditText et_fecha_salida, et_hora_salida, et_nvuelo_salida, et_pea_salida, et_codigo_rsir;
    private TextView tv_cantidad_total, tv_aeropuerto, tv_tipo_aeronave, tv_fechas, tv_compañia, tv_matricula, tv_origen_destino;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_rsire);

        sp_aeropuertos = (Spinner) findViewById(R.id.sp_aeropuertos);
        sp_aeronaves = (Spinner) findViewById(R.id.sp_aeronaves);
        sp_area = (Spinner) findViewById(R.id.sp_area);
        btn_siguiente = (Button) findViewById(R.id.btn_siguiente);
        btn_finalizar = (Button) findViewById(R.id.btn_finalizar);
        btn_agregar = (Button) findViewById(R.id.btn_agregar);
        et_codigo_rsir = (EditText) findViewById(R.id.et_codigo_rsir);
        et_compañia = (EditText) findViewById(R.id.et_compañia);
        et_origen = (EditText) findViewById(R.id.et_origen);
        et_destino = (EditText) findViewById(R.id.et_destino);
        et_matricula = (EditText) findViewById(R.id.et_matricula);
        et_a_cargo_de = (EditText) findViewById(R.id.et_a_cargo_de);
        et_fecha_llegada = (EditText) findViewById(R.id.et_fecha_llegada);
        et_hora_llegada = (EditText) findViewById(R.id.et_hora_llegada);
        et_nvuelo_llegada = (EditText) findViewById(R.id.et_nvuelo_llegada);
        et_pea_llegada = (EditText) findViewById(R.id.et_pea_llegada);
        et_fecha_salida = (EditText) findViewById(R.id.et_fecha_salida);
        et_hora_salida = (EditText) findViewById(R.id.et_hora_salida);
        et_nvuelo_salida = (EditText) findViewById(R.id.et_nvuelo_salida);
        et_pea_salida = (EditText) findViewById(R.id.et_pea_salida);
        sp_servicios = (Spinner) findViewById(R.id.sp_servicios);
        et_codigo = (EditText) findViewById(R.id.et_codigo);
        et_hora_desde_llegada = (EditText) findViewById(R.id.et_hora_desde_llegada);
        et_hora_hasta_llegada = (EditText) findViewById(R.id.et_hora_hasta_llegada);
        et_cantidad_llegada = (EditText) findViewById(R.id.et_cantidad_llegada);
        et_hora_desde_salida = (EditText) findViewById(R.id.et_hora_desde_salida);
        et_hora_hasta_salida = (EditText) findViewById(R.id.et_hora_hasta_salida);
        et_cantidad_salida = (EditText) findViewById(R.id.et_cantidad_salida);
        tv_aeropuerto = (TextView) findViewById(R.id.tv_aeropuerto);
        tv_tipo_aeronave = (TextView) findViewById(R.id.tv_tipo_aeronave);
        tv_matricula = (TextView) findViewById(R.id.tv_matricula);
        tv_fechas = (TextView) findViewById(R.id.tv_fechas);
        tv_origen_destino = (TextView) findViewById(R.id.tv_origen_destino);
        tv_compañia = (TextView) findViewById(R.id.tv_compañia);
        ll_agregar_servicio = (LinearLayout) findViewById(R.id.ll_agregar_servicio);
        cv_datos_rsire = (CardView) findViewById(R.id.cv_datos_rsire);

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

        String [] opciones_areas = {"Patio de Equipaje", "Frio Aereo", "Counter"};
        ArrayAdapter<String> adapter_areas = new ArrayAdapter<String>(RegistrarRsire.this, R.layout.spinner,opciones_areas);
        sp_area.setAdapter(adapter_areas);

        recyclerView = findViewById(R.id.rvListaServicios);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(RegistrarRsire.this));

        btn_siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ll_registrar_rsire.getVisibility() == View.GONE){

                    ll_registrar_rsire.setVisibility(View.VISIBLE);
                    btn_siguiente.setText("GUARDAR");
                    cv_datos_rsire.setVisibility(View.GONE);

                }else if (ll_registrar_rsire.getVisibility() == View.VISIBLE){

                    ll_registrar_rsire.setVisibility(View.GONE);
                    btn_siguiente.setText("EDITAR DATOS GENERALES");

                    tv_aeropuerto.setText(sp_aeropuertos.getSelectedItem().toString());
                    tv_tipo_aeronave.setText(sp_aeronaves.getSelectedItem().toString());
                    tv_fechas.setText(et_fecha_salida.getText().toString() + " - " + et_fecha_llegada.getText().toString());
                    tv_compañia.setText(et_compañia.getText().toString());
                    tv_matricula.setText(et_matricula.getText().toString());
                    tv_origen_destino.setText(et_origen.getText().toString() + " - " + et_destino.getText().toString());

                    cv_datos_rsire.setVisibility(View.VISIBLE);
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