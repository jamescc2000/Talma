package com.example.talma.Fragmentos_empleados;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.talma.Adapters.AdaptadorListaServicios;
import com.example.talma.MainActivity;
import com.example.talma.Modelos.ModeloServicio;
import com.example.talma.R;
import com.example.talma.ValidarServicios;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;


public class PerfilFragment extends Fragment {

    Button btn_cerrar_sesion;
    PieChartView graficaCircular;
    BarChart graficaBarras;

    FirebaseAuth firebaseAuth;
    Integer cant_servicios_pendientes;
    Integer cant_serivcios_registrados;
    Integer cant_servicios_confirmados;
    Integer cant_servicios_reclamados;
    
    String [] servicios = new String []{
            "Pendientes", "Confirmados", "Regsitrados", "Rclamados"
    };

    Integer[] cant_servicios;
    int [] colors = new int []{
            Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW
    };



    FirebaseUser user;
    DatabaseReference bd_rsir;
    DatabaseReference bd_servicios;


    public PerfilFragment() {
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
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        btn_cerrar_sesion = (Button) view.findViewById(R.id.btn_cerrar_sesion);
        //graficaCircular = (PieChartView) view.findViewById(R.id.graficaCircular);
        //graficaBarras = (BarChart) view.findViewById(R.id.graficaBarras);

        firebaseAuth = FirebaseAuth.getInstance();

        bd_servicios = FirebaseDatabase.getInstance().getReference("servicios");
        bd_rsir = FirebaseDatabase.getInstance().getReference("rsir");

        /*
        ObtenerServiciosPendientes();
        ObtenerServiciosReclamados();
        ObtenerServiciosConfirmados();
        ObtenerServiciosRegistrados();


        cant_servicios[0] = cant_servicios_pendientes;
        cant_servicios[1] = cant_servicios_confirmados;
        cant_servicios[2] = cant_serivcios_registrados;
        cant_servicios[3] = cant_servicios_reclamados;
*/


        btn_cerrar_sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                Toast.makeText(getActivity(), "Ha cerrado sesion", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), MainActivity.class));
            }
        });
        return view;
    }

    private Chart getSameChart (Chart chart, String descripcion, int textColor, int background, int animacionY){

        chart.getDescription().setText(descripcion);
        chart.getDescription().setTextSize(18);
        chart.setBackgroundColor(background);
        chart.animateY(animacionY);

        return  chart;
    }

    private ArrayList<BarEntry> getBarEntries(){

        ArrayList<BarEntry> entries = new ArrayList<>();
        for (int i=0; i < servicios.length; i++)
            entries.add(new BarEntry(i, cant_servicios[i]));

        return entries;

    }

    private ArrayList<PieEntry> getPieEntries(){

        ArrayList<PieEntry> entries = new ArrayList<>();
        for (int i=0; i < servicios.length; i++)
            entries.add(new PieEntry(cant_servicios[i]));

        return entries;

    }

    private void ejeX (XAxis axis){
        axis.setGranularityEnabled(true);
        axis.setPosition(XAxis.XAxisPosition.BOTTOM);
        axis.setValueFormatter(new IndexAxisValueFormatter(servicios));

    }

    private void ejeIzquierdo (YAxis axis){
        axis.setSpaceTop(100);
        axis.setAxisMinimum(0);

    }

    private void ejeDerecho (YAxis axis){
        axis.setEnabled(false);

    }

    private void crearChart (){

        graficaBarras = (BarChart) getSameChart(graficaBarras, "Servicios", Color.BLACK, Color.WHITE, 3000);
        graficaBarras.setDrawGridBackground(true);
        graficaBarras.setDrawBarShadow(true);

        ejeX(graficaBarras.getXAxis());
        ejeIzquierdo(graficaBarras.getAxisLeft());
        ejeDerecho(graficaBarras.getAxisRight());



    }


    private void ObtenerServiciosPendientes(){

        Query servicioPendientesQuery = bd_servicios.orderByChild("estado").equalTo("pendiente");

        servicioPendientesQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot ds: snapshot.getChildren()){
                    cant_servicios_pendientes++;

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void ObtenerServiciosRegistrados(){

        Query servicioPendientesQuery = bd_servicios.orderByChild("estado").equalTo("registrado");

        servicioPendientesQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot ds: snapshot.getChildren()){
                    cant_serivcios_registrados++;

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void ObtenerServiciosReclamados(){

        Query servicioPendientesQuery = bd_servicios.orderByChild("estado").equalTo("reclamado");

        servicioPendientesQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot ds: snapshot.getChildren()){
                    cant_servicios_reclamados++;

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void ObtenerServiciosConfirmados(){

        Query servicioPendientesQuery = bd_servicios.orderByChild("estado").equalTo("confirmado");

        servicioPendientesQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot ds: snapshot.getChildren()){
                    cant_servicios_confirmados++;

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


}