package com.rng13.fittrak_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
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
import java.util.Map;

public class View_Appointments extends AppCompatActivity {

    FirebaseAuth mAuth;
    DatabaseReference db;
    public RecyclerView.Adapter mAdapter;
    public RecyclerView mRecyclerView;
    public RecyclerView.LayoutManager mLayoutManager;

    ArrayList<APPOINTMENT_OBJ> appt_list;

    String USER_NAME;
    String TRAINER_NAME;

    TextView page_title;
    TextView appt_details;

    String date = null;
    String time = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__appointments2);

        USER_NAME = getIntent().getStringExtra("username");
        TRAINER_NAME = getIntent().getStringExtra("trainer_uname");

        //db = FirebaseDatabase.getInstance().getReference().child("APPOINTMENTS");
        db = FirebaseDatabase.getInstance().getReference().child("APPTS");

//        mRecyclerView = findViewById(R.id.View_Appointments_RecyclerView);
//        mLayoutManager = new LinearLayoutManager(this);
//        mRecyclerView.setLayoutManager(mLayoutManager);

        appt_list = new ArrayList<>();

        page_title = findViewById(R.id.View_Appts_Title);
        appt_details = findViewById(R.id.appointment_details);

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                appt_list.clear();

                for (DataSnapshot snap: dataSnapshot.getChildren()) {
                    System.out.println("APPT KEY " + snap.getKey());
                    if (snap.getKey().equals(USER_NAME)) {

//                        String date = null;
//                        String time = null;

                        System.out.println(snap.getValue());

                        Map<String, Object> map = (Map<String, Object>) snap.getValue();
                        for (Object val : map.values()) {
                            time = val.toString();
                        }
                        for (String val : map.keySet()) {
                            date = val;
                        }

                        //appt_list.add(new APPOINTMENT_OBJ(date, time));

                    }
                }

//                if (appt_list.isEmpty()) {
//                    page_title.setText("You don't have any appointments!");
//                } else if (appt_list.size() == 1) {
//                    page_title.setText("You have 1 upcoming appointment");
//                } else {
//                    page_title.setText("You have " + appt_list.size() + " appointments");
//                }

                if (date == null && time == null) {
                    page_title.setText("You don't have any appointments!");
                } else {
                    page_title.setText("You have an upcoming appointment");
                    appt_details.setText("Your appointment is on " + date + " at " + time);
                }

//                mAdapter = new View_Appointments_Adapter(appt_list);
//                mRecyclerView.setAdapter(mAdapter);
//                mRecyclerView.addItemDecoration(new DividerItemDecoration(mRecyclerView.getContext(), DividerItemDecoration.VERTICAL));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
