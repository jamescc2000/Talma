package com.example.talma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.talma.Fragmentos_empleados.InicioFragment;
import com.example.talma.Fragmentos_empleados.NotificacionesFragment;
import com.example.talma.Fragmentos_empleados.PerfilFragment;
import com.example.talma.Fragmentos_empleados.RsireFragment;
import com.example.talma.Fragmentos_empleados.UsuariosFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class Dashboard_empleados extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    DatabaseReference BASE_DATOS;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_empleados);

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
                        case R.id.nav_home:
                            //Transaccion del fragmento home

                            actionBar.setTitle("Inicio");

                            InicioFragment fragment1 = new InicioFragment();
                            FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
                            ft1.replace(R.id.content, fragment1, "");
                            ft1.addToBackStack(null);
                            ft1.commit();
                            return true;

                        case R.id.nav_perfil:
                            //Transaccion del fragmento perfil

                            actionBar.setTitle("Perfil");

                            PerfilFragment fragment2 = new PerfilFragment();
                            FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
                            ft2.replace(R.id.content, fragment2, "");
                            ft2.addToBackStack(null);
                            ft2.commit();
                            return true;
                        case R.id.nav_usuarios:
                            //Transaccion del fragmento usuarios
                            actionBar.setTitle("Usuarios");
                            UsuariosFragment fragment3 = new UsuariosFragment();
                            FragmentTransaction ft3 = getSupportFragmentManager().beginTransaction();
                            ft3.replace(R.id.content, fragment3, "");
                            ft3.addToBackStack(null);
                            ft3.commit();
                            return true;

                        case R.id.nav_RSIRe:
                            //Transaccion del fragmento usuarios
                            actionBar.setTitle("Chats");
                            RsireFragment fragment4 = new RsireFragment();
                            FragmentTransaction ft4 = getSupportFragmentManager().beginTransaction();
                            ft4.replace(R.id.content, fragment4, "");
                            ft4.addToBackStack(null);
                            ft4.commit();
                            return true;
                        case R.id.nav_notificaciones:
                            //Transaccion del fragmento usuarios
                            actionBar.setTitle("Notificaciones");
                            NotificacionesFragment fragment5 = new NotificacionesFragment();
                            FragmentTransaction ft5 = getSupportFragmentManager().beginTransaction();
                            ft5.replace(R.id.content, fragment5, "");
                            ft5.addToBackStack(null);
                            ft5.commit();
                            return true;

                    }

                    return false;
                }
            };
}