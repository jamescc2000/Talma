package com.example.talma.Fragmento_cliente;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.talma.Adapters.AdaptadorReclamo;
import com.example.talma.Adapters.AdaptadorRsir;
import com.example.talma.Modelos.ModeloReclamo;
import com.example.talma.R;

import java.util.ArrayList;
import java.util.List;

public class ReclamFragment extends Fragment {

    RecyclerView recyclerView;
    AdaptadorReclamo adaptadorReclamo;
    List<ModeloReclamo> modeloReclamo;

    public ReclamFragment() {
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
        View view=  inflater.inflate(R.layout.fragment_reclam, container, false);

        recyclerView = view.findViewById(R.id.rvListaRecl);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        modeloReclamo = new ArrayList<>();

        ObtenerReclamos();
        return view;
    }

    private void ObtenerReclamos() {
        modeloReclamo.clear();
        modeloReclamo.add(new ModeloReclamo("121654","RISR1654","Frío áreo","16/11/2021", "Aceptado",""));
        modeloReclamo.add(new ModeloReclamo("121655","RISR1655","Patio de equipaje","16/11/2021", "Aceptado",""));
        modeloReclamo.add(new ModeloReclamo("121656","RISR1484","Counter","15/11/2021", "En espera",""));
        modeloReclamo.add(new ModeloReclamo("121657","RISR1674","Counter","14/11/2021", "En espera",""));
        modeloReclamo.add(new ModeloReclamo("121658","RISR1784","Frío áreo","13/11/2021", "Rechazado",""));

        adaptadorReclamo = new AdaptadorReclamo(getActivity(), modeloReclamo, "empleado");
        recyclerView.setAdapter(adaptadorReclamo);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}