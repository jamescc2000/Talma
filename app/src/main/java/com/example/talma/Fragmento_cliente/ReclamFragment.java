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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReclamFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReclamFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView recyclerView;
    AdaptadorReclamo adaptadorReclamo;
    List<ModeloReclamo> modeloReclamo;

    public ReclamFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReclamFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ReclamFragment newInstance(String param1, String param2) {
        ReclamFragment fragment = new ReclamFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_serv, container, false);

        recyclerView = view.findViewById(R.id.rvListaRecl);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        modeloReclamo = new ArrayList<>();

        ObtenerReclamos();
        return view;
    }

    private void ObtenerReclamos() {
        modeloReclamo.clear();
        modeloReclamo.add(new ModeloReclamo("121654","Reclamo01","RISR1654","Frío áreo","16/11/2021", "Aceptado"));
        modeloReclamo.add(new ModeloReclamo("121655","Reclamo02","RISR1655","Patio de equipaje","16/11/2021", "Aceptado"));
        modeloReclamo.add(new ModeloReclamo("121656","Reclamo03","RISR1484","Counter","15/11/2021", "En espera"));
        modeloReclamo.add(new ModeloReclamo("121657","Reclamo04","RISR1674","Counter","14/11/2021", "En espera"));
        modeloReclamo.add(new ModeloReclamo("121658","Reclamo05","RISR1784","Frío áreo","13/11/2021", "Rechazado"));

        adaptadorReclamo = new AdaptadorReclamo(getActivity(), modeloReclamo, "empleado");
        recyclerView.setAdapter(adaptadorReclamo);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}