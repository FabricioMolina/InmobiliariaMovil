package com.ragnar.inmobiliaria.ui.inquilinos;

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

import java.util.List;

public class AdapterInquilino extends RecyclerView.Adapter<AdapterInquilino.ViewHolder> {
    private Context context;
    private LayoutInflater layoutInflater;
    private List<Inquilino> lista;

    public AdapterInquilino(Context context, LayoutInflater layoutInflater, List<Inquilino> lista) {
        this.context = context;
        this.layoutInflater = layoutInflater;
        this.lista = lista;
    }

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_inquilino, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Inquilino inquilino = lista.get(position);
        holder.tvTelefonoInquilino.setText(inquilino.getTelefono());
        holder.tvNombreInquilino.setText(inquilino.getNombre());
        holder.tvApellidoInquilino.setText(inquilino.getApellido());
        holder.tvTrabajoInquilino.setText(inquilino.getLugarDeTrabajo());
        holder.tvEmailInquilino.setText(inquilino.getEmail());
        holder.tvDniInquilino.setText(inquilino.getDNI() + "");
        holder.tvTelefonoGarante.setText(inquilino.getTelefonoGarante());
        holder.tvNombreGarante.setText(inquilino.getNombreGarante());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("inquilino", inquilino );
                Navigation.findNavController(v).navigate(R.id.nav_inquilinoDetalleFragment, bundle);



            }
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvTelefonoInquilino, tvNombreInquilino,tvApellidoInquilino,tvTrabajoInquilino,tvEmailInquilino,tvDniInquilino,tvTelefonoGarante,tvNombreGarante;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTelefonoInquilino = itemView.findViewById(R.id.tvTelefonoInquilino);
            tvNombreInquilino = itemView.findViewById(R.id.tvNombreInquilino);
            tvApellidoInquilino = itemView.findViewById(R.id.tvApellidoInquilino);
            tvTrabajoInquilino = itemView.findViewById(R.id.tvLugarDeTrabajo);
            tvEmailInquilino = itemView.findViewById(R.id.tvEmailInquilino);
            tvDniInquilino = itemView.findViewById(R.id.tvDniInquilino);
            tvTelefonoGarante = itemView.findViewById(R.id.tvTelefonoGarante);
            tvNombreGarante = itemView.findViewById(R.id.tvNombreGarante);

        }
    }
}
