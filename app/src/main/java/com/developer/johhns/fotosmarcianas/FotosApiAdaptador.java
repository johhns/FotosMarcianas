package com.developer.johhns.fotosmarcianas;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FotosApiAdaptador {

    public FotosInterfaseApi establecerConexion() {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://android-kotlin-fun-mars-server.appspot.com/")
                .addConverterFactory( GsonConverterFactory.create() )
                .build();

        return retrofit.create( FotosInterfaseApi.class ) ;



    }

}
