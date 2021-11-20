package com.example.talma.Fragmentos_empleados;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.talma.Nueva_factura;
import com.example.talma.R;
import com.example.talma.RsirEmpleados.Facturacion;


public class FacturasFragment extends Fragment {

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



        return view;
    }
}