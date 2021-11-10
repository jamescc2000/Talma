package com.example.talma.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.talma.Modelos.ModeloServicio;
import com.example.talma.R;

import java.util.List;

public class AdaptadorListaServicios extends RecyclerView.Adapter<AdaptadorListaServicios.ServicioViewHolder>{

    Context context;
    List<ModeloServicio> listaServicios;
    ImageButton ib_editar_servicio, ib_eliminar_servicio;


    public AdaptadorListaServicios(Context context, List<ModeloServicio> listaServicios) {
        this.context = context;
        this.listaServicios = listaServicios;
    }

    @NonNull
    @Override
    public ServicioViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rv_servicios, viewGroup, false);
        return new ServicioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ServicioViewHolder servicioViewHolder, final int i) {
        servicioViewHolder.tv_nombre_servicio.setText(listaServicios.get(i).getNombre_servicio());
        servicioViewHolder.tv_codigo.setText(listaServicios.get(i).getCodigo_servicio());
        servicioViewHolder.tv_horas_servicios.setText(listaServicios.get(i).getHora_desde_llegada() + " - " + listaServicios.get(i).getHora_hasta_salida());

       int cantidad_total = Integer.valueOf(listaServicios.get(i).getCantidad_llegada()) + Integer.valueOf(listaServicios.get(i).getCantidad_salida());
       String string_cantidad_total = String.valueOf(cantidad_total);

        servicioViewHolder.tv_cantidad_total_servicios.setText(string_cantidad_total);

    }

    @Override
    public int getItemCount() {
        return listaServicios.size();
    }

    public class ServicioViewHolder extends RecyclerView.ViewHolder {

        TextView tv_nombre_servicio, tv_horas_servicios,tv_codigo, tv_cantidad_total_servicios;

        public ServicioViewHolder(@NonNull View itemView) {
            super(itemView);

            ib_editar_servicio = itemView.findViewById(R.id.ib_editar_servicio);
            ib_eliminar_servicio = itemView.findViewById(R.id.ib_eliminar_servicio);
            tv_nombre_servicio = itemView.findViewById(R.id.tv_nombre_servicio);
            tv_horas_servicios = itemView.findViewById(R.id.tv_horas_servicios);
            tv_codigo = itemView.findViewById(R.id.tv_codigo);
            tv_cantidad_total_servicios = itemView.findViewById(R.id.tv_cantidad_total_servicios);
        }
    }

}
