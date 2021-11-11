package com.example.talma.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.talma.Modelos.ModeloRSIR;
import com.example.talma.R;
import com.example.talma.RealizarPedido;
import com.example.talma.RevisarServicios;

import java.util.List;

public class AdaptadorRsir extends RecyclerView.Adapter<AdaptadorRsir.RsirViewHolder>{

    Context context;
    List<ModeloRSIR> listaRSIR;

    public AdaptadorRsir(Context context, List<ModeloRSIR> listaRSIR) {
        this.context = context;
        this.listaRSIR = listaRSIR;
    }

    @NonNull
    @Override
    public RsirViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_rsir, viewGroup, false);
        return new RsirViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RsirViewHolder rsirViewHolder, int i) {

        rsirViewHolder.tv_aeropuerto.setText(listaRSIR.get(i).getAeropuerto());
        rsirViewHolder.tv_tipo_aeronave.setText(listaRSIR.get(i).getTipo_aeronave());
        rsirViewHolder.tv_fechas.setText(listaRSIR.get(i).getFecha_salida() + " - " + listaRSIR.get(i).getFecha_llegada());
        rsirViewHolder.tv_codigo_rsir.setText(listaRSIR.get(i).getCodigo());
        rsirViewHolder.tv_area.setText(listaRSIR.get(i).getArea());
        rsirViewHolder.tv_origen_destino.setText(listaRSIR.get(i).getOrigen() + " - " + listaRSIR.get(i).getDestino());

        rsirViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, RevisarServicios.class);
                context.startActivity(intent);

            }
        });

    }


    @Override
    public int getItemCount() {
        return listaRSIR.size();
    }

    public class RsirViewHolder extends RecyclerView.ViewHolder {

        TextView tv_aeropuerto, tv_tipo_aeronave,tv_fechas, tv_codigo_rsir, tv_area, tv_origen_destino;

        public RsirViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_aeropuerto = itemView.findViewById(R.id.tv_aeropuerto);
            tv_tipo_aeronave = itemView.findViewById(R.id.tv_tipo_aeronave);
            tv_fechas = itemView.findViewById(R.id.tv_fechas);
            tv_codigo_rsir = itemView.findViewById(R.id.tv_codigo_rsir);
            tv_area = itemView.findViewById(R.id.tv_area);
            tv_origen_destino = itemView.findViewById(R.id.tv_origen_destino);
        }
    }
}
