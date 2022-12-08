package com.example.app.model;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.R;

public class RemedioEditViewHolder extends RecyclerView.ViewHolder {
    public TextView nome, hora;
    public ImageButton edit, delete;

    public TextView getNome() {
        return nome;
    }

    public TextView getHora() {
        return hora;
    }

    public ImageButton getEdit() {
        return edit;
    }

    public ImageButton getDelete() {
        return delete;
    }

    public RemedioEditViewHolder(@NonNull View itemView) {
        super(itemView);
        nome = itemView.findViewById(R.id.remedio);
        hora = itemView.findViewById(R.id.hora);
        edit = itemView.findViewById(R.id.edit);
        delete = itemView.findViewById(R.id.delete);


    }
}
