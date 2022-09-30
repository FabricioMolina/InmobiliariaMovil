package com.ragnar.inmobiliaria.ui.perfil;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ragnar.inmobiliaria.R;
import com.ragnar.inmobiliaria.modelo.Propietario;
import com.ragnar.inmobiliaria.request.ApiClient;

public class PerfilFragment extends Fragment {

    private PerfilViewModel mViewModel;
    private TextView tvCodigo, tvDni, tvNombre, tvApellido, tvEmail, tvContraseña, tvTelefono;
    private Button btnModificar;
    private ApiClient ac;
    private int icono;
    public static PerfilFragment newInstance() {
        return new PerfilFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);
        mViewModel = new ViewModelProvider(this).get(PerfilViewModel.class);
        mViewModel.getMutablePropietario().observe(getViewLifecycleOwner(), new Observer<Propietario>() {
            @Override
            public void onChanged(Propietario p) {
                tvCodigo.setText(p.getId() + "");
                tvDni.setText(p.getDni() + "");
                tvNombre.setText(p.getNombre() + "");
                tvApellido.setText(p.getApellido() + "");
                tvEmail.setText(p.getEmail() + "");
                tvContraseña.setText(p.getContraseña() + "");
                tvTelefono.setText(p.getTelefono() + "");
            }
        });
        mViewModel.getMutableBoolean().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                tvNombre.setEnabled(aBoolean);
                tvApellido.setEnabled(aBoolean);
                tvEmail.setEnabled(aBoolean);
                tvContraseña.setEnabled(aBoolean);
                tvTelefono.setEnabled(aBoolean);
            }
        });
        mViewModel.getMutableString().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                btnModificar.setText(s);
            }
        });
        inicializarVista(view);
        return view;
    }

    private void inicializarVista(View view) {

        tvCodigo = view.findViewById(R.id.etId);
        tvDni = view.findViewById(R.id.etDni);
        tvNombre = view.findViewById(R.id.etNombre);
        tvApellido = view.findViewById(R.id.etApellido);
        tvEmail = view.findViewById(R.id.etEmail);
        tvContraseña = view.findViewById(R.id.etContraseña);
        tvTelefono = view.findViewById(R.id.etTelefono);
        btnModificar = view.findViewById(R.id.btnModificar);

        mViewModel.traerUser();


        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Propietario p = new Propietario();
                p.setId(Integer.parseInt(tvCodigo.getText().toString()));
                p.setDni(Long.parseLong(tvDni.getText().toString()));
                p.setNombre(tvNombre.getText().toString());
                p.setApellido(tvApellido.getText().toString());
                p.setEmail(tvEmail.getText().toString());
                p.setContraseña(tvContraseña.getText().toString());
                p.setTelefono(tvTelefono.getText().toString());
                p.setAvatar(icono);
                mViewModel.modificar(btnModificar.getText().toString(), p);

            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PerfilViewModel.class);
        // TODO: Use the ViewModel
    }

}