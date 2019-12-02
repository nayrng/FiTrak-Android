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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__appointments2);

        USER_NAME = getIntent().getStringExtra("username");
        TRAINER_NAME = getIntent().getStringExtra("trainer_uname");

        //db = FirebaseDatabase.getInstance().getReference().child("APPOINTMENTS");
        db = FirebaseDatabase.getInstance().getReference().child("APPTS");

        mRecyclerView = findViewById(R.id.View_Appointments_RecyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        appt_list = new ArrayList<>();

        page_title = findViewById(R.id.View_Appts_Title);

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot snap : dataSnapshot.getChildren()) {
//                    if (snap.getValue(String.class).equals(USER_NAME)) {
//                        appt_list.add(snap.getKey());
//                    }
//                }
                appt_list.clear();

                for (DataSnapshot snap: dataSnapshot.getChildren()) {
                    System.out.println("APPT KEY " + snap.getKey());
                    if (snap.getKey().equals(USER_NAME)) {

                        for (DataSnapshot snapshot : snap.getChildren()) {
                            String date = null;
                            String time = null;
                            //appt_list.add(new APPOINTMENT_OBJ(snapshot.getKey(), snapshot.getValue(String.class)));
                            //Map<String, Object> map = (Map<String, Object>) snapshot.getValue();
//                            System.out.println(map.get());
                            Map<String, Object> map = (Map<String, Object>) snapshot.getValue();
                            for(Object val : map.values()) {
                                System.out.println(val);
                                time = val.toString();
                            }
                            for(String val: map.keySet()) {
                                System.out.println(val);
                                date = val;
                            }
                            appt_list.add(new APPOINTMENT_OBJ(date, time));
                        }

                    }
//                    if (snap.getKey().equals(USER_NAME)) {
//                        APPOINTMENT_OBJ obj = snap.getValue(APPOINTMENT_OBJ.class);
//                        appt_list.add(obj);
//                    }
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
                mRecyclerView.addItemDecoration(new DividerItemDecoration(mRecyclerView.getContext(), DividerItemDecoration.VERTICAL));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
