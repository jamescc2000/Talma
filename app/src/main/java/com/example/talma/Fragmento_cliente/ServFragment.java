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

import com.example.talma.Adapters.AdaptadorRsir;
import com.example.talma.Modelos.ModeloRSIR;
import com.example.talma.R;
import com.example.talma.RevisarRsir;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ServFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ServFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    RecyclerView recyclerView;
    AdaptadorRsir adaptadorRsir;
    List<ModeloRSIR> modeloRSIRList;

    public ServFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ServFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ServFragment newInstance(String param1, String param2) {
        ServFragment fragment = new ServFragment();
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

        recyclerView = view.findViewById(R.id.rvListaRsir);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        modeloRSIRList = new ArrayList<>();

        ObtenerRSIR();
        return view;

    }
    private void ObtenerRSIR() {


        modeloRSIRList.clear();
        modeloRSIRList.add(new ModeloRSIR("RISR1654", "Jorge Chavez", "LATAM", "Lima", "Cuzco",
                "avion comercial", "DS84FS6", "Frio aereo", "Jose Luna", "15/11/2021",
                "10:00", "125", "651519", "16/11/2021", "10:00", "178", "52635"));

        modeloRSIRList.add(new ModeloRSIR("RISR1654", "Jorge Chavez", "LATAM", "Lima", "Cuzco",
                "avion comercial", "DS84FS6", "Frio aereo", "Jose Luna", "15/11/2021",
                "10:00", "125", "651519", "16/11/2021", "10:00", "178", "52635"));

        modeloRSIRList.add(new ModeloRSIR("RISR1654", "Jorge Chavez", "LATAM", "Lima", "Cuzco",
                "avion comercial", "DS84FS6", "Frio aereo", "Jose Luna", "15/11/2021",
                "10:00", "125", "651519", "16/11/2021", "10:00", "178", "52635"));

        adaptadorRsir = new AdaptadorRsir(getActivity(), modeloRSIRList);
        recyclerView.setAdapter(adaptadorRsir);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}