package com.example.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.app.model.TakeCare;
import com.example.app.ui.AddDroug;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.common.subtyping.qual.Bottom;

import java.util.Objects;

public class PerfilannyUser extends AppCompatActivity {
    private TextView nome, cpf, email, newOldMan;
    private EditText editCPF;
    private Button btn_add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfilanny_user);
        getSupportActionBar().hide();
        menu();
        nome = findViewById(R.id.textInputEditText);
        cpf = findViewById(R.id.editTextNumber2);
        email = findViewById(R.id.editTextTextEmailAddress3);
        newOldMan = findViewById(R.id.textView3);
        editCPF = findViewById(R.id.input_cpf_idoso);
        btn_add = findViewById(R.id.btn_add);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseFirestore.getInstance().collection("users").whereEqualTo("email", user.getEmail()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){

                   for (QueryDocumentSnapshot document: task.getResult()){
                       nome.setText((CharSequence) document.get("nome"));
                       cpf.setText((CharSequence) document.get("cpf"));
                       email.setText((CharSequence) document.get("email"));
                       if ((boolean)document.get("isCaregiver")){
                           newOldMan.setVisibility(View.VISIBLE);
                           editCPF.setVisibility(View.VISIBLE);
                           btn_add.setVisibility(View.VISIBLE);
                       }
                   }

                }
            }
        });
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FirebaseFirestore.getInstance().collection("takeCare").add(new TakeCare(cpf.getText().toString(), editCPF.getText().toString()));
                    editCPF.setText("");
                    Snackbar.make(findViewById(R.id.menu), "Idoso adicionado com sucesso",
                                    Snackbar.LENGTH_SHORT)
                            .show();

                }catch (Exception e){
                    Snackbar.make(findViewById(R.id.menu), "Erro: CPF invalido ou inexistente",
                                    Snackbar.LENGTH_SHORT)
                            .show();
                }


            }
        });
    }

    public void menu() {
        BottomNavigationView menu;
        menu = findViewById(R.id.menu);
        menu.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.homeId:
                        startActivity(new Intent(getApplicationContext(), Homeuser.class));
                        break;
                    case R.id.addId:
                        startActivity(new Intent(getApplicationContext(), AddDroug.class));
                        break;
                    case R.id.searchId:
                        startActivity(new Intent(getApplicationContext(), Homeuser.class));
                        break;
                    case R.id.profileId:
                        startActivity(new Intent(getApplicationContext(), PerfilannyUser.class));
                    default:
                        return false;
                }
                return false;
            }
        });
    }
}