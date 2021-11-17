package com.example.talma;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class registrar_clientes extends AppCompatActivity {

    private EditText et_email, et_id_cliente, et_contraseña,et_aerolinea, et_fecha_registro ;
    private Button btn_registrar,btn_cancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_clientes);

        et_email = (EditText) findViewById(R.id.et_email);
        et_id_cliente = (EditText) findViewById(R.id.et_id_cliente);
        et_contraseña = (EditText) findViewById(R.id.et_contraseña);
        et_aerolinea = (EditText) findViewById(R.id.et_aerolinea);
        et_fecha_registro = (EditText) findViewById(R.id.et_fecha_registro);
        btn_registrar = (Button) findViewById(R.id.btn_registrar);
        btn_cancelar = (Button) findViewById(R.id.btn_cancelar);
    }





}