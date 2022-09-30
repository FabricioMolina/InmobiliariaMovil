package com.ragnar.inmobiliaria.ui.pagos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ragnar.inmobiliaria.R;
import com.ragnar.inmobiliaria.modelo.Pago;

public class PagoDetalleFragment extends Fragment {

    private PagoDetalleViewModel mViewModel;
    private TextView tvNumeroPagoDetalle, tvIdContratoPagoDetalle, tvImportePagoDetalle,tvFechaPagoDetalle;
    private Button btnIrContrato;

    public static PagoDetalleFragment newInstance() {
        return new PagoDetalleFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pago_detalle, container, false);
        mViewModel = new ViewModelProvider(this).get(PagoDetalleViewModel.class);
        mViewModel.getPagoMutable().observe(getViewLifecycleOwner(), new Observer<Pago>() {
            @Override
            public void onChanged(Pago pago) {

                tvNumeroPagoDetalle.setText("Pago #"+pago.getNumero());
                tvIdContratoPagoDetalle.setText("Contrato #"+pago.getContrato().getIdContrato());
                tvImportePagoDetalle.setText("$"+pago.getImporte());
                tvFechaPagoDetalle.setText("Fecha de Pago: "+pago.getFechaDePago());

                btnIrContrato.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("contrato", pago.getContrato() );
                        Navigation.findNavController(view).navigate(R.id.nav_contratoDetalleFragment, bundle);
                    }
                });
            }
        });

        inicializarVista(view);

        return view;
    }

    private void inicializarVista(View view) {
        btnIrContrato = view.findViewById(R.id.btnIrContrato);
        tvNumeroPagoDetalle = view.findViewById(R.id.tvNumeroPagoDetalle);
        tvIdContratoPagoDetalle = view.findViewById(R.id.tvIdContratoPagoDetalle);
        tvImportePagoDetalle = view.findViewById(R.id.tvImportePagoDetalle);
        tvFechaPagoDetalle = view.findViewById(R.id.tvFechaPagoDetalle);
        mViewModel.obtenerPago(getArguments());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PagoDetalleViewModel.class);
        // TODO: Use the ViewModel
    }

}