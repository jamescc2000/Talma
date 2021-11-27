package com.example.talma.Fragmento_cliente;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ReclamFragment extends Fragment {

    RecyclerView recyclerView;
    AdaptadorReclamo adaptadorReclamo;
    List<ModeloReclamo> ListmodeloReclamo;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    DatabaseReference bd_reclamo;
    ActionBar actionBar;

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

        recyclerView = (RecyclerView) view.findViewById(R.id.rvListaRecl);


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ListmodeloReclamo = new ArrayList<>();
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        bd_reclamo = FirebaseDatabase.getInstance().getReference("reclamo");
        ObtenerReclamos();
        return view;
    }

    private void ObtenerReclamos() {

        ListmodeloReclamo.clear();

        bd_reclamo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds: snapshot.getChildren()){
                    ModeloReclamo reclamo = ds.getValue(ModeloReclamo.class);

                    if (reclamo.getUid().equals(user.getUid())){
                        String codigo_reclamo_string = ""+ ds.child("codigoReclamo").getValue();
                        String rsir_string = ""+ ds.child("codigoRsir").getValue();
                        String area_string = ""+ ds.child("codigoServicio").getValue();
                        String fecha_string = ""+ ds.child("fechaRegistro").getValue();;
                        String estado_string =""+ ds.child("estado").getValue();

                        ListmodeloReclamo.add(new ModeloReclamo(codigo_reclamo_string, user.getUid(), rsir_string,area_string,fecha_string,estado_string,""));
                        adaptadorReclamo = new AdaptadorReclamo(getContext(), ListmodeloReclamo,"empleado");
                        adaptadorReclamo.notifyDataSetChanged();
                        recyclerView.setAdapter(adaptadorReclamo);
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