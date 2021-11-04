package com.developer.johhns.fotosmarcianas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.developer.johhns.fotosmarcianas.databinding.FotosFragBinding;

import java.util.ArrayList;
import java.util.List;


public class FotosFragmento extends Fragment {

    FotosFragBinding      binding   ;
    FotosRecViewAdaptador adaptador ;
    FotosViewModel        viewModel ;
    List<FotoDeMarte>     fotos     ;

    public FotosFragmento() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fotos     = new ArrayList<>() ;
        binding   = FotosFragBinding.inflate( inflater , container , false );
        adaptador = new FotosRecViewAdaptador( container.getContext() , fotos ) ;
        binding.fotosGrid.setAdapter( adaptador );
        binding.fotosGrid.setLayoutManager( new GridLayoutManager( container.getContext() , 2 ));
        View vista = binding.getRoot() ;

        viewModel = new ViewModelProvider(this ).get(FotosViewModel.class) ;
        viewModel.getFotos().observe(getViewLifecycleOwner(), new Observer<List<FotoDeMarte>>() {
            @Override
            public void onChanged(List<FotoDeMarte> fotoDeMartes) {
                if ( fotoDeMartes != null ) {
                    fotos= fotoDeMartes ;
                    adaptador.actualizarFotos( fotoDeMartes );
                }
            }
        });
        viewModel.obtenerlasFotosDeMarte();

        return vista ;
    }
    public void actualizarFragmento( List<FotoDeMarte> fotos ){
        this.fotos = fotos ;
        adaptador.actualizarFotos( this.fotos );
    }



}