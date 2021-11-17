package com.example.talma;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginEmpleados extends AppCompatActivity {

    Button btn_Ingresar;
    EditText et_login_emplados, et_contraseña_empleados;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_empleados);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null; // Afirmammos que el titulo no es nulo
        actionBar.setTitle("Iniciar Sesion");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        et_login_emplados = (EditText) findViewById(R.id.et_login_emplados);
        et_contraseña_empleados = (EditText) findViewById(R.id.et_contraseña_empleados);
        btn_Ingresar = (Button) findViewById(R.id.btn_Ingresar);

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(LoginEmpleados.this);/*Inicializamos el progressDialog*/
        dialog = new Dialog(LoginEmpleados.this); /*Inicializamos el Dialog*/


        btn_Ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emai_String = et_login_emplados.getText().toString();
                String password_String = et_contraseña_empleados.getText().toString();

                if(!Patterns.EMAIL_ADDRESS.matcher(emai_String).matches()){
                    et_login_emplados.setError("Email no valido");
                    et_login_emplados.setFocusable(true);
                }else if(password_String.length() < 6){
                    et_contraseña_empleados.setError("La contraseña debe tener mas de 6 caracteres");
                    et_contraseña_empleados.setFocusable(true);
                }else {

                    LOGINUSUARIO(emai_String, password_String);
                }
            }
        });
    }

    public void LOGINUSUARIO(String email, String password){
        progressDialog.setTitle("Ingresando");
        progressDialog.setMessage("Espere por favor...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //Si se inicia correctamente entonces
                        if(task.isSuccessful()){
                            progressDialog.dismiss(); // El progresss se cierra
                            FirebaseUser user = firebaseAuth.getCurrentUser();

                            startActivity(new Intent(LoginEmpleados.this, Dashboard_empleados.class));
                            assert user != null;
                            Toast.makeText(LoginEmpleados.this, "Hola! Bienvenido(a) "+ user.getEmail(), Toast.LENGTH_SHORT).show();
                            finish();

                        }else {
                            progressDialog.dismiss();
                            Dialog_Fail_Login();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(LoginEmpleados.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*Metodo del Dialog perzonalozado*/
    public void Dialog_Fail_Login(){

        Button ok_fail_login;

        dialog.setContentView(R.layout.fail_login);

        ok_fail_login = dialog.findViewById(R.id.ok_fail_login);
        ok_fail_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.setCancelable(false); /*Al presionar fuera de la animacion, esta seguira mostrandose*/
        dialog.show();
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

}