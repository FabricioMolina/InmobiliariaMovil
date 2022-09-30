package com.ragnar.inmobiliaria;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.ragnar.inmobiliaria.databinding.ActivityMenuBinding;
import com.ragnar.inmobiliaria.modelo.Propietario;
import com.ragnar.inmobiliaria.request.ApiClient;

public class MenuActivity extends AppCompatActivity {
    private TextView etNombre, etMail;
    private ImageView ivIcono;
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMenuBinding binding;
    private ApiClient ap = ApiClient.getApi();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMenuBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMenu.toolbar);
        binding.appBarMenu.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        //inicializarHeader(navigationView);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_perfil, R.id.nav_inmueble, R.id.nav_inquilinoFragment, R.id.nav_contratoFragment, R.id.nav_pagoFragment, R.id.nav_logoutFragment)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_menu);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_menu);
        NavigationView navigationView = binding.navView;
        inicializarHeader(navigationView);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    public void inicializarHeader(NavigationView nv){
        Propietario p = ap.obtenerUsuarioActual();

        View header = nv.getHeaderView(0);
        etNombre = header.findViewById(R.id.perfilNombre);
        etMail = header.findViewById(R.id.perfilMail);
        ivIcono = header.findViewById(R.id.imIcono);

        etNombre.setText(p.getNombre());
        etMail.setText(p.getEmail());
        ivIcono.setImageResource(p.getAvatar());
    }

}