package com.rng13.fittrak;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.mongodb.stitch.android.core.Stitch;
import com.mongodb.stitch.android.core.auth.providers.userpassword.UserPasswordAuthProviderClient;

public class User_Registration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__registration);

        UserPasswordAuthProviderClient emailPassClient = Stitch.getDefaultAppClient().getAuth().getProviderClient(
                UserPasswordAuthProviderClient.factory
        );

        EditText reg_email = (EditText) findViewById(R.id.reg_email);
        String EMAIL = reg_email.getText().toString();
        EditText reg_password = (EditText) findViewById(R.id.reg_password);
        String PASSWORD = reg_password.getText().toString();

        Button reg_confirm = (Button) findViewById(R.id.reg_confirm);
        reg_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailPassClient.registerWithEmail(EMAIL, PASSWORD)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Log.d("stitch", "SUCCESSFULLY REGISTERED YEET");
                                } else {
                                    Log.e("stitch", "ERROR REGISTERING:", task.getException());
                                }
                            }
                        });
            }
        });


    }
}
