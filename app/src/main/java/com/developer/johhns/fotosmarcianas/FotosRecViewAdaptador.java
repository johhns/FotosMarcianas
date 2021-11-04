package com.developer.johhns.fotosmarcianas;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

//import com.developer.johhns.fotosmarcianas.databinding.GridViewItemBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FotosRecViewAdaptador extends RecyclerView.Adapter<FotosRecViewAdaptador.ViewHolder> {

    List<FotoDeMarte>   fotos ;
    //GridViewItemBinding binding ;
    Context             context ;



    public FotosRecViewAdaptador( Context contexto , List<FotoDeMarte> fotos) {
        this.fotos     = fotos ;
        this.context   = contexto ;
    }

   public void actualizarFotos( List<FotoDeMarte> fotos ){
      this.fotos = fotos ;
      this.notifyDataSetChanged();
   }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //binding = GridViewItemBinding.inflate( LayoutInflater.from ( parent.getContext() ) ) ;
        View vista = LayoutInflater.from(parent.getContext()).inflate( R.layout.grid_view_item , parent , false ) ;
        return new ViewHolder( vista ) ; //ViewHolder(  binding.getRoot() ) ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       Picasso.with(context.getApplicationContext())
              .load( fotos.get(position).urlImagen )
              .placeholder(R.drawable.ic_broken_image)
              .into( holder.foto );
    }

    @Override
    public int getItemCount() {
        return fotos.size() ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView foto ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //foto = binding.marsImage ;
            foto = itemView.findViewById( R.id.mars_image ) ;
        }
    }
}
