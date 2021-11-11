package com.example.talma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.talma.Adapters.AdaptadorRsir;
import com.example.talma.Modelos.ModeloRSIR;

import java.util.ArrayList;
import java.util.List;

public class RevisarRsir extends AppCompatActivity {

    RecyclerView recyclerView;
    AdaptadorRsir adaptadorRsir;
    List<ModeloRSIR> rsirsList;


    public RevisarRsir() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revisar_rsir);

        recyclerView = findViewById(R.id.rv_servicios);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(RevisarRsir.this));

        rsirsList = new ArrayList<>();

        //Obtener todos los rsir
        ObtenerTodosRSIR();
    }

    private void ObtenerTodosRSIR() {


        rsirsList.clear();
        rsirsList.add(new ModeloRSIR("RISR1654", "Jorge Chavez", "LATAM", "Lima", "Cuzco",
                "avion comercial", "DS84FS6", "Frio aereo", "Jose Luna", "15/11/2021",
                "10:00", "125", "651519", "16/11/2021", "10:00", "178", "52635"));

        rsirsList.add(new ModeloRSIR("RISR1654", "Jorge Chavez", "LATAM", "Lima", "Cuzco",
                "avion comercial", "DS84FS6", "Frio aereo", "Jose Luna", "15/11/2021",
                "10:00", "125", "651519", "16/11/2021", "10:00", "178", "52635"));

        rsirsList.add(new ModeloRSIR("RISR1654", "Jorge Chavez", "LATAM", "Lima", "Cuzco",
                "avion comercial", "DS84FS6", "Frio aereo", "Jose Luna", "15/11/2021",
                "10:00", "125", "651519", "16/11/2021", "10:00", "178", "52635"));

        adaptadorRsir = new AdaptadorRsir(RevisarRsir.this, rsirsList);
        recyclerView.setAdapter(adaptadorRsir);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}