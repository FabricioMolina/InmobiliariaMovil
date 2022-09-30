package com.ragnar.inmobiliaria.ui.pagos;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.ragnar.inmobiliaria.R;
import com.ragnar.inmobiliaria.modelo.Inquilino;
import com.ragnar.inmobiliaria.modelo.Pago;

import java.util.List;

public class AdapterPago extends RecyclerView.Adapter<AdapterPago.ViewHolder> {
    private Context context;
    private LayoutInflater layoutInflater;
    private List<Pago> lista;

    public AdapterPago(Context context, LayoutInflater layoutInflater, List<Pago> lista) {
        this.context = context;
        this.layoutInflater = layoutInflater;
        this.lista = lista;
    }

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_pago, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pago pago = lista.get(position);
        holder.tvNumeroPago.setText("Pago #"+pago.getNumero());
        holder.tvIdContratoPago.setText("Contrato #"+pago.getContrato().getIdContrato() + "");
        holder.tvImportePago.setText("$"+pago.getImporte() + "");
        holder.tvFechaPago.setText("Fecha de Pago"+pago.getFechaDePago());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("pago", pago );
                Navigation.findNavController(view).navigate(R.id.nav_pagoDetalleFragment, bundle);
            }
        });
        /*holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("contrato", pago.getContrato() );
                Navigation.findNavController(v).navigate(R.id.nav_contratoDetalleFragment, bundle);


            }
        });*/

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvNumeroPago, tvIdContratoPago,tvImportePago,tvFechaPago;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNumeroPago = itemView.findViewById(R.id.tvNumeroPago);
            tvIdContratoPago = itemView.findViewById(R.id.tvIdContratoPago);
            tvImportePago = itemView.findViewById(R.id.tvImportePago);
            tvFechaPago = itemView.findViewById(R.id.tvFechaPago);

        }
    }
}
