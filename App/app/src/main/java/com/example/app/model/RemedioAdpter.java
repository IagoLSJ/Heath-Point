package com.example.app.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.DAO.RemedioDAO;
import com.example.app.R;

import java.util.ArrayList;

public class RemedioAdpter extends RecyclerView.Adapter<RemedioViewHolder> {
    private ArrayList<Remedio> remedios;


    public RemedioAdpter(  ArrayList<Remedio> remedios) {
        this.remedios = remedios;
    }

    @NonNull
    @Override
    public RemedioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_list,parent, false);
        RemedioViewHolder remedioViewHolder = new RemedioViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Campos em branco" , Toast.LENGTH_LONG).show();
            }
        });
        return remedioViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RemedioViewHolder holder, int position) {
        Remedio remedio = remedios.get(position);
        holder.nome.setText(remedio.getNome());
        holder.descricao.setText(remedio.getDescricao());
        holder.hora.setText(remedio.getEntervaloEmHoras());
    }

    @Override
    public int getItemCount() {
        return remedios.size();
    }
}