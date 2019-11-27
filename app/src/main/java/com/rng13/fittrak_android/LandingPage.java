package com.rng13.fittrak_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class LandingPage extends AppCompatActivity {

    FirebaseAuth mAuth;
    DatabaseReference db;
    DatabaseReference trainer_db;

    FirebaseUser user;

    TextView welcome;
    CLIENT_OBJ client;

    Button view_appts;
    Button view_workouts;
    Button view_trainer_prof;
    Button view_available_trainers;

    String USER_NAME;

    String USER_EMAIL;

    String trainer_uname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        user = mAuth.getInstance().getCurrentUser();
        db = FirebaseDatabase.getInstance().getReference().child("CLIENTS");

        trainer_db = FirebaseDatabase.getInstance().getReference().child("TRAINERS");

        welcome = (TextView) findViewById(R.id.welcome_message);

        view_trainer_prof = (Button) findViewById(R.id.view_trainer_button);
        view_available_trainers = (Button) findViewById(R.id.view_available_trainers_button);
        view_appts = (Button) findViewById(R.id.view_appt_button);

        view_workouts = (Button) findViewById(R.id.view_workout_button);

        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String uid = snapshot.child("USER_ID").getValue(String.class);
                    System.out.println("HERE YA GO " + uid);
                    if (user.getUid().equals(uid)) {
//                        System.out.println(snapshot.child("EMAIL").getValue(String.class));
                        welcome.setText("Welcome,\n" + snapshot.child("FIRST_NAME").getValue(String.class));
                        //USER_NAME = snapshot.child("USER_NAME").getValue(String.class);
                        USER_EMAIL = snapshot.child("EMAIL").getValue(String.class);

                        trainer_uname = snapshot.child("TRAINER_USERNAME").getValue(String.class);

                        if ((snapshot.child("TRAINER_USERNAME").getValue(String.class)).equals("NO_TRAINER")) {
                            System.out.println("FUCK YES");
                            view_trainer_prof.setVisibility(View.GONE);
                        } else {
                            view_available_trainers.setVisibility(View.GONE);
                        }
                        System.out.println(snapshot.child("TRAINER_ID").getValue(String.class));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("FUCK", "loadPost:onCancelled", databaseError.toException());

            }
        };
        db.addListenerForSingleValueEvent(listener);
        view_available_trainers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), View_Trainers.class);
                intent.putExtra("USER_NAME",USER_EMAIL);
                startActivity(intent);
            }
        });

        view_workouts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), View_Workouts.class);
                intent.putExtra("USER_NAME", USER_EMAIL);
                intent.putExtra("trainer_uname", trainer_uname);
                startActivity(intent);
            }
        });

        view_appts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), View_Appointments.class);
                intent.putExtra("username", USER_EMAIL.split("\\@")[0]);
                startActivity(intent);
            }
        });

        view_trainer_prof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                trainer_db.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snap: dataSnapshot.getChildren()) {
                            if (snap.getKey().equals(trainer_uname)) {
                                Intent intent = new Intent(getApplicationContext(), Trainer_Details.class);
                                intent.putExtra("trainer_uname", snap.getKey());
                                intent.putExtra("trainer_gender", snap.child("gender").getValue(String.class));
                                intent.putExtra("trainer_fname", snap.child("first_name").getValue(String.class));
                                intent.putExtra("trainer_lname", snap.child("last_name").getValue(String.class));
                                intent.putExtra("trainer_age", snap.child("age").getValue(Integer.class));
                                intent.putExtra("trainer_exp", snap.child("experience").getValue(Integer.class));
                                intent.putExtra("trainer_email", snap.child("email").getValue(String.class));
                                intent.putExtra("trainer_about", snap.child("about_me").getValue(String.class));
                                intent.putExtra("DO_I_HAVE_TRAINER", true);
                                startActivity(intent);

                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

//                Intent intent = new Intent(getApplicationContext(), Trainer_Details.class);
//                intent.putExtra("has_trainer", true);
//                intent.putExtra("trainer_uname", trainer_uname);
//                startActivity(intent);
            }
        });
        //System.out.println(client.EMAIL);



    }
}
