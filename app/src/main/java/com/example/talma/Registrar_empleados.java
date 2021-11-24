package com.example.talma;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.talma.RsirEmpleados.RegistrarRsire;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Registrar_empleados extends AppCompatActivity {

    private EditText et_email, et_id_empleado, et_contraseña,et_nombres, et_apellidos,et_dni,et_fecha_nacimiento ;
    private Spinner sp_tipo_cargo, sp_area;
    private Button btn_fecha_registro, btn_registrar;

    int hora, minuto;

    private ProgressDialog progressDialog;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_empleados);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null; // Afirmammos que el titulo no es nulo
        actionBar.setTitle("Registro de Empleados");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        et_email = (EditText) findViewById(R.id.et_email);
        et_id_empleado = (EditText) findViewById(R.id.et_id_empleado);
        et_contraseña = (EditText) findViewById(R.id.et_contraseña);
        et_nombres = (EditText) findViewById(R.id.et_nombres);
        et_apellidos = (EditText) findViewById(R.id.et_apellidos);
        et_dni = (EditText) findViewById(R.id.et_dni);
        sp_tipo_cargo = (Spinner) findViewById(R.id.sp_tipo_cargo);
        sp_area= (Spinner) findViewById(R.id.sp_area);
        btn_fecha_registro = (Button) findViewById(R.id.btn_fecha_registro);
        btn_registrar = (Button) findViewById(R.id.btn_registrar);


        String [] opciones_tipo_cargo = {"Encargado de area","Encargado de facturacion","Coordinador de operaciones", ""};
        ArrayAdapter<String> adapter_tipo_cargo = new ArrayAdapter<String>(Registrar_empleados.this, R.layout.spinner,opciones_tipo_cargo);
        sp_tipo_cargo.setAdapter(adapter_tipo_cargo);

        String [] opciones_areas = {"Patio de Equipaje", "Frio Aereo", "Counter"};
        ArrayAdapter<String> adapter_areas = new ArrayAdapter<String>(Registrar_empleados.this, R.layout.spinner,opciones_areas);
        sp_area.setAdapter(adapter_areas);

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(Registrar_empleados.this);

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
                    RegistarEmpleados(email_string, password_string);
                }
            }
        });

    }

    /*Metodo para registrar un usuario*/
    public void RegistarEmpleados(String Email, String Password){

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
                            String id_String = et_id_empleado.getText().toString();
                            String email_String = et_email.getText().toString();
                            String password_String = et_contraseña.getText().toString();
                            String nombres_string = et_nombres.getText().toString();
                            String apellidos_String = et_apellidos.getText().toString();
                            String dni_String = et_dni.getText().toString();

                            String tipo_cargo_string = sp_tipo_cargo.getSelectedItem().toString();
                            String area_string = sp_area.getSelectedItem().toString();

                            /*Creamos un Hashmap para mandar los datos a firebase*/
                            Map<String, Object> datosUser = new HashMap<>();

                            datosUser.put("uid", uid_String);
                            datosUser.put("id", id_String);
                            datosUser.put("email", email_String);
                            datosUser.put("password", password_String);
                            datosUser.put("nombres", nombres_string);
                            datosUser.put("apellidos", apellidos_String);
                            datosUser.put("dni", dni_String);
                            datosUser.put("tipoCargo", tipo_cargo_string);
                            datosUser.put("area", area_string);

                            //Inicializamos la instancia a la base de datos
                            FirebaseDatabase database = FirebaseDatabase.getInstance();

                            //Creamos la base de datos
                            DatabaseReference reference = database.getReference("empleados"); // Este es el nomber de la tabla
                            reference.child(id_String).setValue(datosUser);
                            Toast.makeText(Registrar_empleados.this, "Registro exitoso", Toast.LENGTH_SHORT).show();

                            //Una vez registrado, pasamos a la pantalla de dashboard
                            startActivity(new Intent(Registrar_empleados.this, Dashboard_empleados.class));
                        } else {
                            progressDialog.dismiss(); // El progresss se cierra
                            Toast.makeText(Registrar_empleados.this, "Algo ha salido mal, vuelva a intentarlo", Toast.LENGTH_SHORT).show();
                        }
                     }
                    }).addOnFailureListener(new OnFailureListener() {
                                               @Override
                                               public void onFailure(@NonNull Exception e) {
                                                   progressDialog.dismiss(); // El progresss se cierra
                                                   Toast.makeText(Registrar_empleados.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                               }
                                           });

        btn_fecha_registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        hora = hourOfDay;
                        minuto = minute;
                        btn_fecha_registro.setText(String.format(Locale.getDefault(), "%02d:%02d", hora, minuto));
                    }};
                int style = AlertDialog.THEME_HOLO_LIGHT;

                TimePickerDialog timePickerDialog = new TimePickerDialog(Registrar_empleados.this, style,listener, hora, minuto, true);
                timePickerDialog.show();

            }
        });

    }

    //Habilitamos la accion para retroceder
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }


}