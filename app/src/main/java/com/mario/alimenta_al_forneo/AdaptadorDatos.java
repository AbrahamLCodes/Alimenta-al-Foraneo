package com.mario.alimenta_al_forneo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdaptadorDatos extends RecyclerView.Adapter<AdaptadorDatos.ViewHolder>{

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView itemInventario;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemInventario = itemView.findViewById(R.id.itemInventario);


        }
    }

    public List<listaModelo> listaItems;

    public AdaptadorDatos(List<listaModelo> listaItems) {
        this.listaItems = listaItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemInventario.setText(listaItems.get(position).getItem());

    }

    @Override
    public int getItemCount() {
        return listaItems.size();
    }
}
