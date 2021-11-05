package com.developer.johhns.fotosmarcianas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
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
        //fotos     = new ArrayList<>() ;
        //binding   = FotosFragBinding.inflate( inflater , container , false );
        binding   = DataBindingUtil.inflate( inflater , R.layout.fotos_frag , container , false) ;
        viewModel = new ViewModelProvider( requireActivity() ).get(FotosViewModel.class) ;
        binding.setViewModel( viewModel );
        binding.setLifecycleOwner(this);
        binding.executePendingBindings();
        return binding.getRoot() ;
    }

    public void actualizarFragmento( List<FotoDeMarte> fotos ){
        this.fotos = fotos ;
        adaptador.actualizarFotos( this.fotos );
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.obtenerlasFotosDeMarte();
        viewModel.getFotos().observe( requireActivity(), new Observer<List<FotoDeMarte>>() {
            @Override
            public void onChanged(List<FotoDeMarte> fotoDeMartes) {
                if ( fotoDeMartes != null ) {
                    fotos= fotoDeMartes ;
                    adaptador.actualizarFotos( fotoDeMartes );
                }
            }
        });
        adaptador = new FotosRecViewAdaptador( requireActivity() , fotos ) ;
        binding.fotosGrid.setAdapter( adaptador );
        binding.fotosGrid.setLayoutManager( new GridLayoutManager( requireActivity() , 2 ));
    }




}