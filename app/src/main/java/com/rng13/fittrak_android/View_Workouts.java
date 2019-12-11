package com.rng13.fittrak_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class View_Workouts extends AppCompatActivity {

    FirebaseAuth mAuth;
    DatabaseReference db;

    FirebaseUser user;

    public RecyclerView.Adapter mAdapter;
    public RecyclerView mRecyclerView;
    public RecyclerView.LayoutManager mLayoutManager;

    ArrayList<WORKOUT_OBJ> workout_list;

    String USER_NAME;
    String TRAINER_NAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__workouts);

        USER_NAME = getIntent().getStringExtra("USER_NAME");
        TRAINER_NAME = getIntent().getStringExtra("trainer_uname");

        user = mAuth.getInstance().getCurrentUser();
        db = FirebaseDatabase.getInstance().getReference().child("WORKOUTS");

        mRecyclerView = findViewById(R.id.workouts_recycler);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        workout_list = new ArrayList<>();

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                workout_list.clear();
                for (DataSnapshot snap: dataSnapshot.getChildren()) {
                    if (snap.child("username").getValue(String.class).equals(TRAINER_NAME)) {
                        String workout_title = snap.child("name").getValue(String.class);
                        String workout_details = snap.child("description").getValue(String.class);
                        String workout_trainer = snap.child("username").getValue(String.class);
                        WORKOUT_OBJ obj = new WORKOUT_OBJ(workout_title, workout_details, workout_trainer);
                        workout_list.add(obj);
                    }

                }

                if (workout_list.isEmpty()) {
                    findViewById(R.id.no_workouts).setVisibility(View.VISIBLE);
                }

                mAdapter = new View_Workout_Adapter(workout_list);
                mRecyclerView.setAdapter(mAdapter);
                mRecyclerView.addItemDecoration(new DividerItemDecoration(mRecyclerView.getContext(), DividerItemDecoration.VERTICAL));
                ((View_Workout_Adapter) mAdapter).setOnItemClickListener(new View_Workout_Adapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        System.out.println(workout_list.get(position).WORKOUT_TRAINER);
                        WORKOUT_OBJ obj = workout_list.get(position);
                        Intent intent = new Intent(getApplicationContext(), View_Workout_Details.class);
                        intent.putExtra("workout_name", obj.WORKOUT_TITLE);
                        intent.putExtra("workout_details", obj.WORKOUT_DETAILS);
                        intent.putExtra("workout_trainer", obj.WORKOUT_TRAINER);
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
