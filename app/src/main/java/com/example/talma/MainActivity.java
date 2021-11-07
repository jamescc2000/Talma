package com.example.talma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn_login_empleado, btn_login_cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_login_cliente = (Button) findViewById(R.id.btn_login_cliente);
        btn_login_empleado = (Button) findViewById(R.id.btn_login_empleado);

        btn_login_cliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginClientes.class));
            }
        });

        btn_login_empleado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginEmpleados.class));
            }
        });
    }
}