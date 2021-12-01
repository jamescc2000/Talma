package com.example.talma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.talma.RsirEmpleados.RegistrarRsire;
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
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Nueva_factura extends AppCompatActivity {

    private EditText et_RSIR,et_encargado_facturacion;
    private Button btn_fecha_emision;

    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    DatabaseReference bd_facturas;
    DatePickerDialog datePickerDialog;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_factura);

        et_RSIR = (EditText) findViewById(R.id.et_RSIR);
        et_encargado_facturacion = (EditText) findViewById(R.id.et_encargado_facturacion);
        btn_fecha_emision = (Button) findViewById(R.id.btn_fecha_emision);

        firebaseAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        progressDialog = new ProgressDialog(Nueva_factura.this);

        bd_facturas = FirebaseDatabase.getInstance().getReference("facturas");

        btn_fecha_emision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String fecha = makeDateString(dayOfMonth, month, year);
                        btn_fecha_emision.setText(fecha);
                    }
                };
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int moth = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int style  = AlertDialog.THEME_HOLO_LIGHT;
                datePickerDialog = new DatePickerDialog(Nueva_factura.this, style, dateSetListener, year, moth, day);
                datePickerDialog.show();
            }
        });


    }

    private void RegistrarFactura (){
        progressDialog.setTitle("Registrando");
        progressDialog.setMessage("Espere por favor...");
        progressDialog.setCancelable(false);
        progressDialog.show();

    }

    public void RegistarClientes(String Email, String Password){

        progressDialog.setTitle("Registrando");
        progressDialog.setMessage("Espere por favor...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        assert user != null; //Afirmamos que el usuario no sea nulo

        String uid_String = user.getUid();
        String RSIR_String = et_RSIR.getText().toString();
        String encargado_facturacion_String = et_encargado_facturacion.getText().toString();
        String fecha_emision_String = btn_fecha_emision.getText().toString();

        /*Creamos un Hashmap para mandar los datos a firebase*/
        Map<String, Object> datosNuevaFactura = new HashMap<>();

        datosNuevaFactura.put("uid", uid_String);
        datosNuevaFactura.put("RSIR", RSIR_String);
        datosNuevaFactura.put("EncargadoFacturacion", encargado_facturacion_String);
        datosNuevaFactura.put("FechaEmision", fecha_emision_String);

        bd_facturas.child(uid_String).setValue(datosNuevaFactura).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                progressDialog.dismiss(); // El progresss se cierra

                Toast.makeText(Nueva_factura.this, "Registro exitoso", Toast.LENGTH_SHORT).show();


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                progressDialog.dismiss(); // El progresss se cierra
                Toast.makeText(Nueva_factura.this, "Algo ha salido mal, vuelva a intentarlo", Toast.LENGTH_SHORT).show();

            }
        });


    }

    private String makeDateString(int dayOfMonth, int month, int year){
        return dayOfMonth + "/" + month + "/" + year;
    }

    /*private void ObtenerServicios () {

        bd_facturas.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()){
                    bd_facturas.child()
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        })
    }*/

}