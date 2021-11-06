package com.example.talma;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TableLayout;

import java.util.ArrayList;


public class RegistrarServiciosFragment extends Fragment {

    private TableLayout table_servicios;
    private String [] header = {"Nombre", "Codigo", " "};
    private ArrayList<String[]> rows = new ArrayList<>();
    private TablaDinamica tablaDinamica;

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

        table_servicios = (TableLayout) view.findViewById(R.id.table_servicios);

        tablaDinamica = new TablaDinamica(table_servicios, view.getContext());
        tablaDinamica.addHeader(header);
        tablaDinamica.addData(getServicios());
        tablaDinamica.backgroundHeader(Color.BLACK);
        tablaDinamica.backgroundData(Color.GRAY, Color.DKGRAY);
        tablaDinamica.lineColor(Color.BLACK);
        tablaDinamica.textColorData(Color.BLACK);
        tablaDinamica.textColorHeader(Color.WHITE);

        return view;
    }

    public void agregar(View view){
        String [] item = new String[] {"Remolque", "SRMQ167"};
        tablaDinamica.addItem(item);
    }

    private ArrayList <String[]> getServicios(){
        rows.add(new String[]{"Escala tecnica", "SET124"});
        return rows;
    }

}