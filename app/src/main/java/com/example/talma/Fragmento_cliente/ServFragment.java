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
import com.example.talma.Modelos.ModeloRSIR;
import com.example.talma.Modelos.ModeloReclamo;
import com.example.talma.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

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
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    DatabaseReference bd_serv;

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

        //ObtenerRSIR();
        return view;

    }

    private void ObtenerRSIR() {

        modeloRSIRList.clear();

        bd_serv.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds: snapshot.getChildren()){
                    ModeloRSIR rsir = ds.getValue(ModeloRSIR.class);

                    if (rsir.getEmailCliente().equals(user.getEmail())){
                        String rsir_string = ""+ ds.child("codigoRsir").getValue();
                        String aeropuerto_string = ""+ ds.child("aeropuerto").getValue();
                        String compania_string = ""+ ds.child("compania").getValue();
                        String email_string = ""+ ds.child("emailCliente").getValue();
                        String origen_string = ""+ ds.child("origen").getValue();
                        String destino_string = ""+ ds.child("destino").getValue();
                        String aeronave_string = ""+ ds.child("aeronave").getValue();
                        String matricula_string = ""+ ds.child("matricula").getValue();
                        String area_string = ""+ ds.child("area").getValue();
                        String cargo_string = ""+ ds.child("aCargoDe").getValue();
                        String fechaL_string = ""+ ds.child("fechaLlegada").getValue();
                        String hora_string = ""+ ds.child("horaLlegada").getValue();
                        String nvuelo_string = ""+ ds.child("nVueloLlegada").getValue();
                        String pea_string = ""+ ds.child("peaLlegada").getValue();
                        String fechaS_string = ""+ ds.child("fechaSalida").getValue();
                        String horaS_string = ""+ ds.child("horaSalida").getValue();
                        String nvueloS_string = ""+ ds.child("nVueloSalida").getValue();
                        String peaS_string = ""+ ds.child("peaSalida").getValue();
                        String estado_string =""+ ds.child("estado").getValue();
                        String peso_string =""+ ds.child("peso").getValue();

                        modeloRSIRList.add(new ModeloRSIR(user.getUid(), rsir_string,aeropuerto_string,compania_string,email_string,origen_string,destino_string,
                                aeronave_string,matricula_string,area_string,cargo_string,fechaL_string,hora_string,nvuelo_string,pea_string,
                                fechaS_string,horaS_string,nvueloS_string,peaS_string,estado_string,peso_string));
                        adaptadorRsir = new AdaptadorRsir(getContext(), modeloRSIRList,"empleado");
                        adaptadorRsir.notifyDataSetChanged();
                        recyclerView.setAdapter(adaptadorRsir);
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}