package com.ragnar.inmobiliaria.ui.inmueble;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ragnar.inmobiliaria.R;
import com.ragnar.inmobiliaria.modelo.Inmueble;

import java.util.List;

public class AdapterInmueble extends RecyclerView.Adapter<AdapterInmueble.ViewHolder> {
    private Context context;
    private LayoutInflater layoutInflater;
    private List<Inmueble> lista;

    public AdapterInmueble(Context context, List<Inmueble> lista, LayoutInflater layoutInflater) {
        this.context = context;
        this.layoutInflater = layoutInflater;
        this.lista = lista;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_inmueble, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Inmueble inmueble = lista.get(position);
        holder.tvInmuebleDireccion.setText("Direccion: "+inmueble.getDireccion());
        holder.tvInmueblePrecio.setText("Precio: $" + inmueble.getPrecio());
        Glide.with(context)
                .load(lista.get(position).getImagen())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivInmuebleAvatar);


        //holder.ivInmuebleAvatar.setImageResource(inmueble.getImagen());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("inmueble", inmueble );
                Navigation.findNavController(v).navigate(R.id.nav_inmuebleDetalle, bundle);



            }
        });

        /*
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        */
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvInmueblePrecio, tvInmuebleDireccion;
        private ImageView ivInmuebleAvatar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivInmuebleAvatar = itemView.findViewById(R.id.ivInmuebleAvatar);
            tvInmuebleDireccion = itemView.findViewById(R.id.tvInmuebleDireccion);
            tvInmueblePrecio = itemView.findViewById(R.id.tvInmueblePrecio);

        }
    }
}
