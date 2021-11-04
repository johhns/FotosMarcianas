package com.developer.johhns.fotosmarcianas;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FotosViewModel extends ViewModel {

    private MutableLiveData<MarteApiEstado> _estado = new MutableLiveData<MarteApiEstado>();

    private MutableLiveData<List<FotoDeMarte>> _fotos;

    public FotosViewModel() {
        Log.i("VIEW-MODEL","INICIALIZANDO EL VIEW-MODEL *********************");
        _fotos = new MutableLiveData<>();
        //obtenerlasFotosDeMarte();
        Log.i("VIEW-MODEL","************   VIEW-MODEL INICIADO  *********************");
    }

    public MutableLiveData<MarteApiEstado> getEstado(){
        Log.i("VIEW-MODEL","RETORNANDO EL ESTADO *********************");
        return _estado ;
    }

    public MutableLiveData<List<FotoDeMarte>> getFotos(){
        Log.i("VIEW-MODEL","RETORNANDO LAS FOTOS : "  );
        return _fotos ;
    }

    public void obtenerlasFotosDeMarte() {
        Log.i("VIEW-MODEL","INICIANDO RETROFIT *********************");
        _estado.setValue(MarteApiEstado.LOADING);
        FotosApiAdaptador adaptador = new FotosApiAdaptador();
        FotosInterfaseApi endpoint =adaptador.establecerConexion() ;
        Call<List<FotoDeMarte>> fotoResponseCall = endpoint.obtenerFotos() ;
        fotoResponseCall.enqueue(new Callback<List<FotoDeMarte>>() {
            @Override
            public void onResponse(Call<List<FotoDeMarte>> call, Response<List<FotoDeMarte>> response) {
                Log.i("VIEW-MODEL","INICIO DE RESPONSE RETROFIT *********************");
                _fotos.postValue( response.body() );
                _estado.postValue( MarteApiEstado.DONE );
                Log.i("VIEW-MODEL","FIN DE RESPONSE RETROFIT : " + response.body().size() ) ;
            }

            @Override
            public void onFailure(Call<List<FotoDeMarte>> call, Throwable t) {
                _estado.postValue( MarteApiEstado.ERROR );
                Log.i("VIEW-MODEL","ERROR : " + t.getMessage() );
            }
        });
        Log.i("VIEW-MODEL","FINAL DE RETROFIT  *********************");
    }

}
