package com.example.talma.Fragmento_cliente;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.talma.MainActivity;
import com.example.talma.R;
import com.example.talma.RealizarPedido;
import com.example.talma.RealizarReclam;
import com.example.talma.RealizarReclamo;
import com.example.talma.ValidarRsir;
import com.google.firebase.auth.FirebaseAuth;


public class PedFragment extends Fragment {

    Button btn_realizar_pedido, btn_validar_servicio,btn_realizar_reclamo, btn_cerrar_sesion;

    FirebaseAuth firebaseAuth;


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
        btn_cerrar_sesion = (Button) view.findViewById(R.id.btn_mis_facturas);

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
                Intent intent = new Intent(view.getContext(), RealizarReclam.class);
                startActivity(intent);
            }
        });

        btn_cerrar_sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                Toast.makeText(getActivity(), "Ha cerrado sesion", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), MainActivity.class));
            }
        });



        return view;
    }
}