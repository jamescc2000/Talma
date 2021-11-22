package com.example.talma;

import androidx.annotation.NonNull;
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

import com.example.talma.Adapters.AdaptadorRsir;
import com.example.talma.Modelos.ModeloCliente;
import com.example.talma.Modelos.ModeloRSIR;
import com.example.talma.RsirEmpleados.RevisarRsir;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginClientes extends AppCompatActivity {

    Button btn_Ingresar;
    EditText et_Login_cliente, et_password;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    DatabaseReference bd_clientes;
    Dialog dialog;

    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_cliente);

        actionBar = getSupportActionBar();
        actionBar.hide();
        et_Login_cliente = (EditText) findViewById(R.id.et_Login_cliente);
        et_password = (EditText) findViewById(R.id. et_password);
        btn_Ingresar = (Button) findViewById(R.id.btn_Ingresar);

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(LoginClientes.this);/*Inicializamos el progressDialog*/
        dialog = new Dialog(LoginClientes.this); /*Inicializamos el Dialog*/
        bd_clientes = FirebaseDatabase.getInstance().getReference("clientes");


        btn_Ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email_String = et_Login_cliente.getText().toString();
                String password_String = et_password.getText().toString();

                if (!Patterns.EMAIL_ADDRESS.matcher(email_String).matches()) {
                    et_Login_cliente.setError("Email no valido");
                    et_Login_cliente.setFocusable(true);
                } else if (password_String.length() < 6) {
                    et_password.setError("La contraseña debe tener mas de 6 caracteres");
                    et_password.setFocusable(true);
                } else {

                        LOGINUSUARIO(email_String, password_String);
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


                                startActivity(new Intent(LoginClientes.this, Dashboard_cliente.class));
                                Toast.makeText(LoginClientes.this, "Hola! Bienvenido(a) "+ user.getEmail(), Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(LoginClientes.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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