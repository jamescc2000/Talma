package com.example.talma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.talma.Adapters.AdaptadorRsir;
import com.example.talma.Modelos.ModeloRSIR;

import java.util.ArrayList;
import java.util.List;

public class ValidarRsir extends AppCompatActivity {

    RecyclerView rv_rsir_pendiente, rv_rsir_validados;
    AdaptadorRsir adaptadorRsirPendientes, adaptadorRsirValidados;
    List<ModeloRSIR> rsirsPendienteList = new ArrayList<>();
    List<ModeloRSIR> rsirsValidadosList= new ArrayList<>();
    Button btn_rsir_pendientes, btn_rsir_validados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validar_rsir);

        rv_rsir_pendiente = findViewById(R.id.rv_rsir_pendiente);
        rv_rsir_pendiente.setHasFixedSize(true);
        rv_rsir_pendiente.setLayoutManager(new LinearLayoutManager(ValidarRsir.this));

        rv_rsir_validados = findViewById(R.id.rv_rsir_validados);
        rv_rsir_validados.setHasFixedSize(true);
        rv_rsir_validados.setLayoutManager(new LinearLayoutManager(ValidarRsir.this));

        btn_rsir_pendientes = (Button) findViewById(R.id.btn_rsir_pendientes);
        btn_rsir_validados = (Button) findViewById(R.id.btn_rsir_validados);

        btn_rsir_pendientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(rv_rsir_pendiente.getVisibility() == View.GONE){

                    ObtenerRSIRPendientes();
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

                    ObtenerRSIRValidados();
                    rv_rsir_validados.setVisibility(View.VISIBLE);
                    btn_rsir_validados.setText("Ocultar");

                }else if (rv_rsir_validados.getVisibility() == View.VISIBLE){

                    rv_rsir_validados.setVisibility(View.GONE);
                    btn_rsir_validados.setText("RSIR VALIDADOS");

                }
            }
        });

    }

    private void ObtenerRSIRPendientes() {

        rsirsPendienteList.add(new ModeloRSIR("465463dsf4sdf","RISR1654", "Jorge Chavez", "LATAM", "Lima", "Cuzco",
                "avion comercial", "DS84FS6", "Frio aereo", "Jose Luna", "15/11/2021",
                "10:00", "125", "651519", "16/11/2021", "10:00", "178", "52635"));

        rsirsPendienteList.add(new ModeloRSIR("465463dsf4sdf","RISR1654", "Jorge Chavez", "LATAM", "Lima", "Cuzco",
                "avion comercial", "DS84FS6", "Frio aereo", "Jose Luna", "15/11/2021",
                "10:00", "125", "651519", "16/11/2021", "10:00", "178", "52635"));

        rsirsPendienteList.add(new ModeloRSIR("465463dsf4sdf","RISR1654", "Jorge Chavez", "LATAM", "Lima", "Cuzco",
                "avion comercial", "DS84FS6", "Frio aereo", "Jose Luna", "15/11/2021",
                "10:00", "125", "651519", "16/11/2021", "10:00", "178", "52635"));



        adaptadorRsirPendientes = new AdaptadorRsir(ValidarRsir.this, rsirsPendienteList, "cliente");
        rv_rsir_pendiente.setAdapter(adaptadorRsirPendientes);

    }

    private void ObtenerRSIRValidados() {


        rsirsPendienteList.add(new ModeloRSIR("465463dsf4sdf","RISR1654", "Jorge Chavez", "LATAM", "Lima", "Cuzco",
                "avion comercial", "DS84FS6", "Frio aereo", "Jose Luna", "15/11/2021",
                "10:00", "125", "651519", "16/11/2021", "10:00", "178", "52635"));

        rsirsPendienteList.add(new ModeloRSIR("465463dsf4sdf","RISR1654", "Jorge Chavez", "LATAM", "Lima", "Cuzco",
                "avion comercial", "DS84FS6", "Frio aereo", "Jose Luna", "15/11/2021",
                "10:00", "125", "651519", "16/11/2021", "10:00", "178", "52635"));

        rsirsPendienteList.add(new ModeloRSIR("465463dsf4sdf","RISR1654", "Jorge Chavez", "LATAM", "Lima", "Cuzco",
                "avion comercial", "DS84FS6", "Frio aereo", "Jose Luna", "15/11/2021",
                "10:00", "125", "651519", "16/11/2021", "10:00", "178", "52635"));


        adaptadorRsirValidados = new AdaptadorRsir(ValidarRsir.this, rsirsValidadosList, "cliente");
        rv_rsir_validados.setAdapter(adaptadorRsirValidados);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}