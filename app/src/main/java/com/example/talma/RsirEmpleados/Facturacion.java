package com.example.talma.RsirEmpleados;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.talma.Nueva_factura;
import com.example.talma.R;

public class Facturacion extends AppCompatActivity {

    Button btn_nueva_factura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facturacion);

        btn_nueva_factura = (Button) findViewById(R.id.btn_nueva_factura);

        btn_nueva_factura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Facturacion.this, Nueva_factura.class);
                startActivity(intent);
            }
        });
    }

}