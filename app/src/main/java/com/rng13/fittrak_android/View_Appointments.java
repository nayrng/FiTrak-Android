package com.rng13.fittrak_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class View_Appointments extends AppCompatActivity {

    FirebaseAuth mAuth;
    DatabaseReference db;
    public RecyclerView.Adapter mAdapter;
    public RecyclerView mRecyclerView;
    public RecyclerView.LayoutManager mLayoutManager;

    ArrayList<String> appt_list;

    String USER_NAME;
    String TRAINER_NAME;

    TextView page_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__appointments2);

        USER_NAME = getIntent().getStringExtra("username");
        TRAINER_NAME = getIntent().getStringExtra("trainer_uname");

        db = FirebaseDatabase.getInstance().getReference().child("APPOINTMENTS");

        mRecyclerView = findViewById(R.id.View_Appointments_RecyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        appt_list = new ArrayList<>();

        page_title = findViewById(R.id.View_Appts_Title);

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    if (snap.getValue(String.class).equals(USER_NAME)) {
                        appt_list.add(snap.getKey());
                    }
                }

                if (appt_list.isEmpty()) {
                    page_title.setText("You don't have any appointments!");
                } else if (appt_list.size() == 1) {
                    page_title.setText("You only have 1 upcoming appointment");
                } else {
                    page_title.setText("You have " + appt_list.size() + " appointments");
                }
                mAdapter = new View_Appointments_Adapter(appt_list);
                mRecyclerView.setAdapter(mAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
