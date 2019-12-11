package com.rng13.fittrak_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class View_Appointments extends AppCompatActivity {

    DatabaseReference db;

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

        db = FirebaseDatabase.getInstance().getReference().child("APPTS");
        page_title = findViewById(R.id.View_Appts_Title);
        appt_details = findViewById(R.id.appointment_details);

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snap: dataSnapshot.getChildren()) {
                    System.out.println("APPT KEY " + snap.getKey());
                    if (snap.getKey().equals(USER_NAME)) {

                        Map<String, Object> map = (Map<String, Object>) snap.getValue();
                        for (Object val : map.values()) {
                            time = val.toString();
                        }
                        for (String val : map.keySet()) {
                            date = val;
                        }

                    }
                }


                if (date == null && time == null) {
                    page_title.setText("You don't have any appointments!");
                } else {
                    page_title.setText("You have an upcoming appointment");
                    appt_details.setText("Your appointment is on " + date + " at " + time);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
