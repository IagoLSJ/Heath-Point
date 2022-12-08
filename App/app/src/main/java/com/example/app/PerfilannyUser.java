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

import com.example.app.model.Idoso;
import com.example.app.model.TakeCare;
import com.example.app.ui.AddDroug;
import com.example.app.ui.Loginacess;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.common.subtyping.qual.Bottom;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class PerfilannyUser extends AppCompatActivity {
    private TextView nome, cpf, email, newOldMan;
    private EditText editCPF;
    private Button btn_add, logout;
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
        logout = findViewById(R.id.buttonconperfil);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseFirestore.getInstance().collection("cuidador").whereEqualTo("email", user.getEmail()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
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
        FirebaseAuth.getInstance().getCurrentUser().
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String[] id = new String[1];
                final Idoso[] idoso = new Idoso[1];
                try {
                    FirebaseFirestore.getInstance().collection("idoso")
                            .whereEqualTo("cpf", editCPF.getText().toString())
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()){
                                        for  (QueryDocumentSnapshot document : task.getResult()) {
                                            String nome = document.getString("nome");
                                            String cpf = document.getString("cpf");
                                            String email = document.getString("email");
                                            String password = document.getString("password");
                                            String uuid = document.getString("uuid");
                                            id[0] = document.getId();
                                            idoso[0] = new Idoso(uuid,nome,cpf,email,password);
                                            break;
                                        }
                                    }
                                }
                            });
                    FirebaseFirestore.getInstance()
                            .collection("idoso")
                            .document(id[0])
                            .update((Map<String, Object>) idoso[0]);
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

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), Loginacess.class));
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