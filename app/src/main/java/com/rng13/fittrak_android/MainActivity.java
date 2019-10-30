package com.rng13.fittrak_android;

//import android.support.v7.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    DatabaseReference db;
    ArrayList<String> workouts;
    ArrayList<String> appointments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance().getReference();

        workouts = new ArrayList<>();
        appointments = new ArrayList<>();


        CLIENT_OBJ client = new CLIENT_OBJ("uICnlZiercPqW6MvaObqmAoGigI3",
                "yeet@yeet.com",
                "yeet",
                "haw",
                null,
                workouts,
                appointments);
        db.child("CLIENTS").child(client.USER_ID).setValue(client);

    }
}
