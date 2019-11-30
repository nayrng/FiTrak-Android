package com.rng13.fittrak_android;

//import android.support.v7.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText user;
    EditText password;

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

//        workouts = new ArrayList<>();
//        appointments = new ArrayList<>();
//
//
//        CLIENT_OBJ client = new CLIENT_OBJ("uICnlZiercPqW6MvaObqmAoGigI3",
//                "yeet@yeet.com",
//                "yeet",
//                "haw",
//                null,
//                workouts,
//                appointments);
//        db.child("CLIENTS").child(client.USER_ID).setValue(client);

        user = (EditText) findViewById(R.id.email_main);
        password = (EditText) findViewById(R.id.password_main);

        final Button login = (Button) findViewById(R.id.Log_In);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = user.getText().toString();
                System.out.println("FUCK " + name);
                String pass = password.getText().toString();
                System.out.println("FUCK " + pass);
                sign_in(name, pass);
            }
        });


        Button registration = (Button) findViewById(R.id.Register);
        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), User_Registration.class);
                startActivity(intent);
            }
        });

    }
    private void sign_in(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("YEET", "LOGIN SUCCESSFUL");
                            Toast.makeText(getApplicationContext(), "LOGIN SUCCESS", Toast.LENGTH_LONG).show();
                            FirebaseUser user = mAuth.getCurrentUser();
//                            Intent intent = new Intent(getApplicationContext(), LandingPage.class);
//                            intent.putExtra("USER", user);
//                            startActivity(intent);
                            Intent intent = new Intent(getApplicationContext(), LandingPage.class);
                            startActivity(intent);
                        }
                        else {
                            Log.d("YEET", "LOGIN FAILED");
                            Toast.makeText(getApplicationContext(), "LOGIN FAIL", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
