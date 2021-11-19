package com.example.talma.Fragmento_cliente;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.talma.R;
import com.example.talma.RealizarPedido;
import com.example.talma.RsirCliente.RealizarReclamo;
import com.example.talma.ValidarRsir;


public class PedFragment extends Fragment {

    Button btn_realizar_pedido, btn_validar_servicio,btn_realizar_reclamo;


    public PedFragment() {
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
        View view = inflater.inflate(R.layout.fragment_ped, container, false);
        btn_realizar_pedido = (Button) view.findViewById(R.id.btn_realizar_pedido);
        btn_validar_servicio = (Button) view.findViewById(R.id.btn_validar_servicio);
        btn_realizar_reclamo = (Button) view.findViewById(R.id.btn_realizar_reclamo);

        btn_realizar_pedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), RealizarPedido.class);
                startActivity(intent);
            }
        });

        btn_validar_servicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), ValidarRsir.class);
                startActivity(intent);
            }
        });
        btn_realizar_reclamo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), RealizarReclamo.class);
                startActivity(intent);
            }
        });

        return view;
    }
}