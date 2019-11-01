package com.rng13.fittrak_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Trainer_Details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer__details);

        String f_name = getIntent().getStringExtra("trainer_fname");
        String l_name = getIntent().getStringExtra("trainer_lname");
        int age = getIntent().getIntExtra("trainer_age", 0);
        int exp = getIntent().getIntExtra("trainer_exp", 0);
        String email = getIntent().getStringExtra("trainer_email");
        String about = getIntent().getStringExtra("trainer_about");

        TextView trainer_name, trainer_age, trainer_years, trainer_email, trainer_bio;
        trainer_name = findViewById(R.id.trainer_name);
        trainer_age = findViewById(R.id.trainer_age);
        trainer_years = findViewById(R.id.trainer_years);
        trainer_email = findViewById(R.id.trainer_email);
        trainer_bio = findViewById(R.id.trainer_bio);

        trainer_name.setText(f_name + " " + l_name);
        trainer_age.setText("Age: "+age);
        trainer_years.setText("Years of Experience: " + exp);
        trainer_email.setText(""+email);
        trainer_bio.setText(about);

    }
}
