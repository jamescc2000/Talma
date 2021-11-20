package com.example.talma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Dashboard_cliente extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    DatabaseReference BASE_DATOS;
    ActionBar actionBar;
    String mUID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_cliente);

        actionBar = getSupportActionBar();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        BottomNavigationView navigationView = findViewById(R.id.navegacion);
        navigationView.setOnNavigationItemSelectedListener(selectedListener);

    }

    @Override
    protected void onResume() {
        verificarEstadoUsuario();
        super.onResume();
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

    private void verificarEstadoUsuario(){
        //obtenemos al usuario actual
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user != null){
            mUID = user.getUid();

            //Guardar el UID del usuario logeado en shared preferences
            SharedPreferences sp = getSharedPreferences("SP_USER", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("USUARIO_ACTUAL", mUID);
            editor.apply();

        }else {
            startActivity(new Intent(Dashboard_cliente.this, MainActivity.class));
            finish();
        }
    }

    public void onStart() {
        VERIFICACIONSESION();
        super.onStart();
    }

    public void VERIFICACIONSESION(){

        //Si el usuario ha iniciado sesion nos dirige directamente ha esta actividad
        if(firebaseUser != null){

            BASE_DATOS = FirebaseDatabase.getInstance().getReference("clientes");

            /*Obtenemos los datos del usuario*/
            BASE_DATOS.child(firebaseUser.getUid()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    //Si el usuario existe
                    if(snapshot.exists()){

                        //Obtenemos los datos de firebase


                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


            //Caso contrario nos dirige al MainActivity
        }else {
            startActivity(new Intent(Dashboard_cliente.this, MainActivity.class));
            finish();
        }
    }

}