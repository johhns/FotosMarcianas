package com.developer.johhns.fotosmarcianas;


import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

//import com.developer.johhns.fotosmarcianas.databinding.GridViewItemBinding;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FotosRecViewAdaptador extends RecyclerView.Adapter<FotosRecViewAdaptador.ViewHolder> {

    List<FotoDeMarte>   fotos ;
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
        View vista = LayoutInflater.from(parent.getContext()).inflate( R.layout.grid_view_item , parent , false ) ;
        return new ViewHolder( vista ) ; //ViewHolder(  binding.getRoot() ) ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //holder.getBinding().executePendingBindings();
        holder.titulo.setText( fotos.get( position ).id );
        Glide.with(context)
                .load( fotos.get(position).img_src )
                .apply(RequestOptions.centerCropTransform())
                .into(holder.foto);
        /*
        Picasso.with(context.getApplicationContext())
                .load( fotos.get(position).img_src )
                .placeholder(R.drawable.ic_broken_image)
                .into( holder.foto );

         */
    }

    @Override
    public int getItemCount() {
        if ( fotos != null ) {
            return fotos.size();
        } else {
            return 0 ;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView  foto ;
        TextView   titulo ;
        // private GridViewItemBinding binding ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //binding =DataBindingUtil.bind( itemView ) ;
            //foto = binding.marsImage ;
            foto   = itemView.findViewById( R.id.mars_image ) ;
            titulo = itemView.findViewById( R.id.titulo ) ;
        }
        /*
        public ViewDataBinding getBinding(){
           return binding ;
        }

       void unbind(){
           if (binding != null) {
               binding.unbind();
           }
       }
       */

    }
}