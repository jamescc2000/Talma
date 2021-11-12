package com.example.talma.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.talma.Modelos.ModeloReclamo;
import com.example.talma.R;
import com.example.talma.RsirEmpleados.RevisarServicios;
import com.example.talma.ValidarServicios;

import java.util.List;

public class AdaptadorReclamo extends RecyclerView.Adapter<AdaptadorReclamo.ReclamoViewHolder> {

    Context context;
    List<ModeloReclamo> listaReclamo;
    String tipo_usuario;

    public AdaptadorReclamo(Context context, List<ModeloReclamo> listaReclamo, String tipo_usuario) {
        this.context = context;
        this.listaReclamo = listaReclamo;
        this.tipo_usuario = tipo_usuario;
    }

    @NonNull
    @Override
    public ReclamoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_reclamo, parent, false);
        return new AdaptadorReclamo.ReclamoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReclamoViewHolder holder, int i) {
        if (tipo_usuario == "empleado"){

            holder.tv_reclamo.setText(listaReclamo.get(i).getReclamo());
            holder.tv_rsir.setText(listaReclamo.get(i).getRsir());
            holder.tv_fecha.setText(listaReclamo.get(i).getFecha());
            holder.tv_codigo.setText(listaReclamo.get(i).getCodigo());
            holder.tv_area.setText(listaReclamo.get(i).getArea());
            holder.tv_estado.setText(listaReclamo.get(i).getEstado());
             /*
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(context, RevisarServicios.class);
                    context.startActivity(intent);

                }


            }); */

        } /* else if (tipo_usuario == "cliente"){
            rsirViewHolder.tv_aeropuerto.setText(listaRSIR.get(i).getAeropuerto());
            rsirViewHolder.tv_tipo_aeronave.setText(listaRSIR.get(i).getTipo_aeronave());
            rsirViewHolder.tv_fechas.setText(listaRSIR.get(i).getFecha_salida() + " - " + listaRSIR.get(i).getFecha_llegada());
            rsirViewHolder.tv_codigo_rsir.setText(listaRSIR.get(i).getCodigo());
            rsirViewHolder.tv_area.setText(listaRSIR.get(i).getArea());
            rsirViewHolder.tv_origen_destino.setText(listaRSIR.get(i).getOrigen() + " - " + listaRSIR.get(i).getDestino());




        }*/

    }

    @Override
    public int getItemCount() {
        return listaReclamo.size();
    }

    public class ReclamoViewHolder extends RecyclerView.ViewHolder {

        TextView tv_reclamo, tv_rsir,tv_fecha, tv_codigo, tv_area, tv_estado;

        public ReclamoViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_reclamo = itemView.findViewById(R.id.tv_reclamo);
            tv_rsir = itemView.findViewById(R.id.tv_rsir);
            tv_fecha = itemView.findViewById(R.id.tv_fecha);
            tv_codigo = itemView.findViewById(R.id.tv_codigo);
            tv_area = itemView.findViewById(R.id.tv_area);
            tv_estado = itemView.findViewById(R.id.tv_estado);
        }
    }
}
