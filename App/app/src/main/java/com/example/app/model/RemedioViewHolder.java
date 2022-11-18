package com.example.app.model;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.R;

public class RemedioViewHolder extends RecyclerView.ViewHolder {
    public TextView nome, hora;
    public RemedioViewHolder(@NonNull View itemView) {
        super(itemView);
        nome = itemView.findViewById(R.id.remedio);
        hora = itemView.findViewById(R.id.hora);
    }
}
