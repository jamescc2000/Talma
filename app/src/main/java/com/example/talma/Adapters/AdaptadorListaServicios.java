package com.example.talma.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.talma.Modelos.ModeloServicio;
import com.example.talma.R;
import com.example.talma.RealizarReclam;
import com.example.talma.RealizarReclamo;

import java.util.List;

public class AdaptadorListaServicios extends RecyclerView.Adapter<AdaptadorListaServicios.ServicioViewHolder>{

    Context context;
    List<ModeloServicio> listaServicios;
    String tipo;
    ImageButton ib_editar_servicio, ib_eliminar_servicio;
    CardView cardView;


    public AdaptadorListaServicios(Context context, List<ModeloServicio> listaServicios, String tipo) {
        this.context = context;
        this.listaServicios = listaServicios;
        this.tipo = tipo;
    }

    @NonNull
    @Override
    public ServicioViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rv_servicios, viewGroup, false);
        return new ServicioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ServicioViewHolder servicioViewHolder, @SuppressLint("RecyclerView") int i) {

        if(tipo == "revisar"){

            servicioViewHolder.tv_nombre_servicio.setText(listaServicios.get(i).getNombre_servicio());
            servicioViewHolder.tv_codigo.setText(listaServicios.get(i).getCodigo_servicio());
            servicioViewHolder.tv_horas_servicios.setText(listaServicios.get(i).getHora_desde_llegada() + " - " + listaServicios.get(i).getHora_hasta_salida());

            int cantidad_total = Integer.valueOf(listaServicios.get(i).getCantidad_llegada()) + Integer.valueOf(listaServicios.get(i).getCantidad_salida());
            String string_cantidad_total = String.valueOf(cantidad_total);

            servicioViewHolder.tv_cantidad_total_servicios.setText(string_cantidad_total);

            ib_eliminar_servicio.setVisibility(View.GONE);

            if(listaServicios.get(i).getEstado().equals("reclamado")){

                cardView.setCardBackgroundColor(Color.parseColor("#ADB3B2"));
                ib_editar_servicio.setVisibility(View.GONE);

            }else {

                ib_editar_servicio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, RealizarReclam.class);
                        intent.putExtra("codigoRsir", listaServicios.get(i).getCodigo_rsir());
                        intent.putExtra("codigoServicio", listaServicios.get(i).getCodigo_servicio());
                        context.startActivity(intent);
                    }
                });

            }

        }else if(tipo == "registro"){

            servicioViewHolder.tv_cantidad_total_servicios.setVisibility(View.GONE);

            servicioViewHolder.tv_nombre_servicio.setText(listaServicios.get(i).getNombre_servicio());
            servicioViewHolder.tv_horas_servicios.setText(listaServicios.get(i).getHora_desde_llegada() + " - " + listaServicios.get(i).getHora_hasta_salida());

            int cantidad_total = Integer.valueOf(listaServicios.get(i).getCantidad_llegada()) + Integer.valueOf(listaServicios.get(i).getCantidad_salida());
            String string_cantidad_total = String.valueOf(cantidad_total);

            servicioViewHolder.tv_codigo.setText(string_cantidad_total);

        }

    }

    @Override
    public int getItemCount() {
        return listaServicios.size();
    }

    public class ServicioViewHolder extends RecyclerView.ViewHolder {

        TextView tv_nombre_servicio, tv_horas_servicios,tv_codigo, tv_cantidad_total_servicios;

        public ServicioViewHolder(@NonNull View itemView) {
            super(itemView);


            cardView = itemView.findViewById(R.id.cv_servicios);
            ib_editar_servicio = itemView.findViewById(R.id.ib_editar_servicio);
            ib_eliminar_servicio = itemView.findViewById(R.id.ib_eliminar_servicio);
            tv_nombre_servicio = itemView.findViewById(R.id.tv_nombre_servicio);
            tv_horas_servicios = itemView.findViewById(R.id.tv_horas_servicios);
            tv_codigo = itemView.findViewById(R.id.tv_codigo);
            tv_cantidad_total_servicios = itemView.findViewById(R.id.tv_cantidad_total_servicios);
        }
    }

}
