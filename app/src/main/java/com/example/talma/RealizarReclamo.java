package com.example.talma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.talma.Adapters.AdaptadorReclamo;
import com.example.talma.Modelos.ModeloReclamo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RealizarReclamo extends AppCompatActivity {

    private EditText et_codigo_rsir, et_motivo;
    private Button btn_enviar, btn_cancelar;
    AdaptadorReclamo adaptadorReclamo;
    List<ModeloReclamo> modeloReclamoList;
    ActionBar actionBar;
    FirebaseUser user;
    private ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;

    public RealizarReclamo (){

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realizar_reclamo);

        actionBar = getSupportActionBar();
        actionBar.setTitle("Registro de Reclamo");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        et_codigo_rsir = (EditText) findViewById(R.id.et_codigo_rsir);
        et_motivo = (EditText) findViewById(R.id.et_motivo);
        btn_enviar = (Button) findViewById(R.id.btn_enviar);
        btn_cancelar = (Button) findViewById(R.id.btn_cancelar);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        progressDialog = new ProgressDialog(RealizarReclamo.this);

        /*btn_enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(et_codigo_rsir.getText().toString()) && TextUtils.isEmpty(et_area.getText().toString()) &&
                        TextUtils.isEmpty(et_motivo.getText().toString())){
                    Toast.makeText(RealizarReclamo.this, "Por favor, primero complete todos los campos", Toast.LENGTH_SHORT).show();
                }
                else {
                    //RegistrarReclamo();
                }
            }
        });*/

    }

    private void RegistrarReclamo(){

        progressDialog.setTitle("Registrando");
        progressDialog.setMessage("Espere por favor...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        FirebaseUser user = firebaseAuth.getCurrentUser();
        //Aqui van los datos que queremos registrar
        assert user != null; //Afirmamos que el usuario no sea nulo

        String codigo_reclamo_string = "R_"+ et_codigo_rsir.getText().toString();
        String uid_string = user.getUid();
        String rsir_string = et_codigo_rsir.getText().toString();
        String estado_string = "Pendiente";
        String motivo_string = et_motivo.getText().toString();

        /*Creamos un Hashmap para mandar los datos a firebase*/
        Map<String, Object> datosReclamo = new HashMap<>();
        datosReclamo.put("codigo_reclamo", codigo_reclamo_string);
        datosReclamo.put("uid", uid_string);
        datosReclamo.put("rsir", rsir_string);
        datosReclamo.put("estado", estado_string);
        datosReclamo.put("motivo", motivo_string);



        //Inicializamos la instancia a la base de datos
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference reference = database.getReference("reclamo"); // Este es el nombre de la tabla
        reference.child(codigo_reclamo_string).setValue(datosReclamo).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                progressDialog.dismiss(); // El progresss se cierra

                Toast.makeText(RealizarReclamo.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                //Una vez registrado, pasamos a la pantalla de dashboard empleados
                startActivity(new Intent(RealizarReclamo.this, Dashboard_cliente.class));

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                progressDialog.dismiss(); // El progresss se cierra
                Toast.makeText(RealizarReclamo.this, "Algo ha salido mal, vuelva a intentarlo", Toast.LENGTH_SHORT).show();

            }
        });



    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}