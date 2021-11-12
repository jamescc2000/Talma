package com.example.talma;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.talma.Adapters.AdaptadorListaServicios;
import com.example.talma.Modelos.ModeloServicio;
import com.example.talma.RsirEmpleados.RegistrarRsire;

import java.util.ArrayList;
import java.util.List;

public class ValidarServicios extends AppCompatActivity {

    AdaptadorListaServicios adapterListaServicios;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validar_servicios);


        recyclerView = findViewById(R.id.rv_lista_servicios_validar);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(ValidarServicios.this));
        List<ModeloServicio> listaServicios = new ArrayList<>();

        listaServicios.add(new ModeloServicio("Counter", "CT49863", "10:00", "11:00",
                "17:00", "18:00", "4", "6"));

        listaServicios.add(new ModeloServicio("Counter", "CT49863", "10:00", "11:00",
                "17:00", "18:00", "4", "6"));

        listaServicios.add(new ModeloServicio("Counter", "CT49863", "10:00", "11:00",
                "17:00", "18:00", "4", "6"));

        listaServicios.add(new ModeloServicio("Counter", "CT49863", "10:00", "11:00",
                "17:00", "18:00", "4", "6"));


        adapterListaServicios = new AdaptadorListaServicios(ValidarServicios.this, listaServicios);
        recyclerView.setAdapter(adapterListaServicios);
    }
}