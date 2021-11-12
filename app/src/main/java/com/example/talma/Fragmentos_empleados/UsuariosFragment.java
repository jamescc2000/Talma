package com.example.talma.Fragmentos_empleados;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.talma.R;
import com.example.talma.Registrar_empleados;
import com.example.talma.registrar_clientes;


public class UsuariosFragment extends Fragment {
    Button Btn_registrar_clientes, Btn_registrar_empleados;



    public UsuariosFragment() {
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
        View view = inflater.inflate(R.layout.fragment_usuarios, container, false);
        Btn_registrar_clientes = (Button) view.findViewById(R.id.Btn_registrar_clientes);
        Btn_registrar_empleados = (Button) view.findViewById(R.id.Btn_registrar_empleados);

        Btn_registrar_clientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), registrar_clientes.class);
                startActivity(intent);
            }
        });

        Btn_registrar_empleados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Registrar_empleados.class);
                startActivity(intent);
            }
        });




        return view;

    }
}