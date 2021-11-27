package com.example.talma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.talma.Adapters.AdaptadorRsir;
import com.example.talma.Modelos.ModeloRSIR;
import com.example.talma.RsirEmpleados.RevisarRsir;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ValidarRsir extends AppCompatActivity {

    RecyclerView rv_rsir_pendiente, rv_rsir_validados;
    AdaptadorRsir adaptadorRsirPendientes, adaptadorRsirValidados;
    List<ModeloRSIR> rsirsPendienteList = new ArrayList<>();
    List<ModeloRSIR> rsirsValidadosList= new ArrayList<>();

    Button btn_rsir_pendientes, btn_rsir_validados;

    String email_cliente;

    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    DatabaseReference bd_rsir;
    DatabaseReference bd_clientes;

    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validar_rsir);

        actionBar = getSupportActionBar();
        actionBar.setTitle("Validar RSIR");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);


        rv_rsir_pendiente = findViewById(R.id.rv_rsir_pendiente);
        rv_rsir_pendiente.setHasFixedSize(true);
        rv_rsir_pendiente.setLayoutManager(new LinearLayoutManager(ValidarRsir.this));

        rv_rsir_validados = findViewById(R.id.rv_rsir_validados);
        rv_rsir_validados.setHasFixedSize(true);
        rv_rsir_validados.setLayoutManager(new LinearLayoutManager(ValidarRsir.this));

        btn_rsir_pendientes = (Button) findViewById(R.id.btn_rsir_pendientes);
        btn_rsir_validados = (Button) findViewById(R.id.btn_rsir_validados);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        bd_rsir = FirebaseDatabase.getInstance().getReference("rsir");
        bd_clientes = FirebaseDatabase.getInstance().getReference("clientes");

        ObtenerRSIRPendientes();
        ObtenerRSIRReclamados();

        email_cliente = user.getEmail();

        btn_rsir_pendientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(rv_rsir_pendiente.getVisibility() == View.GONE){

                    rv_rsir_pendiente.setVisibility(View.VISIBLE);
                    btn_rsir_pendientes.setText("Ocultar");

                }else if (rv_rsir_pendiente.getVisibility() == View.VISIBLE){

                    rv_rsir_pendiente.setVisibility(View.GONE);
                    btn_rsir_pendientes.setText("RSIR PENDIENTE");

                }
            }
        });

        btn_rsir_validados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(rv_rsir_validados.getVisibility() == View.GONE){

                    rv_rsir_validados.setVisibility(View.VISIBLE);
                    btn_rsir_validados.setText("Ocultar");

                }else if (rv_rsir_validados.getVisibility() == View.VISIBLE){

                    rv_rsir_validados.setVisibility(View.GONE);
                    btn_rsir_validados.setText("RSIR RECLAMADOS");

                }
            }
        });

    }

    private void ObtenerRSIRPendientes() {

        rsirsPendienteList.clear();

        Query rsirQuery = bd_rsir.orderByChild("emailCliente").equalTo(user.getEmail());

        rsirQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot ds: snapshot.getChildren()) {

                    ModeloRSIR rsir = ds.getValue(ModeloRSIR.class);

                    if(rsir.getEstado().equals("pendiente") || rsir.getEstado().equals("reclamado parcial")){

                        //Obtenemos los datos
                        String codigo_rsir_string = ""+ ds.child("codigoRsir").getValue();
                        String uid_string = ""+ ds.child("uid").getValue();
                        String aeropuerto_string = ""+ ds.child("aeropuerto").getValue();
                        String compañia_string = ""+ ds.child("compania").getValue();
                        String origen_string = ""+ ds.child("origen").getValue();
                        String destino_string = ""+ ds.child("destino").getValue();
                        String email_cliente_string = ""+ ds.child("emailCliente").getValue();
                        String aeronave_string = ""+ ds.child("aeronave").getValue();
                        String matricula_string = ""+ ds.child("matricula").getValue();
                        String area_string = ""+ ds.child("area").getValue();
                        String a_cargo_de_string = ""+ ds.child("aCargoDe").getValue();
                        String fecha_llegada_string = ""+ ds.child("fechaLlegada").getValue();
                        String hora_llegada_string = ""+ ds.child("horaLlegada").getValue();
                        String nvuelo_llegada_string = ""+ ds.child("nvueloLlegada").getValue();
                        String pea_llegada_string = ""+ ds.child("peaLlegada").getValue();
                        String fecha_salida_string = ""+ ds.child("fechaSalida").getValue();
                        String hora_salida_string = ""+ ds.child("horaSalida").getValue();
                        String nvuelo_salida_string = ""+ ds.child("nvueloSalida").getValue();
                        String pea_salida_string = ""+ ds.child("peaSalida").getValue();
                        String estado_string = ""+ ds.child("estado").getValue();

                        //Colocamos los datos
                        rsirsPendienteList.add(new ModeloRSIR(uid_string, codigo_rsir_string, aeropuerto_string, compañia_string, email_cliente_string, origen_string, destino_string,
                                aeronave_string, matricula_string, area_string, a_cargo_de_string, fecha_llegada_string, hora_llegada_string,
                                nvuelo_llegada_string, pea_llegada_string, fecha_salida_string, hora_salida_string, nvuelo_salida_string, pea_salida_string, estado_string));

                        adaptadorRsirPendientes = new AdaptadorRsir(ValidarRsir.this, rsirsPendienteList, "cliente");
                        rv_rsir_pendiente.setAdapter(adaptadorRsirPendientes);

                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private void ObtenerRSIRReclamados() {

        Query rsirQuery = bd_rsir.orderByChild("emailCliente").equalTo(user.getEmail());

        rsirQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                for (DataSnapshot ds: snapshot.getChildren()) {

                    ModeloRSIR rsir = ds.getValue(ModeloRSIR.class);

                    if (rsir.getEstado().equals("reclamado")) {

                        //Obtenemos los datos
                        String codigo_rsir_string = ""+ ds.child("codigoRsir").getValue();
                        String uid_string = ""+ ds.child("uid").getValue();
                        String aeropuerto_string = ""+ ds.child("aeropuerto").getValue();
                        String compañia_string = ""+ ds.child("compania").getValue();
                        String origen_string = ""+ ds.child("origen").getValue();
                        String destino_string = ""+ ds.child("destino").getValue();
                        String email_cliente_string = ""+ ds.child("emailCliente").getValue();
                        String aeronave_string = ""+ ds.child("aeronave").getValue();
                        String matricula_string = ""+ ds.child("matricula").getValue();
                        String area_string = ""+ ds.child("area").getValue();
                        String a_cargo_de_string = ""+ ds.child("aCargoDe").getValue();
                        String fecha_llegada_string = ""+ ds.child("fechaLlegada").getValue();
                        String hora_llegada_string = ""+ ds.child("horaLlegada").getValue();
                        String nvuelo_llegada_string = ""+ ds.child("nvueloLlegada").getValue();
                        String pea_llegada_string = ""+ ds.child("peaLlegada").getValue();
                        String fecha_salida_string = ""+ ds.child("fechaSalida").getValue();
                        String hora_salida_string = ""+ ds.child("horaSalida").getValue();
                        String nvuelo_salida_string = ""+ ds.child("nvueloSalida").getValue();
                        String pea_salida_string = ""+ ds.child("peaSalida").getValue();
                        String estado_string = ""+ ds.child("estado").getValue();

                        //Colocamos los datos
                        rsirsValidadosList.add(new ModeloRSIR(uid_string,codigo_rsir_string, aeropuerto_string, compañia_string, email_cliente_string,origen_string, destino_string,
                                aeronave_string, matricula_string, area_string, a_cargo_de_string, fecha_llegada_string, hora_llegada_string,
                                nvuelo_llegada_string, pea_llegada_string, fecha_salida_string, hora_salida_string, nvuelo_salida_string, pea_salida_string,estado_string));

                        adaptadorRsirValidados = new AdaptadorRsir(ValidarRsir.this, rsirsValidadosList,"cliente");
                        adaptadorRsirValidados.notifyDataSetChanged();
                        rv_rsir_validados.setAdapter(adaptadorRsirValidados);

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

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

}