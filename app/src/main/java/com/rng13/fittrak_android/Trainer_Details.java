package com.rng13.fittrak_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Trainer_Details extends AppCompatActivity {

    DatabaseReference db;
    FirebaseAuth mAuth;

    String CLIENT_USER_NAME;
    String TRAINER_USER_NAME;
    ArrayList<String> trainer_client_list;

    String f_name;
    String l_name;
    Long age;
    Long exp;
    String email;
    String about;
    String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer__details);

        Boolean has_trainer = getIntent().getBooleanExtra("DO_I_HAVE_TRAINER", false);

        trainer_client_list = new ArrayList<>();

        db = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        CLIENT_USER_NAME = getIntent().getStringExtra("USER_NAME");
        TRAINER_USER_NAME = getIntent().getStringExtra("trainer_uname");

        f_name = getIntent().getStringExtra("trainer_fname");
        l_name = getIntent().getStringExtra("trainer_lname");
        age = getIntent().getLongExtra("trainer_age", 0);
        exp = getIntent().getLongExtra("trainer_exp", 0);
        gender = getIntent().getStringExtra("trainer_gender");
        email = getIntent().getStringExtra("trainer_email");
        about = getIntent().getStringExtra("trainer_about");

        final TextView trainer_name, trainer_age, trainer_years, trainer_email, trainer_gender, trainer_bio;
        trainer_name = findViewById(R.id.trainer_name);
        trainer_age = findViewById(R.id.trainer_age);
        trainer_years = findViewById(R.id.trainer_years);
        trainer_email = findViewById(R.id.trainer_email);
        trainer_gender = findViewById(R.id.trainer_gender);
        trainer_bio = findViewById(R.id.trainer_bio);

        trainer_name.setText(f_name + " " + l_name);
        trainer_age.setText("Age: "+age);
        trainer_years.setText("Years of Experience: " + exp);
        trainer_email.setText(""+email);
        trainer_gender.setText("Gender: " + gender);
        trainer_bio.setText(about);

        Button signup = findViewById(R.id.trainer_join);
        ImageView people = findViewById(R.id.people);
        if (has_trainer) {
            signup.setVisibility(View.GONE);
            people.setVisibility(View.VISIBLE);
        } else {
            signup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TRAINER_OBJ trainer = new TRAINER_OBJ(TRAINER_USER_NAME, email, f_name, l_name, age, exp, gender, about);
                    join_trainer(trainer, CLIENT_USER_NAME);

                    Intent intent = new Intent(getApplicationContext(), LandingPage.class);
                    intent.putExtra("HAS_TRAINER", true);
                    startActivity(intent);
                }
            });
        }
    }

    private void join_trainer(final TRAINER_OBJ trainer, final String client_name) {
        final DatabaseReference client_list_ref = db.child("TRAINERS").child(trainer.USERNAME);

        final ArrayList<String> trainer_client_list = new ArrayList<>();

        client_list_ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.hasChild("clients")) {
                    trainer_client_list.add(client_name.split("\\@")[0]);
                    update_clients(trainer_client_list, trainer.USERNAME, client_name.split("\\@")[0]);
                }
                else if (dataSnapshot.hasChild("clients")) {
                    for (DataSnapshot snap: dataSnapshot.child("clients").getChildren()) {
                        trainer_client_list.add(snap.getValue(String.class));
                    }
                    trainer_client_list.add(client_name.split("\\@")[0]);
                    update_clients(trainer_client_list, trainer.USERNAME, client_name.split("\\@")[0]);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void update_clients(ArrayList<String> client_list, String trainer, String client) {
        db.child("TRAINERS").child(trainer).child("clients").setValue(client_list);


        db.child("CLIENTS").child(client).child("TRAINER_USERNAME").setValue(trainer);

    }

}
