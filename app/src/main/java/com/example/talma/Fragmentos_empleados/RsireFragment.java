package com.example.talma.Fragmentos_empleados;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.talma.R;
import com.example.talma.RegistrarRsire;


public class RsireFragment extends Fragment {

    Button btn_registrar_rsire, btn_revisar_servicios, btn_atender_reclamos,btn_facturacion;



    public RsireFragment() {
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
        View view = inflater.inflate(R.layout.fragment_rsire, container, false);

        btn_registrar_rsire = (Button) view.findViewById(R.id.btn_registrar_rsire);
        btn_revisar_servicios = (Button) view.findViewById(R.id.btn_revisar_servicios);
        btn_atender_reclamos = (Button) view.findViewById(R.id.btn_atender_reclamos);
        btn_facturacion = (Button) view.findViewById(R.id.btn_facturacion);

        btn_registrar_rsire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(view.getContext(), RegistrarRsire.class);
                startActivity(intent);

            }
        });



        return view;
    }
}