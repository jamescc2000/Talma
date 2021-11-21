package com.example.talma.Adapters;

import android.annotation.SuppressLint;
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
import com.example.talma.ValidarServicios;
import com.example.talma.RsirEmpleados.RevisarServicios;

import java.util.List;

public class AdaptadorRsir extends RecyclerView.Adapter<AdaptadorRsir.RsirViewHolder>{

    Context context;
    List<ModeloRSIR> listaRSIR;
    String tipo_usuario;

    public AdaptadorRsir(Context context, List<ModeloRSIR> listaRSIR, String tipo_usuario) {
        this.context = context;
        this.listaRSIR = listaRSIR;
        this.tipo_usuario = tipo_usuario;
    }

    @NonNull
    @Override
    public RsirViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_rsir, viewGroup, false);
        return new RsirViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RsirViewHolder rsirViewHolder, @SuppressLint("RecyclerView") int i) {

        if (tipo_usuario == "empleado"){

            rsirViewHolder.tv_estado.setText(listaRSIR.get(i).getEstado());
            rsirViewHolder.tv_tipo_aeronave.setText(listaRSIR.get(i).getTipo_aeronave());
            rsirViewHolder.tv_fechas.setText(listaRSIR.get(i).getFecha_salida() + " - " + listaRSIR.get(i).getFecha_llegada());
            rsirViewHolder.tv_codigo_rsir.setText(listaRSIR.get(i).getCodigo());
            rsirViewHolder.tv_area.setText(listaRSIR.get(i).getArea());
            rsirViewHolder.tv_origen_destino.setText(listaRSIR.get(i).getOrigen() + " - " + listaRSIR.get(i).getDestino());

            rsirViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(context, RevisarServicios.class);
                    intent.putExtra("codigoRsir", listaRSIR.get(i).getCodigo());
                    context.startActivity(intent);

                }
            });

        }else if (tipo_usuario == "cliente"){
            rsirViewHolder.tv_estado.setText(listaRSIR.get(i).getEstado());
            rsirViewHolder.tv_tipo_aeronave.setText(listaRSIR.get(i).getTipo_aeronave());
            rsirViewHolder.tv_fechas.setText(listaRSIR.get(i).getFecha_salida() + " - " + listaRSIR.get(i).getFecha_llegada());
            rsirViewHolder.tv_codigo_rsir.setText(listaRSIR.get(i).getCodigo());
            rsirViewHolder.tv_area.setText(listaRSIR.get(i).getArea());
            rsirViewHolder.tv_origen_destino.setText(listaRSIR.get(i).getOrigen() + " - " + listaRSIR.get(i).getDestino());

            rsirViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(context, ValidarServicios.class);
                    context.startActivity(intent);

                }
            });

        }

    }


    @Override
    public int getItemCount() {
        return listaRSIR.size();
    }

    public class RsirViewHolder extends RecyclerView.ViewHolder {

        TextView tv_estado, tv_tipo_aeronave,tv_fechas, tv_codigo_rsir, tv_area, tv_origen_destino;

        public RsirViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_estado = itemView.findViewById(R.id.tv_estado);
            tv_tipo_aeronave = itemView.findViewById(R.id.tv_tipo_aeronave);
            tv_fechas = itemView.findViewById(R.id.tv_fechas);
            tv_codigo_rsir = itemView.findViewById(R.id.tv_codigo_rsir);
            tv_area = itemView.findViewById(R.id.tv_area);
            tv_origen_destino = itemView.findViewById(R.id.tv_origen_destino);
        }
    }
}
