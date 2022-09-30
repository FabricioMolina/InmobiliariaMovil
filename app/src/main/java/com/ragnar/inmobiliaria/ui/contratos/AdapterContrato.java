package com.ragnar.inmobiliaria.ui.contratos;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.ragnar.inmobiliaria.R;
import com.ragnar.inmobiliaria.modelo.Contrato;
import com.ragnar.inmobiliaria.modelo.Inquilino;

import java.util.ArrayList;
import java.util.List;

public class AdapterContrato extends RecyclerView.Adapter<AdapterContrato.ViewHolder> {
    private Context context;
    private LayoutInflater layoutInflater;
    private List<Contrato> lista;

    public AdapterContrato(Context context, LayoutInflater layoutInflater, List<Contrato> lista) {
        this.context = context;
        this.layoutInflater = layoutInflater;
        this.lista = lista;
    }

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_contrato, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Contrato contrato = lista.get(position);
        holder.tvIdContrato.setText(contrato.getIdContrato() + "");
        holder.tvFechaInicio.setText(contrato.getFechaInicio());
        holder.tvFechaExpiracion.setText(contrato.getFechaFin());
        holder.tvMonto.setText(contrato.getMontoAlquiler() + "");
        holder.tvNombreInquilinoContrato.setText(contrato.getInquilino().getNombre());
        holder.idInmuebleContrato.setText(contrato.getInmueble().getDireccion());

        holder.btnInquilino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("inquilino", contrato.getInquilino()) ;
                Navigation.findNavController(view).navigate(R.id.nav_inquilinoDetalleFragment, bundle);
            }
        });
        holder.btnInmueble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("inmueble", contrato.getInmueble()) ;
                Navigation.findNavController(view).navigate(R.id.nav_inmuebleDetalle, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvIdContrato, tvFechaInicio,tvFechaExpiracion,tvMonto,tvNombreInquilinoContrato,idInmuebleContrato;
        private Button btnInquilino, btnInmueble;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIdContrato = itemView.findViewById(R.id.tvIdContrato);
            tvFechaInicio = itemView.findViewById(R.id.tvFechaInicio);
            tvFechaExpiracion = itemView.findViewById(R.id.tvFechaExpiracion);
            tvMonto = itemView.findViewById(R.id.tvMonto);
            tvNombreInquilinoContrato = itemView.findViewById(R.id.tvNombreInquilinoContrato);
            idInmuebleContrato = itemView.findViewById(R.id.idInmuebleContrato);
            btnInquilino = itemView.findViewById(R.id.btnInquilino);
            btnInmueble = itemView.findViewById(R.id.btnInmueble);

        }
    }
}
