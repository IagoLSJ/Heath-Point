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

public class UserDAO {
    private static UserDAO dao = null;
    private static MainActivity mainActivity;
    private FirebaseFirestore db;
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private static String userAuth;



}
