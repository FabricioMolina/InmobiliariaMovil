package com.ragnar.inmobiliaria.ui.logout;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ragnar.inmobiliaria.LoginActivity;
import com.ragnar.inmobiliaria.R;

public class LogoutFragment extends Fragment {

    private LogoutViewModel mViewModel;
    private Button btnLogout;
    public static LogoutFragment newInstance() {
        return new LogoutFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mViewModel = new ViewModelProvider(this).get(LogoutViewModel.class);
        View view = inflater.inflate(R.layout.fragment_logout, container, false);


        mViewModel.getDecisionMutable().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    Intent intent = new Intent(view.getContext(), LoginActivity.class);
                    startActivity(intent);
                    System.exit(0);
                }else{
                    Navigation.findNavController(view).navigate(R.id.nav_home);
                }
            }
        });
        inicializarVista(view);


        return view;
    }

    private void inicializarVista(View view) {
        btnLogout = view.findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.muestraDialog(view);
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LogoutViewModel.class);
        // TODO: Use the ViewModel
    }

}