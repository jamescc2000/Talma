package com.example.talma.Fragmentos_empleados;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.talma.Facturacion;
import com.example.talma.Nueva_factura;
import com.example.talma.R;



public class FacturasFragment extends Fragment {

    Button btn_nueva_factura, btn_visualizar_factura;

    public FacturasFragment() {
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
        View view = inflater.inflate(R.layout.fragment_facturas, container, false);

        btn_nueva_factura = (Button) view.findViewById(R.id.btn_nueva_factura);

        btn_nueva_factura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), Nueva_factura.class);
                startActivity(intent);
            }
        });


        return view;
    }
}