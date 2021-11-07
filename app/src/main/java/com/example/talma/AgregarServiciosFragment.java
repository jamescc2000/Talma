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


public class AgregarServiciosFragment extends Fragment {

    private Spinner sp_servicios;
    private Button btn_guardar_servicio;



    public AgregarServiciosFragment() {
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
        View view = inflater.inflate(R.layout.fragment_agregar_servicios, container, false);

        sp_servicios = (Spinner) view.findViewById(R.id.sp_aeropuertos);
        btn_guardar_servicio = (Button) view.findViewById(R.id.btn_guardar_servicio);

        String [] opciones_servicios = {"Montacarga","Tractor","Estibador"};
        ArrayAdapter<String> adapter_servicios = new ArrayAdapter<String>(view.getContext(), R.layout.spinner,opciones_servicios);
        sp_servicios.setAdapter(adapter_servicios);

        btn_guardar_servicio.setOnClickListener(new View.OnClickListener() {
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