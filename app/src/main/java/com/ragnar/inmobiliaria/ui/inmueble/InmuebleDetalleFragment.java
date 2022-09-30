package com.ragnar.inmobiliaria.ui.inmueble;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ragnar.inmobiliaria.R;
import com.ragnar.inmobiliaria.modelo.Inmueble;

public class InmuebleDetalleFragment extends Fragment {

    private InmuebleDetalleViewModel mViewModel;
    private TextView tvCodigoDireccion, tvDireccion, tvUsoDetalle, tvTipoDetalle, tvAmbienteDetalle, tvPrecioDetalle;
    private CheckBox disponible;
    private ImageView ivAvatarDetalle;

    public static InmuebleDetalleFragment newInstance() {
        return new InmuebleDetalleFragment();
    }

    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(InmuebleDetalleViewModel.class);

        View view = inflater.inflate(R.layout.fragment_inmueble_detalle, container, false);

        mViewModel.getMutableInmueble().observe(getViewLifecycleOwner(), new Observer<Inmueble>() {
            @Override
            public void onChanged(Inmueble inmueble) {
                tvCodigoDireccion.setText(inmueble.getIdInmueble() + "");
                tvDireccion.setText(inmueble.getDireccion());
                tvUsoDetalle.setText(inmueble.getUso());
                tvTipoDetalle.setText(inmueble.getTipo());
                tvAmbienteDetalle.setText(inmueble.getAmbientes() + "");
                tvPrecioDetalle.setText(inmueble.getPrecio() + "");
                disponible.setChecked(inmueble.isEstado());
                disponible.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        inmueble.setEstado(disponible.isChecked());
                        mViewModel.modificar(inmueble);
                        Toast.makeText(getContext(), "Acabas de modificar el estado de este inmueble", Toast.LENGTH_SHORT).show();
                    }
                });
                Glide.with(getContext())
                        .load(inmueble.getImagen())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(ivAvatarDetalle);
            }
        });
        inicializarVista(view);

        return view;

    }

    private void inicializarVista(View view) {
        tvCodigoDireccion = view.findViewById(R.id.tvCodigoDetalle);
        tvDireccion= view.findViewById(R.id.tvDireccionDetalle);
        tvUsoDetalle= view.findViewById(R.id.tvUsoDetalle);
        tvTipoDetalle= view.findViewById(R.id.tvTipoDetalle);
        tvAmbienteDetalle= view.findViewById(R.id.tvAmbientesDetalle);
        tvPrecioDetalle= view.findViewById(R.id.tvPrecioDetalle);
        disponible= view.findViewById(R.id.checkBox);
        ivAvatarDetalle = view.findViewById(R.id.ivAvatarDetalle);
         mViewModel.obtenerInmuebles(getArguments());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(InmuebleDetalleViewModel.class);
        // TODO: Use the ViewModel
    }

}