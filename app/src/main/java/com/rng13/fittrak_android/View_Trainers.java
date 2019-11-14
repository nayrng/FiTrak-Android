package com.rng13.fittrak_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class View_Trainers extends AppCompatActivity {

    FirebaseAuth mAuth;
    DatabaseReference db;

    FirebaseUser user;

    public RecyclerView.Adapter mAdapter;
    public RecyclerView mRecyclerView;
    public RecyclerView.LayoutManager mLayoutManager;

    ArrayList<TRAINER_OBJ> trainer_list;

    String USER_NAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__trainers);

        user = mAuth.getInstance().getCurrentUser();
        db = FirebaseDatabase.getInstance().getReference().child("TRAINERS");


        mRecyclerView = findViewById(R.id.avail_trainers_recycler);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        trainer_list = new ArrayList<>();

        USER_NAME = getIntent().getStringExtra("USER_NAME");

        System.out.println("YEET " + USER_NAME);


        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                trainer_list.clear();
                for (DataSnapshot snap: dataSnapshot.getChildren()) {
                    String USERNAME = snap.getKey();
                    String EMAIL = snap.child("email").getValue(String.class);
                    String F_NAME = snap.child("first_name").getValue(String.class);
                    String L_NAME = snap.child("last_name").getValue(String.class);
                    int AGE = snap.child("age").getValue(Integer.class);
                    int EXP = snap.child("experience").getValue(Integer.class);
                    String GENDER = snap.child("gender").getValue(String.class);
                    String ABOUT_ME = snap.child("about_me").getValue(String.class);

                    TRAINER_OBJ obj = new TRAINER_OBJ(USERNAME, EMAIL, F_NAME, L_NAME, AGE, EXP, GENDER, ABOUT_ME);

                    trainer_list.add(obj);

                }

                mAdapter = new View_Trainers_Adapter(trainer_list);
                mRecyclerView.setAdapter(mAdapter);
                mRecyclerView.addItemDecoration(new DividerItemDecoration(mRecyclerView.getContext(), DividerItemDecoration.VERTICAL));
                ((View_Trainers_Adapter)mAdapter).setOnItemClickListener(new View_Trainers_Adapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        // todo: trainer profiles
                        System.out.println(trainer_list.get(position).USERNAME);
                        TRAINER_OBJ trainer = trainer_list.get(position);
                        Intent intent = new Intent(getApplicationContext(), Trainer_Details.class);
                        intent.putExtra("trainer_uname", trainer_list.get(position).USERNAME);
                        intent.putExtra("trainer_gender", trainer.GENDER);
                        intent.putExtra("trainer_fname", trainer.FIRST_NAME);
                        intent.putExtra("trainer_lname", trainer.LAST_NAME);
                        intent.putExtra("trainer_age", trainer.AGE);
                        intent.putExtra("trainer_exp", trainer.EXPERIENCE);
                        intent.putExtra("trainer_email", trainer.EMAIL);
                        intent.putExtra("trainer_about", trainer.ABOUT_ME);
                        intent.putExtra("USER_NAME", USER_NAME);
                        startActivity(intent);

                    }
                });
            }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
