package com.example.app.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.app.R;

import java.util.ArrayList;
import java.util.Map;

public class RemedioAdpter extends RecyclerView.Adapter<RemedioViewHolder> {
    private ArrayList<Remedio> remedios;



    public RemedioAdpter(   ArrayList<Remedio> remedios) {
        this.remedios = remedios;
    }

    @NonNull
    @Override
    public RemedioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_list,parent, false);
        RemedioViewHolder remedioViewHolder = new RemedioViewHolder(view);
        return remedioViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RemedioViewHolder holder, int position) {
        Remedio remedio = remedios.get(position);
        holder.nome.setText(remedio.getNome());
        holder.hora.setText(remedio.getHorario());
    }

    @Override
    public int getItemCount() {
        return remedios.size();
    }
}
