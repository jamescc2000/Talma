package com.example.talma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.talma.Adapters.AdaptadorListaServicios;
import com.example.talma.Modelos.ModeloRSIR;
import com.example.talma.Modelos.ModeloServicio;
import com.example.talma.RsirEmpleados.RegistrarRsire;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class RealizarPedido extends AppCompatActivity {

    private Spinner sp_aeropuertos;
    private Spinner sp_aeronaves;
    Button btn_siguiente, btn_agregar, btn_finalizar;
    Button btn_fecha_llegada, btn_fecha_salida, btn_hora_llegada, btn_hora_salida;
    Button btn_hora_desde_llegada, btn_hora_hasta_llegada, btn_hora_desde_salida, btn_hora_hasta_salida;

    private EditText et_peso;
    private DatePickerDialog datePickerDialog;

    int hora,minuto;
    String codigo_rsir, codigo_servicio;
    int cantRSIR = 0, cantServicios=0;

    LinearLayout ll_registrar_rsire, ll_subtipo_servicio;
    CardView cv_datos_rsire;
    private Spinner sp_tipo_servicio, sp_subtipo_servicio, sp_nombre_servicio;
    private ProgressDialog progressDialog;

    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    DatabaseReference bd_rsir;
    DatabaseReference bd_servicios;

    RecyclerView recyclerView;
    AdaptadorListaServicios adapterListaServicios;
    List<ModeloServicio> servicios = new ArrayList<>();
    List<ModeloServicio> listaServicios = new ArrayList<>();
    LinearLayout ll_agregar_servicio;

    ActionBar actionBar;

    private Spinner sp_servicios;
    private EditText et_cantidad_llegada, et_cantidad_salida;
    private EditText et_compañia, et_origen, et_destino, et_matricula, et_nvuelo_llegada, et_pea_llegada;
    private EditText et_nvuelo_salida, et_pea_salida;
    private TextView tv_cantidad_total, tv_aeropuerto, tv_tipo_aeronave, tv_fechas, tv_compañia, tv_matricula, tv_origen_destino;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relizar_pedido);

        actionBar = getSupportActionBar();
        actionBar.setTitle("Realizar Pedido");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        sp_aeropuertos = (Spinner) findViewById(R.id.sp_aeropuertos);
        sp_aeronaves = (Spinner) findViewById(R.id.sp_aeronaves);
        btn_siguiente = (Button) findViewById(R.id.btn_siguiente);
        btn_finalizar = (Button) findViewById(R.id.btn_finalizar);
        btn_agregar = (Button) findViewById(R.id.btn_agregar);
        btn_fecha_salida = (Button) findViewById(R.id.btn_fecha_salida);
        btn_hora_salida = (Button) findViewById(R.id.btn_hora_salida);
        btn_hora_llegada = (Button) findViewById(R.id.btn_hora_llegada);
        btn_fecha_llegada = (Button) findViewById(R.id.btn_fecha_llegada) ;
        et_compañia = (EditText) findViewById(R.id.et_compañia);
        et_origen = (EditText) findViewById(R.id.et_origen);
        et_destino = (EditText) findViewById(R.id.et_destino);
        et_matricula = (EditText) findViewById(R.id.et_matricula);
        et_peso = (EditText) findViewById(R.id.et_peso);
        et_nvuelo_llegada = (EditText) findViewById(R.id.et_nvuelo_llegada);
        et_pea_llegada = (EditText) findViewById(R.id.et_pea_llegada);
        et_nvuelo_salida = (EditText) findViewById(R.id.et_nvuelo_salida);
        et_pea_salida = (EditText) findViewById(R.id.et_pea_salida);
        sp_tipo_servicio = (Spinner) findViewById(R.id.sp_tipo_servicio);
        sp_subtipo_servicio = (Spinner) findViewById(R.id.sp_subtipo_servicio);
        sp_nombre_servicio = (Spinner) findViewById(R.id.sp_nombre_servicio);
        et_cantidad_llegada = (EditText) findViewById(R.id.et_cantidad_llegada);
        et_cantidad_salida = (EditText) findViewById(R.id.et_cantidad_salida);
        tv_aeropuerto = (TextView) findViewById(R.id.tv_aeropuerto);
        tv_tipo_aeronave = (TextView) findViewById(R.id.tv_tipo_aeronave);
        tv_matricula = (TextView) findViewById(R.id.tv_matricula);
        tv_fechas = (TextView) findViewById(R.id.tv_fechas);
        tv_origen_destino = (TextView) findViewById(R.id.tv_origen_destino);
        tv_compañia = (TextView) findViewById(R.id.tv_compañia);
        btn_hora_desde_llegada = (Button) findViewById(R.id.btn_hora_desde_llegada) ;
        btn_hora_hasta_llegada = (Button) findViewById(R.id.btn_hora_hasta_llegada) ;
        btn_hora_desde_salida = (Button) findViewById(R.id.btn_hora_desde_salida) ;
        btn_hora_hasta_salida = (Button) findViewById(R.id.btn_hora_hasta_salida) ;
        ll_agregar_servicio = (LinearLayout) findViewById(R.id.ll_agregar_servicio);
        cv_datos_rsire = (CardView) findViewById(R.id.cv_datos_rsire);
        ll_registrar_rsire = (LinearLayout) findViewById(R.id.ll_registrar_rsire);
        ll_subtipo_servicio = (LinearLayout) findViewById(R.id.ll_subtipo_servicio);

        String[] opciones_tipos_servicios = {"Carga", "Adicionales"};
        ArrayAdapter<String> adapter_tipos_servicios = new ArrayAdapter<String>(RealizarPedido.this, R.layout.spinner, opciones_tipos_servicios);
        sp_tipo_servicio.setAdapter(adapter_tipos_servicios);

        sp_tipo_servicio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position == 0){

                    ll_subtipo_servicio.setVisibility(View.VISIBLE);

                    String[] opciones_subtipos_servicios = {"Carga general", "Carga peligorsa", "Carga perecible", "Carga valorada", "Carga mixta", "Carga mascota", "Carga temperada", "Carga farmaceutica", "Carga maritima"};
                    ArrayAdapter<String> adapter_subtipos_servicios = new ArrayAdapter<String>(RealizarPedido.this, R.layout.spinner, opciones_subtipos_servicios);
                    sp_subtipo_servicio.setAdapter(adapter_subtipos_servicios);

                    sp_subtipo_servicio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {

                            if(i == 0){

                                String[] opciones_nombre_servicios = {"Almacenaje", "Manipuleo aeroportuario", "Descarga", "Monitoreo y control de carga", "Salvaguardia"};
                                ArrayAdapter<String> adapter_nombre_servicios = new ArrayAdapter<String>(RealizarPedido.this, R.layout.spinner, opciones_nombre_servicios);
                                sp_nombre_servicio.setAdapter(adapter_nombre_servicios);

                            }else if(i == 1){

                                String[] opciones_nombre_servicios = {"Almacenaje", "Manipuleo aeroportuario", "Descarga", "Monitoreo y control de carga", "Salvaguardia"};
                                ArrayAdapter<String> adapter_nombre_servicios = new ArrayAdapter<String>(RealizarPedido.this, R.layout.spinner, opciones_nombre_servicios);
                                sp_nombre_servicio.setAdapter(adapter_nombre_servicios);

                            }else if(i == 2){

                                String[] opciones_nombre_servicios = {"Almacenaje", "Manipuleo aeroportuario", "Descarga", "Monitoreo y control de carga", "Salvaguardia"};
                                ArrayAdapter<String> adapter_nombre_servicios = new ArrayAdapter<String>(RealizarPedido.this, R.layout.spinner, opciones_nombre_servicios);
                                sp_nombre_servicio.setAdapter(adapter_nombre_servicios);

                            }else if(i == 3){

                                String[] opciones_nombre_servicios = {"Almacenaje", "Manipuleo aeroportuario", "Descarga", "Monitoreo y control de carga", "Salvaguardia"};
                                ArrayAdapter<String> adapter_nombre_servicios = new ArrayAdapter<String>(RealizarPedido.this, R.layout.spinner, opciones_nombre_servicios);
                                sp_nombre_servicio.setAdapter(adapter_nombre_servicios);

                            }else if(i == 4){

                                String[] opciones_nombre_servicios = {"Almacenaje", "Manipuleo aeroportuario", "Descarga", "Monitoreo y control de carga", "Salvaguardia"};
                                ArrayAdapter<String> adapter_nombre_servicios = new ArrayAdapter<String>(RealizarPedido.this, R.layout.spinner, opciones_nombre_servicios);
                                sp_nombre_servicio.setAdapter(adapter_nombre_servicios);

                            }else if(i == 5){

                            }else if(i == 6){

                                String[] opciones_nombre_servicios = {"Almacenaje", "Manipuleo aeroportuario", "Descarga", "Monitoreo y control de carga", "Salvaguardia"};
                                ArrayAdapter<String> adapter_nombre_servicios = new ArrayAdapter<String>(RealizarPedido.this, R.layout.spinner, opciones_nombre_servicios);
                                sp_nombre_servicio.setAdapter(adapter_nombre_servicios);

                            }else if(i == 7){

                                String[] opciones_nombre_servicios = {"Almacenaje", "Manipuleo aeroportuario", "Descarga", "Monitoreo y control de carga", "Salvaguardia"};
                                ArrayAdapter<String> adapter_nombre_servicios = new ArrayAdapter<String>(RealizarPedido.this, R.layout.spinner, opciones_nombre_servicios);
                                sp_nombre_servicio.setAdapter(adapter_nombre_servicios);

                            }else if(i == 8){

                                String[] opciones_nombre_servicios = {"Almacenaje marítimo", "Servicio de carga marítima", "Manipuleo marítimo", "Reconocimiento previo maritimo", "Reconocimiento físico maritimo", "Control y manejo de carga marítima"};
                                ArrayAdapter<String> adapter_nombre_servicios = new ArrayAdapter<String>(RealizarPedido.this, R.layout.spinner, opciones_nombre_servicios);
                                sp_nombre_servicio.setAdapter(adapter_nombre_servicios);

                            }

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });


                }else if(position == 1){

                    ll_subtipo_servicio.setVisibility(View.GONE);

                    String[] opciones_nombre_servicios =  {"Estibador", "Reconocimiento fisico", "Extraccion de muestras", "Montacarga", "Inspeccion"};
                    ArrayAdapter<String> adapter_nombre_servicios = new ArrayAdapter<String>(RealizarPedido.this, R.layout.spinner, opciones_nombre_servicios);
                    sp_nombre_servicio.setAdapter(adapter_nombre_servicios);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        String [] opciones_aeropuertos = {"Jorge Cahvez","Alfredo Rodríguez Ballón","Alfredo Mendívil Duarte","Armando Revoredo Iglesias", "Chiclayo", "Alejandro Velasco Astete de Cusco"};
        ArrayAdapter<String> adapter_aeropuetos = new ArrayAdapter<String>(RealizarPedido.this, R.layout.spinner,opciones_aeropuertos);
        sp_aeropuertos.setAdapter(adapter_aeropuetos);

        String [] opciones_aeronaves = {"Avión de carga", "Avion comercial"};
        ArrayAdapter<String> adapter_aeronaves = new ArrayAdapter<String>(RealizarPedido.this, R.layout.spinner,opciones_aeronaves);
        sp_aeronaves.setAdapter(adapter_aeronaves);


        recyclerView = findViewById(R.id.rvListaServicios);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(RealizarPedido.this));

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        progressDialog = new ProgressDialog(RealizarPedido.this);
        bd_rsir = FirebaseDatabase.getInstance().getReference("rsir");
        bd_servicios = FirebaseDatabase.getInstance().getReference("servicios");

        ObtenerCodigoServicioAutomatico();
        ObtenerCodigoRsirAutomatico();

        btn_hora_desde_llegada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        hora = hourOfDay;
                        minuto = minute;
                        btn_hora_desde_llegada.setText(String.format(Locale.getDefault(), "%02d:%02d", hora, minuto));
                    }
                };

                int style = AlertDialog.THEME_HOLO_LIGHT;

                TimePickerDialog timePickerDialog = new TimePickerDialog(RealizarPedido.this, style,listener, hora, minuto, true);
                timePickerDialog.show();

            }
        });

        btn_hora_hasta_llegada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        hora = hourOfDay;
                        minuto = minute;
                        btn_hora_hasta_llegada.setText(String.format(Locale.getDefault(), "%02d:%02d", hora, minuto));
                    }
                };

                int style = AlertDialog.THEME_HOLO_LIGHT;

                TimePickerDialog timePickerDialog = new TimePickerDialog(RealizarPedido.this, style,listener, hora, minuto, true);
                timePickerDialog.show();

            }
        });

        btn_hora_desde_salida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        hora = hourOfDay;
                        minuto = minute;
                        btn_hora_desde_salida.setText(String.format(Locale.getDefault(), "%02d:%02d", hora, minuto));
                    }
                };

                int style = AlertDialog.THEME_HOLO_LIGHT;

                TimePickerDialog timePickerDialog = new TimePickerDialog(RealizarPedido.this, style,listener, hora, minuto, true);
                timePickerDialog.show();

            }
        });

        btn_hora_hasta_salida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        hora = hourOfDay;
                        minuto = minute;
                        btn_hora_hasta_salida.setText(String.format(Locale.getDefault(), "%02d:%02d", hora, minuto));
                    }
                };

                int style = AlertDialog.THEME_HOLO_LIGHT;

                TimePickerDialog timePickerDialog = new TimePickerDialog(RealizarPedido.this, style,listener, hora, minuto, true);
                timePickerDialog.show();

            }
        });

        btn_fecha_salida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String fecha = makeDateString(dayOfMonth, month, year);
                        btn_fecha_salida.setText(fecha);
                    }
                };
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int moth = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int style  = AlertDialog.THEME_HOLO_LIGHT;
                datePickerDialog = new DatePickerDialog(RealizarPedido.this, style, dateSetListener, year, moth, day);
                datePickerDialog.show();
            }
        });

        btn_fecha_llegada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String fecha = makeDateString(dayOfMonth, month, year);
                        btn_fecha_llegada.setText(fecha);
                    }
                };
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int moth = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int style  = AlertDialog.THEME_HOLO_LIGHT;

                datePickerDialog = new DatePickerDialog(RealizarPedido.this, style, dateSetListener, year, moth, day);

                datePickerDialog.show();
            }
        });

        btn_hora_salida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        hora = hourOfDay;
                        minuto = minute;
                        btn_hora_salida.setText(String.format(Locale.getDefault(), "%02d:%02d", hora, minuto));
                    }
                };

                int style = AlertDialog.THEME_HOLO_LIGHT;

                TimePickerDialog timePickerDialog = new TimePickerDialog(RealizarPedido.this, style,listener, hora, minuto, true);
                timePickerDialog.show();
            }
        });

        btn_hora_llegada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        hora = hourOfDay;
                        minuto = minute;
                        btn_hora_llegada.setText(String.format(Locale.getDefault(), "%02d:%02d", hora, minuto));
                    }
                };

                int style = AlertDialog.THEME_HOLO_LIGHT;

                TimePickerDialog timePickerDialog = new TimePickerDialog(RealizarPedido.this, style,listener, hora, minuto, true);
                timePickerDialog.show();
            }
        });

        btn_siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ll_registrar_rsire.getVisibility() == View.GONE){

                    ll_registrar_rsire.setVisibility(View.VISIBLE);
                    btn_siguiente.setText("GUARDAR");
                    cv_datos_rsire.setVisibility(View.GONE);

                }else if (ll_registrar_rsire.getVisibility() == View.VISIBLE){

                    ll_registrar_rsire.setVisibility(View.GONE);
                    btn_siguiente.setText("EDITAR DATOS GENERALES");


                    tv_aeropuerto.setText(sp_aeropuertos.getSelectedItem().toString());
                    tv_tipo_aeronave.setText(sp_aeronaves.getSelectedItem().toString());
                    tv_fechas.setText(btn_fecha_salida.getText().toString() + " - " + btn_fecha_llegada.getText().toString());
                    tv_compañia.setText(et_compañia.getText().toString());
                    tv_matricula.setText(et_matricula.getText().toString());
                    tv_origen_destino.setText(et_origen.getText().toString() + " - " + et_destino.getText().toString());

                    cv_datos_rsire.setVisibility(View.VISIBLE);
                }


            }
        });

        btn_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ll_agregar_servicio.getVisibility() == View.GONE){

                        ll_agregar_servicio.setVisibility(View.VISIBLE);
                        btn_agregar.setText("Guardar");

                }else if (ll_agregar_servicio.getVisibility() == View.VISIBLE){

                    //Una vez registrado el servicio, lo agregamos al rv
                    listaServicios.add(new ModeloServicio(user.getUid(),
                            codigo_rsir,
                            sp_tipo_servicio.getSelectedItem().toString(),
                            sp_subtipo_servicio.getSelectedItem().toString(),
                            sp_servicios.getSelectedItem().toString(),
                            codigo_servicio,
                            btn_hora_desde_llegada.getText().toString(),
                            btn_hora_hasta_llegada.getText().toString(),
                            btn_hora_desde_salida.getText().toString(),
                            btn_hora_hasta_salida.getText().toString(),
                            et_cantidad_llegada.getText().toString(),
                            et_cantidad_salida.getText().toString(), "registrado"));

                    cantServicios++;
                    codigo_servicio = darFormatoServicio(cantServicios);

                    //Limpiamos los campos
                    btn_hora_desde_llegada.setText("Hora desde");
                    btn_hora_hasta_llegada.setText("Hora hasta");
                    btn_hora_desde_salida.setText("Hora desde");
                    btn_hora_hasta_salida.setText("Hora hasta");
                    et_cantidad_llegada.setText("");
                    et_cantidad_salida.setText("");

                    adapterListaServicios = new AdaptadorListaServicios(RealizarPedido.this, listaServicios, "registro", "cliente");
                    recyclerView.setAdapter(adapterListaServicios);

                    btn_agregar.setText("Agregar servicio");
                    ll_agregar_servicio.setVisibility(View.GONE);
                }


            }
        });

        btn_finalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Validacion de los campos
                if(TextUtils.isEmpty(sp_aeropuertos.getSelectedItem().toString()) ||
                        TextUtils.isEmpty(et_compañia.getText().toString()) || TextUtils.isEmpty(et_origen.getText().toString()) ||
                        TextUtils.isEmpty(et_destino.getText().toString()) || TextUtils.isEmpty(sp_aeronaves.getSelectedItem().toString()) ||
                        TextUtils.isEmpty(et_matricula.getText().toString()) ||
                        TextUtils.isEmpty(btn_fecha_llegada.getText().toString()) ||
                        TextUtils.isEmpty(btn_hora_llegada.getText().toString()) || TextUtils.isEmpty(et_nvuelo_llegada.getText().toString()) ||
                        TextUtils.isEmpty(btn_fecha_salida.getText().toString()) || TextUtils.isEmpty(btn_hora_salida.getText().toString()) ||
                        TextUtils.isEmpty(et_nvuelo_salida.getText().toString()) || TextUtils.isEmpty(et_pea_salida.getText().toString()) ||
                        TextUtils.isEmpty(et_pea_llegada.getText().toString())){

                    Toast.makeText(RealizarPedido.this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();

                }else {

                    if(listaServicios.size() < 1){

                        Toast.makeText(RealizarPedido.this, "Por favor agrege minimo un servicio", Toast.LENGTH_SHORT).show();

                    }else {

                        RegistrarServicios();
                        RegistrarRSIR();

                    }

                }

            }
        });


    }

    private void RegistrarRSIR(){

        progressDialog.setTitle("Registrando");
        progressDialog.setMessage("Espere por favor...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        //Aqui van los datos que queremos registrar
        assert user != null; //Afirmamos que el usuario no sea nulo

        ObtenerCodigoRsirAutomatico();

        String uid_String = user.getUid();
        String codigo_String = codigo_rsir;
        String aeropuerto_String = sp_aeropuertos.getSelectedItem().toString();
        String compañia_string = et_compañia.getText().toString();
        String origen_String = et_origen.getText().toString();
        String destino_String = et_destino.getText().toString();
        String aeronave_String = sp_aeronaves.getSelectedItem().toString();
        String matricula_string = et_matricula.getText().toString();
        String peso_string = et_peso.getText().toString();
        String area_string = "";
        String a_cargo_de_string = "";
        String fecha_llegada_string = btn_fecha_llegada.getText().toString();
        String hora_llegada_string = btn_hora_llegada.getText().toString();
        String nvuelo_llegada_string = et_nvuelo_llegada.getText().toString();
        String pea_llegada_string = et_pea_llegada.getText().toString();
        String fecha_salida_string = btn_fecha_salida.getText().toString();
        String hora_salida_string = btn_hora_salida.getText().toString();
        String nvuelo_salida_string = et_nvuelo_salida.getText().toString();
        String pea_salida_string = et_pea_salida.getText().toString();

        /*Creamos un Hashmap para mandar los datos a firebase*/
        Map<String, Object> datosRsir = new HashMap<>();

        datosRsir.put("uid", uid_String);
        datosRsir.put("codigoRsir", codigo_String);
        datosRsir.put("aeropuerto", aeropuerto_String);
        datosRsir.put("compania", compañia_string);
        datosRsir.put("emailCliente", user.getEmail());
        datosRsir.put("origen", origen_String);
        datosRsir.put("destino", destino_String);
        datosRsir.put("aeronave", aeronave_String);
        datosRsir.put("matricula", matricula_string);
        datosRsir.put("peso", peso_string);
        datosRsir.put("area", area_string);
        datosRsir.put("aCargoDe", a_cargo_de_string);
        datosRsir.put("fechaLlegada", fecha_llegada_string);
        datosRsir.put("horaLlegada", hora_llegada_string);
        datosRsir.put("nvueloLlegada", nvuelo_llegada_string);
        datosRsir.put("peaLlegada", pea_llegada_string);
        datosRsir.put("fechaSalida", fecha_salida_string);
        datosRsir.put("horaSalida", hora_salida_string);
        datosRsir.put("nvueloSalida", nvuelo_salida_string);
        datosRsir.put("peaSalida", pea_salida_string);
        datosRsir.put("estado", "registrado");

        //Inicializamos la instancia a la base de datos
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        //Creamos la tabla
        DatabaseReference reference = database.getReference("rsir"); // Este es el nombre de la tabla
        reference.child(codigo_String).setValue(datosRsir).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                progressDialog.dismiss(); // El progresss se cierra

                Toast.makeText(RealizarPedido.this, "Registro exitoso", Toast.LENGTH_SHORT).show();


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                progressDialog.dismiss(); // El progresss se cierra
                Toast.makeText(RealizarPedido.this, "Algo ha salido mal, vuelva a intentarlo", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void RegistrarServicios(){

        //Aqui van los datos que queremos registrar
        assert user != null; //Afirmamos que el usuario no sea nulo

        for(int i=0; i < listaServicios.size(); i++){


            String uid_String = user.getUid();
            String codigo_RSIR_string = codigo_rsir;
            String nombre_string = listaServicios.get(i).getNombre_servicio();
            String codigo_servicio_string = listaServicios.get(i).getCodigo_servicio();
            String hora_desde_llegada = listaServicios.get(i).getHora_desde_llegada();
            String hora_hasta_llegada = listaServicios.get(i).getHora_hasta_llegada();
            String cantidad_llegada_string = listaServicios.get(i).getCantidad_llegada();
            String hora_desde_salida = listaServicios.get(i).getHora_desde_salida();
            String hora_hasta_salida = listaServicios.get(i).getHora_hasta_salida();
            String cantidad_salida_string = listaServicios.get(i).getCantidad_salida();

            /*Creamos un Hashmap para mandar los datos a firebase*/
            Map<String, Object> datosServicio = new HashMap<>();

            datosServicio.put("codigoServicio", codigo_servicio_string);
            datosServicio.put("uid", uid_String);
            datosServicio.put("codigoRSIR", codigo_RSIR_string);
            datosServicio.put("nombre", nombre_string);
            datosServicio.put("horaDesdeLlegada", hora_desde_llegada);
            datosServicio.put("horaHastaLlegada", hora_hasta_llegada);
            datosServicio.put("cantidadLlegada", cantidad_llegada_string);
            datosServicio.put("horaDesdeSalida", hora_desde_salida);
            datosServicio.put("horaHastaSalida", hora_hasta_salida);
            datosServicio.put("cantidadSalida", cantidad_salida_string);
            datosServicio.put("estado", "registrado");

            //Inicializamos la instancia a la base de datos
            FirebaseDatabase database = FirebaseDatabase.getInstance();

            //Creamos la tabla
            DatabaseReference reference = database.getReference("servicios"); // Este es el nombre de la tabla
            reference.child(codigo_servicio_string).setValue(datosServicio).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    //Una vez registrado, pasamos a la pantalla de dashboard cliente
                    startActivity(new Intent(RealizarPedido.this, Dashboard_cliente.class));

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Toast.makeText(RealizarPedido.this, "Algo ha salido mal, vuelva a intentarlo", Toast.LENGTH_SHORT).show();

                }
            });

        }

    }

    private  String darFormatoRsir(int cantidad){

        String cantRsir = "";

        if(cantidad <10){
            cantRsir = "R0000000"+cantidad;
        }else if(cantidad <100){
            cantRsir = "R000000"+cantidad;
        }else if(cantidad <1000){
            cantRsir = "R00000"+cantidad;
        }else if(cantidad <10000){
            cantRsir = "R0000"+cantidad;
        }else if(cantidad <100000){
            cantRsir = "R000"+cantidad;
        }else if(cantidad <1000000){
            cantRsir = "R00"+cantidad;
        }else if(cantidad <10000000){
            cantRsir = "R0"+cantidad;
        }else if(cantidad <100000000){
            cantRsir = "R"+cantidad;
        }else {
            cantRsir = "R" + cantidad;
        }

        return cantRsir;
    }

    private void ObtenerCodigoRsirAutomatico(){

        bd_rsir.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot ds: snapshot.getChildren()){
                    cantRSIR++;
                }
                cantRSIR++;
                codigo_rsir = darFormatoRsir(cantRSIR);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void ObtenerCodigoServicioAutomatico(){

        bd_servicios.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot ds: snapshot.getChildren()){
                    cantServicios++;
                }
                codigo_servicio = darFormatoServicio(cantServicios);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private String darFormatoServicio(int cant){
        String cantServicios = "";

        if(cant <10){
            cantServicios = "S0000000"+cant;
        }else if(cant <100){
            cantServicios = "S000000"+cant;
        }else if(cant <1000){
            cantServicios = "S00000"+cant;
        }else if(cant <10000){
            cantServicios = "S0000"+cant;
        }else if(cant <100000){
            cantServicios = "S000"+cant;
        }else if(cant <1000000){
            cantServicios = "S00"+cant;
        }else if(cant <10000000){
            cantServicios = "S0"+cant;
        }else if(cant <100000000){
            cantServicios = "S"+cant;
        }else {
            cantServicios = "S" + cant;
        }

        return cantServicios;
    }

    private String makeDateString(int dayOfMonth, int month, int year){
        return dayOfMonth + "/" + month + "/" + year;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}