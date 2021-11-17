package com.example.talma;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Registrar_empleados extends AppCompatActivity {

    private EditText et_email, et_id_empleado, et_contraseña,et_nombres, et_apellidos,et_dni,et_fecha_nacimiento ;
    private Spinner sp_tipo_cargo, sp_area;
    private Button btn_registrar,btn_cancelar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_empleados);

        et_email = (EditText) findViewById(R.id.et_email);
        et_id_empleado = (EditText) findViewById(R.id.et_id_empleado);
        et_contraseña = (EditText) findViewById(R.id.et_contraseña);
        et_nombres = (EditText) findViewById(R.id.et_nombres);
        et_apellidos = (EditText) findViewById(R.id.et_apellidos);
        et_dni = (EditText) findViewById(R.id.et_dni);
        et_fecha_nacimiento = (EditText) findViewById(R.id.et_fecha_nacimiento);
        sp_tipo_cargo = (Spinner) findViewById(R.id.sp_tipo_cargo);
        sp_area= (Spinner) findViewById(R.id.sp_area);
        btn_registrar = (Button) findViewById(R.id.btn_registrar);
        btn_cancelar = (Button) findViewById(R.id.btn_cancelar);

    }




}