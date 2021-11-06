package com.example.talma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginEmpleados extends AppCompatActivity {

    Button btn_Ingresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_empleados);

        btn_Ingresar = (Button) findViewById(R.id.btn_Ingresar);

        btn_Ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginEmpleados.this, Dashboard_empleados.class));
            }
        });
    }
}