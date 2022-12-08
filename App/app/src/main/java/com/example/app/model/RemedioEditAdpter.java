package com.example.app.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.EditCard;
import com.example.app.R;

import java.util.ArrayList;

public class RemedioEditAdpter extends RecyclerView.Adapter<RemedioEditViewHolder> {
    private ArrayList<Remedio> remedios;
private EditCard editCard;
    public RemedioEditAdpter(EditCard editCard , ArrayList<Remedio> remedios) {
        this.editCard = editCard;
        this.remedios = remedios;
    }

    @NonNull
    @Override
    public RemedioEditViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_list_edit,parent, false);
        RemedioEditViewHolder remedioViewHolder = new RemedioEditViewHolder(view);
        return remedioViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RemedioEditViewHolder holder, int position) {
        Remedio remedio = remedios.get(position);
        holder.nome.setText(remedio.getNome());
        holder.hora.setText(remedio.getHorario());

        holder.getEdit().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Remedio r  = remedios.get(holder.getAbsoluteAdapterPosition());
                editCard.editar(r);
            }
        });
        holder.getDelete().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Remedio r = remedios.get(holder.getAbsoluteAdapterPosition());
                editCard.delete(r);
            }
        });
    }

    @Override
    public int getItemCount() {
        return remedios.size();
    }
}
