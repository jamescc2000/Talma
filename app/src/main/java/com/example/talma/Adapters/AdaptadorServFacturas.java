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

public class AdaptadorServFacturas extends RecyclerView.Adapter<AdaptadorServFacturas.ServicioViewHolder> {

    Context context;
    List<ModeloServicio> listaServicios;
    String tipo, usuario;

    public AdaptadorServFacturas(Context context, List<ModeloServicio> listaServicios, String usuario) {
        this.context = context;
        this.listaServicios = listaServicios;
        this.usuario = usuario;
    }

    @NonNull
    @Override
    public ServicioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_serv_facturas, parent, false);
        return new AdaptadorServFacturas.ServicioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServicioViewHolder holder, int i) {
        holder.tv_nombre_servicio.setText(listaServicios.get(i).getNombre_servicio());
        holder.tv_codigo.setText(listaServicios.get(i).getCodigo_servicio());
        holder.tv_precio.setText("122");
    }

    @Override
    public int getItemCount() {
        return listaServicios.size();
    }

    public class ServicioViewHolder extends RecyclerView.ViewHolder {

        TextView tv_nombre_servicio, tv_codigo, tv_precio;
        public ServicioViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_precio = itemView.findViewById(R.id.tv_precio);
            tv_codigo = itemView.findViewById(R.id.tv_codigo);
            tv_nombre_servicio = itemView.findViewById(R.id.tv_nombre_servicio);
        }
    }
}
