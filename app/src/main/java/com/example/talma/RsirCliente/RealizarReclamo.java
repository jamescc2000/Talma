package com.example.talma.RsirCliente;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.talma.Adapters.AdaptadorReclamo;
import com.example.talma.Modelos.ModeloReclamo;
import com.example.talma.R;
import com.example.talma.RsirEmpleados.RegistrarRsire;
import com.google.firebase.auth.FirebaseAuth;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class RealizarReclamo extends AppCompatActivity {

    private EditText et_codigo_rsir, et_area, et_motivo;
    private Button btn_enviar, btn_cancelar;
    AdaptadorReclamo adaptadorReclamo;
    List<ModeloReclamo> modeloReclamoList;
    ActionBar actionBar;
    private ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realizar_reclamo);
        actionBar = getSupportActionBar();
        actionBar.setTitle("Registro de Reclamo");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        et_codigo_rsir = (EditText) findViewById(R.id.et_codigo_rsir);
        et_area = (EditText) findViewById(R.id.et_area);
        et_motivo = (EditText) findViewById(R.id.et_motivo);
        btn_enviar = (Button) findViewById(R.id.btn_enviar);
        btn_cancelar = (Button) findViewById(R.id.btn_cancelar);

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(RealizarReclamo.this);

        btn_enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(et_codigo_rsir.getText().toString()) && TextUtils.isEmpty(et_area.getText().toString()) &&
                        TextUtils.isEmpty(et_motivo.getText().toString())){
                    Toast.makeText(RealizarReclamo.this, "Por favor, primero complete todos los campos", Toast.LENGTH_SHORT).show();
                }
                else {
                    modeloReclamoList.add(new ModeloReclamo(

                    ));
                }
            }
        });

    }
}