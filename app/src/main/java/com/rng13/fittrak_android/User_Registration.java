package com.rng13.fittrak_android;

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

public class User_Registration extends AppCompatActivity {

    DatabaseReference db;
    FirebaseAuth mAuth;

    public String EMAIL;
    public String PASSWORD;
    public String F_NAME;
    public String L_NAME;
    public String USERNAME;

    EditText reg_email;
    EditText reg_password;
    EditText reg_fname;
    EditText reg_lname;
    EditText reg_username;
    ArrayList<String> workouts;
    ArrayList<String> appts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__registration);

        db = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        reg_email = (EditText) findViewById(R.id.reg_email);
        //EMAIL = reg_email.getText().toString();
        reg_password = (EditText) findViewById(R.id.reg_password);
        //PASSWORD = reg_password.getText().toString();
        reg_fname = (EditText) findViewById(R.id.reg_first_name);
        reg_lname = (EditText) findViewById(R.id.reg_last_name);

        reg_username = (EditText) findViewById(R.id.reg_username);

        workouts = new ArrayList<>();
        appts = new ArrayList<>();

        Button reg_confirm = (Button) findViewById(R.id.reg_continue);
        reg_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EMAIL = reg_email.getText().toString();
                PASSWORD = reg_password.getText().toString();
                F_NAME = reg_fname.getText().toString();
                L_NAME = reg_lname.getText().toString();
                USERNAME = reg_username.getText().toString();

                System.out.println(EMAIL);
                Log.d("HELLO", EMAIL);
                System.out.println(PASSWORD);
                Log.d("HELLO", PASSWORD);
                signIn(EMAIL, PASSWORD);
            }
        });
    }
    private void signIn(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("YEET", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            System.out.println(user);
                            workouts.add(0, "placeholder");
                            appts.add(0, "placeholder");
                            CLIENT_OBJ client = new CLIENT_OBJ(USERNAME, mAuth.getUid(), EMAIL, F_NAME, L_NAME, "NO_TRAINER", workouts, appts);
//                            CLIENT_OBJ client = new CLIENT_OBJ(mAuth.getUid(),
//                                    EMAIL, F_NAME, L_NAME, "no trainer", workouts, appts);
                            create_db_entry(client);
                            Intent intent = new Intent(getApplicationContext(), LandingPage.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("YEET", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            System.out.println("FUCK");
                        }
                    }
                });
    }

    private void create_db_entry(CLIENT_OBJ client) {
        db.child("CLIENTS").child(USERNAME).setValue(client);
    }
}
