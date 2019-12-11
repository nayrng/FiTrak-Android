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
    public String INTERESTS;

    EditText reg_email;
    EditText reg_password;
    EditText reg_fname;
    EditText reg_lname;
    EditText reg_username;
    EditText reg_interests;
    ArrayList<String> workouts;
    ArrayList<String> appts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__registration);

        db = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        reg_email = (EditText) findViewById(R.id.reg_email);
        reg_password = (EditText) findViewById(R.id.reg_password);
        reg_fname = (EditText) findViewById(R.id.reg_first_name);
        reg_lname = (EditText) findViewById(R.id.reg_last_name);

        reg_username = (EditText) findViewById(R.id.reg_username);

        reg_interests = (EditText) findViewById(R.id.reg_interests);

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

                INTERESTS = reg_interests.getText().toString();

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
                            Log.d("createUserWithEmail", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            CLIENT_OBJ client = new CLIENT_OBJ(mAuth.getUid(), EMAIL, F_NAME, L_NAME, "NO_TRAINER", INTERESTS);
                            create_db_entry(client);
                            Intent intent = new Intent(getApplicationContext(), LandingPage.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("createUserWithEmail", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void create_db_entry(CLIENT_OBJ client) {
        String email_parsed = EMAIL.split("\\@")[0];
        db.child("CLIENTS").child(email_parsed).setValue(client);
    }
}
