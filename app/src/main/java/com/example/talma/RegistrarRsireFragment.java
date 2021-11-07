package com.example.talma;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;


public class RegistrarRsireFragment extends Fragment {

    private Spinner sp_aeropuertos;
    private Spinner sp_aeronaves;
    private Spinner sp_pagos;
    Button btn_siguiente;

    public RegistrarRsireFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_regitrar_rsire, container, false);

        sp_aeropuertos = (Spinner) view.findViewById(R.id.sp_aeropuertos);
        sp_aeronaves = (Spinner) view.findViewById(R.id.sp_aeronaves);
        sp_pagos = (Spinner) view.findViewById(R.id.sp_pagos);
        btn_siguiente = (Button) view.findViewById(R.id.btn_siguiente);

        String [] opciones_aeropuertos = {"Jorge Cahvez","Alfredo Rodríguez Ballón","Alfredo Mendívil Duarte","Armando Revoredo Iglesias", "Chiclayo", "Alejandro Velasco Astete de Cusco"};
        ArrayAdapter<String> adapter_aeropuetos = new ArrayAdapter<String>(view.getContext(), R.layout.sp_aeropuertos,opciones_aeropuertos);
        sp_aeropuertos.setAdapter(adapter_aeropuetos);

        String [] opciones_aeronaves = {"Avión de carga", "Avion comercial"};
        ArrayAdapter<String> adapter_aeronaves = new ArrayAdapter<String>(view.getContext(), R.layout.sp_aeronaves,opciones_aeronaves);
        sp_aeronaves.setAdapter(adapter_aeronaves);

        String [] opciones_pagos = {"Contado", "Credito"};
        ArrayAdapter<String> adapter_pagos = new ArrayAdapter<String>(view.getContext(), R.layout.sp_pagos,opciones_pagos);
        sp_pagos.setAdapter(adapter_pagos);

        btn_siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction =  fragmentManager.beginTransaction();
                transaction.setReorderingAllowed(true);

                transaction.replace(R.id.content, RegistrarServiciosFragment.class, null);
                transaction.commit();
                transaction.addToBackStack(null);
            }
        });

        return view;
    }
}