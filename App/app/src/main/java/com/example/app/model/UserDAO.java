package com.example.app.model;

import androidx.annotation.NonNull;

import com.example.app.R;
import com.example.app.ui.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class UserDAO implements UserInterface{
    private static UserDAO dao = null;
    private static MainActivity mainActivity;
    private FirebaseFirestore db;
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private static String userAuth;

    private UserDAO(MainActivity mainActivity){
        UserDAO.mainActivity = mainActivity;
    }

    public static UserInterface getInstance(MainActivity mainActivity){
        if (dao==null){
            dao = new UserDAO(mainActivity);
        }
        return dao;
    }

    @Override
    public boolean addUser(User u) {
        Map<String, Object> user = new HashMap<>();
        auth.createUserWithEmailAndPassword(u.getEmail(), u.getPassword());
        user.put("UUID", auth.getUid());
        user.put("Nome", u.getNome());
        user.put("Email", u.getEmail());
        user.put("CPF", u.getCpf());
        user.put("IsCaregiver", u.getIsCaregiver());

        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        FirebaseAuth.getInstance().createUserWithEmailAndPassword(u.getEmail(), u.getPassword());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Snackbar.make(mainActivity.findViewById(R.id.buttonaindaloginacess), "Erro: CPF invalido ou inexistente",
                                        Snackbar.LENGTH_SHORT)
                                .show();
                    }
                });

        return false;
    }

    @Override
    public boolean editUser(User u) {
        return false;
    }

    @Override
    public User getUser(String userId) {
        final User[] u = new User[1];
        db.collection("users").whereEqualTo("UUID", UserDAO.userAuth)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (QueryDocumentSnapshot document: task.getResult()){
                            u[0] = new User((String) document.get("UUID"), (String) document.get("nome"), (String) document.get("CPF"), (String) document.get("Email"), "", (boolean) document.get("IsCaregiver"));
                        }
                    }
                });

        return u[0];
    }

    @Override
    public boolean login(String email, String password) {
        final boolean[] isAuth = new boolean[1];
        auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        UserDAO.userAuth = authResult.getUser().getUid();
                        isAuth[0] = true;
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Snackbar.make(mainActivity.findViewById(R.id.buttonaindaloginacess), "Erro: CPF invalido ou inexistente",
                                        Snackbar.LENGTH_SHORT)
                                .show();
                        isAuth[0] = false;
                    }
                });
        return isAuth[0];
    }

    @Override
    public String getAuthUser() {
        return userAuth;
    }

    @Override
    public boolean init() {
        if (db == null) db = FirebaseFirestore.getInstance();
        return true;
    }

    @Override
    public boolean close() {
        return false;
    }

    @Override
    public boolean isStarted() {
        return false;
    }
}
