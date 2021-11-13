package com.example.talma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.talma.Fragmento_cliente.HomeFragment;
import com.example.talma.Fragmento_cliente.ServFragment;
import com.example.talma.Fragmento_cliente.PedFragment;
import com.example.talma.Fragmento_cliente.FactFragment;
import com.example.talma.Fragmento_cliente.ReclamFragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class Dashboard_cliente extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    DatabaseReference BASE_DATOS;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_cliente);

        actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);


        BottomNavigationView navigationView = findViewById(R.id.navegacion);
        navigationView.setOnNavigationItemSelectedListener(selectedListener);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener selectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    //Manejamos los clicks de los items
                    switch (item.getItemId()){
                        case R.id.nav_serv:
                            //Transaccion del fragmento home

                            actionBar.setTitle("Servicios");

                            ServFragment fragment1 = new ServFragment();
                            FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
                            ft1.replace(R.id.content, fragment1, "");
                            ft1.addToBackStack(null);
                            ft1.commit();
                            return true;

                        case R.id.nav_pedidos:
                            //Transaccion del fragmento perfil

                            actionBar.setTitle("Pedir");

                            PedFragment fragment2 = new PedFragment();
                            FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
                            ft2.replace(R.id.content, fragment2, "");
                            ft2.addToBackStack(null);
                            ft2.commit();
                            return true;
                        case R.id.nav_reclamos:
                            //Transaccion del fragmento usuarios
                            actionBar.setTitle("Reclamos");
                            ReclamFragment fragment3 = new ReclamFragment();
                            FragmentTransaction ft3 = getSupportFragmentManager().beginTransaction();
                            ft3.replace(R.id.content, fragment3, "");
                            ft3.addToBackStack(null);
                            ft3.commit();
                            return true;
                            /*

                        case R.id.nav_facturas:
                            //Transaccion del fragmento usuarios
                            actionBar.setTitle("RSIRe");
                            FactFragment fragment4 = new FactFragment();
                            FragmentTransaction ft4 = getSupportFragmentManager().beginTransaction();
                            ft4.replace(R.id.content, fragment4, "");
                            ft4.addToBackStack(null);
                            ft4.commit();
                            return true;
                        case R.id.nav_reclamos:
                            //Transaccion del fragmento usuarios
                            actionBar.setTitle("Notificaciones");
                            ReclamFragment fragment5 = new ReclamFragment();
                            FragmentTransaction ft5 = getSupportFragmentManager().beginTransaction();
                            ft5.replace(R.id.content, fragment5, "");
                            ft5.addToBackStack(null);
                            ft5.commit();
                            return true; */

                    }

                    return false;
                }
            };
}