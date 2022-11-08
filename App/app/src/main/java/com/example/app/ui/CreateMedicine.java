package com.example.app.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.app.DAO.RemedioDAO;
import com.example.app.R;
import com.example.app.model.Remedio;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class CreateMedicine extends AppCompatActivity {
    private TextInputEditText nome, descricao, horario;
    private Button btn;
    private RemedioDAO dao = RemedioDAO.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_medicine);
        getSupportActionBar().hide();
        carregar();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nome.getText().toString().isEmpty() ||  descricao.getText().toString().isEmpty()  || horario.getText().toString().isEmpty() ){
                    Toast.makeText(getApplicationContext(), "Campos em branco", Toast.LENGTH_LONG).show();
                }else{
                    dao.insert(new Remedio(nome.getText().toString(), descricao.getText().toString(), horario.getText().toString()));
                    startActivity(new Intent(CreateMedicine.this, listMedicine.class));
                }
            }
        });

    }

    public void carregar(){
        nome = findViewById(R.id.nome_input);
        descricao = findViewById(R.id.descricao_input);
        horario = findViewById(R.id.horario_input);
        btn = findViewById(R.id.btn);

    }
}