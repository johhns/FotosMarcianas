package com.developer.johhns.fotosmarcianas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.developer.johhns.fotosmarcianas.databinding.FotosFragBinding;

import java.util.ArrayList;
import java.util.List;


public class FotosFragmento extends Fragment {

    FotosFragBinding binding   ;
    FotosRecViewAdaptador adaptador ;
    FotosViewModel        viewModel = new FotosViewModel();

    public FotosFragmento() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(this ).get(FotosViewModel.class) ;

        binding   = FotosFragBinding.inflate( inflater , container , false );
        adaptador = new FotosRecViewAdaptador( container.getContext() ) ;
        binding.fotosGrid.setAdapter( adaptador );
        binding.fotosGrid.setLayoutManager( new GridLayoutManager( container.getContext() , 2 ));
        View vista = binding.getRoot() ;
        return vista ;


      //  return inflater.inflate(R.layout.fotos_frag, container, false);
    }
}