package com.example.talma;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;

import java.util.ArrayList;


public class RegistrarServiciosFragment extends Fragment {

    private TableLayout tabla_servicios;
    private String [] header = {"Nombre", "Codigo", " "};
    private ArrayList<String[]> rows = new ArrayList<>();
    private TablaDinamica tablaDinamica;
    private EditText et_nombre;
    Button btn_agregar;

    public RegistrarServiciosFragment() {
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
        View view = inflater.inflate(R.layout.fragment_registrar_servicios, container, false);

        tabla_servicios = (TableLayout) view.findViewById(R.id.tabla_servicios);
        et_nombre = (EditText) view.findViewById(R.id.et_nombre);
        btn_agregar = (Button)view.findViewById(R.id.btn_agregar);

        tablaDinamica = new TablaDinamica(tabla_servicios, view.getContext());
        tablaDinamica.addHeader(header);
        tablaDinamica.addData(getServicios());
        tablaDinamica.backgroundHeader(Color.BLACK);
        tablaDinamica.backgroundData(Color.GRAY, Color.DKGRAY);
        tablaDinamica.lineColor(Color.WHITE);
        tablaDinamica.textColorData(Color.WHITE);
        tablaDinamica.textColorHeader(Color.WHITE);

        btn_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction =  fragmentManager.beginTransaction();
                transaction.setReorderingAllowed(true);

                transaction.replace(R.id.content, AgregarServiciosFragment.class, null);
                transaction.commit();
                transaction.addToBackStack(null);
            }
        });

        return view;
    }

    private ArrayList <String[]> getServicios(){
        rows.add(new String[]{"Escala tecnica", "SET124", " "});
        rows.add(new String[]{"Escala tecnica", "SET124", " "});
        rows.add(new String[]{"Escala tecnica", "SET124", " "});
        return rows;
    }

}