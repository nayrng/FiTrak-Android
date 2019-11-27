package com.rng13.fittrak_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;


public class View_Workout_Details extends AppCompatActivity {

    String WORKOUT_TITLE;
    String WORKOUT_DESC;
    String WORKOUT_TRAINER;

    TextView title;
    TextView desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__workout__details);

        WORKOUT_TITLE = getIntent().getStringExtra("workout_name");
        WORKOUT_DESC = getIntent().getStringExtra("workout_details");
        WORKOUT_TRAINER = getIntent().getStringExtra("workout_trainer");

        title = findViewById(R.id.view_workout_details_title);
        desc = findViewById(R.id.view_workout_details_description);

        title.setText(WORKOUT_TITLE + " assigned by " + WORKOUT_TRAINER);
        desc.setText(WORKOUT_DESC);

    }
}
