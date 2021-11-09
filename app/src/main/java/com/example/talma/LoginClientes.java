package com.example.talma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginClientes extends AppCompatActivity {

    Button btn_Ingresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_cliente);

        btn_Ingresar = (Button) findViewById(R.id.btn_Ingresar);

        btn_Ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginClientes.this, Dashboard_cliente.class));
            }
        });
    }
}