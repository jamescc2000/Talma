package com.example.talma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Dashboard_empleados extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    DatabaseReference BASE_DATOS;
    ActionBar actionBar;

    String mUID;
    String id_string,nombres_string, apellidos_string, correo_string, fechaNac_string, dni_string, area_String, tipo_cargo_String;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_empleados);

        actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();


        BottomNavigationView navigationView = findViewById(R.id.navegacion);
        navigationView.setOnNavigationItemSelectedListener(selectedListener);

        //RsireFragment por default
        actionBar.setTitle("RSIR");
        RsireFragment fragment1 = new RsireFragment();
        FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
        ft1.replace(R.id.content, fragment1, "");
        ft1.commit();

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
                            actionBar.setTitle("RSIRe");
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
            startActivity(new Intent(Dashboard_empleados.this, MainActivity.class));
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void onStart() {
        VERIFICACIONSESION();
        super.onStart();
    }

    public void VERIFICACIONSESION(){

        //Si el usuario ha iniciado sesion nos dirige directamente ha esta actividad
        if(firebaseUser != null){

            BASE_DATOS = FirebaseDatabase.getInstance().getReference("empleados");

            /*Obtenemos los datos del usuario*/
            BASE_DATOS.child(firebaseUser.getUid()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    //Si el usuario existe
                    if(snapshot.exists()){

                        //Obtenemos los datos de firebase
                        nombres_string = ""+ snapshot.child("nombres").getValue();
                        apellidos_string = ""+ snapshot.child("apellidos").getValue();
                        correo_string = ""+ snapshot.child("email").getValue();
                        fechaNac_string = ""+ snapshot.child("fechaNac").getValue();
                        id_string = ""+ snapshot.child("id").getValue();
                        dni_string = ""+snapshot.child("dni").getValue();
                        area_String = ""+snapshot.child("area").getValue();
                        tipo_cargo_String = ""+snapshot.child("tipoCargo").getValue();

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


            //Caso contrario nos dirige al MainActivity
        }else {
            startActivity(new Intent(Dashboard_empleados.this, MainActivity.class));
            finish();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

}