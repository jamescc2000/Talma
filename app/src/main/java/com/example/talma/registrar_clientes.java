package com.example.talma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.talma.RsirEmpleados.RegistrarRsire;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class registrar_clientes extends AppCompatActivity {

    private EditText et_email, et_id_cliente, et_contraseña,et_aerolinea, et_fecha_registro ;
    private Button btn_fecha_registro_clientes, btn_registrar;

    private ProgressDialog progressDialog;
    DatePickerDialog datePickerDialog;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_clientes);

        et_email = (EditText) findViewById(R.id.et_email);
        et_id_cliente = (EditText) findViewById(R.id.et_id_cliente);
        et_contraseña = (EditText) findViewById(R.id.et_contraseña);
        et_aerolinea = (EditText) findViewById(R.id.et_aerolinea);
        btn_fecha_registro_clientes = (Button) findViewById(R.id.btn_fecha_registro_clientes);
        btn_registrar = (Button) findViewById(R.id.btn_registrar);


        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(registrar_clientes.this);

        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email_string = et_email.getText().toString();
                String password_string = et_contraseña.getText().toString();

                //Validacion del email y contraseña
                if(!Patterns.EMAIL_ADDRESS.matcher(email_string).matches()){
                    et_email.setError("Email no valido");
                    et_email.setFocusable(true);
                }else if(password_string.length() < 6){
                    et_contraseña.setError("La contraseña debe tener mas de 6 caracteres");
                    et_contraseña.setFocusable(true);
                }else {
                    RegistarClientes(email_string, password_string);
                }
            }

        });

    }

    /*Metodo para registrar un cliente*/
    public void RegistarClientes(String Email, String Password){

        progressDialog.setTitle("Registrando");
        progressDialog.setMessage("Espere por favor...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(Email, Password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        /*Si el registro es exitoso*/
                        if (task.isSuccessful()) {
                            progressDialog.dismiss(); // El progresss se cierra
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            //Aqui van los datos que queremos registrar
                            assert user != null; //Afirmamos que el usuario no sea nulo
                            String uid_String = user.getUid();
                            String id_String = et_id_cliente.getText().toString();
                            String email_String = et_email.getText().toString();
                            String password_String = et_contraseña.getText().toString();
                            String aerolinea_string = et_aerolinea.getText().toString();
                            String fechRegistro_string = et_fecha_registro.getText().toString();


                            /*Creamos un Hashmap para mandar los datos a firebase*/
                            Map<String, Object> datosUser = new HashMap<>();

                            datosUser.put("uid", uid_String);
                            datosUser.put("id", id_String);
                            datosUser.put("email", email_String);
                            datosUser.put("password", password_String);
                            datosUser.put("aerolínea", aerolinea_string);
                            datosUser.put("fechaRegistro", fechRegistro_string);


                            //Inicializamos la instancia a la base de datos
                            FirebaseDatabase database = FirebaseDatabase.getInstance();

                            //Creamos la base de datos
                            DatabaseReference reference = database.getReference("clientes"); // Este es el nomber de la tabla
                            reference.child(id_String).setValue(datosUser);
                            Toast.makeText(registrar_clientes.this, "Registro exitoso", Toast.LENGTH_SHORT).show();

                            //Una vez registrado, pasamos a la pantalla de dashboard
                            startActivity(new Intent(registrar_clientes.this, Dashboard_empleados.class));
                        } else {
                            progressDialog.dismiss(); // El progresss se cierra
                            Toast.makeText(registrar_clientes.this, "Algo ha salido mal, vuelva a intentarlo", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss(); // El progresss se cierra
                Toast.makeText(registrar_clientes.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        btn_fecha_registro_clientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String fecha = makeDateString(dayOfMonth, month, year);
                        btn_fecha_registro_clientes.setText(fecha);
                    }
                };
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int moth = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int style  = AlertDialog.THEME_HOLO_LIGHT;

                datePickerDialog = new DatePickerDialog(registrar_clientes.this, style, dateSetListener, year, moth, day);
                datePickerDialog.show();

            }
        });

    }

    private String makeDateString(int dayOfMonth, int month, int year){
        return dayOfMonth + "/" + month + "/" + year;
    }

    //Habilitamos la accion para retroceder
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }


}