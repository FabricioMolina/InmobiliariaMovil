package com.ragnar.inmobiliaria.ui.home;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.ragnar.inmobiliaria.R;
import com.ragnar.inmobiliaria.databinding.FragmentHomeBinding;
import com.ragnar.inmobiliaria.ui.perfil.PerfilViewModel;

public class HomeFragment extends Fragment {



    private HomeViewModel mViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        mViewModel.getMutableLeerMapa().observe(getViewLifecycleOwner(), new Observer<LeerMapa>() {
            @Override
            public void onChanged(LeerMapa leerMapa) {
                SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                        .findFragmentById(R.id.map);
                mapFragment.getMapAsync(leerMapa);
            }
        });
        mViewModel.leerMapa();
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}