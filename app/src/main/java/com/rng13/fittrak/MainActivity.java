package com.rng13.fittrak;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.mongodb.stitch.android.core.auth.StitchUser;
import com.mongodb.stitch.android.core.Stitch;
import com.mongodb.stitch.android.core.StitchAppClient;
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoClient;
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoCollection;
import com.mongodb.stitch.core.auth.providers.anonymous.AnonymousCredential;
import com.mongodb.stitch.core.services.mongodb.remote.RemoteUpdateOptions;
import com.mongodb.stitch.core.services.mongodb.remote.RemoteUpdateResult;

import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    StitchAppClient stitchClient = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button debug_button_main = (Button) findViewById(R.id.DEBUG_REG);
        debug_button_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LandingPage.class);
                startActivity(intent);
            }
        });

        Button registration = (Button) findViewById(R.id.Register);
        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), User_Registration.class);
                startActivity(intent);
            }
        });

//        final StitchAppClient client =
//                Stitch.initializeDefaultAppClient("fittrak-qynxn");


//        this.stitchClient = Stitch.getDefaultAppClient();
//
//        final RemoteMongoClient mongoClient =
//                stitchClient.getServiceClient(RemoteMongoClient.factory, "mongodb-atlas");
//
//        final RemoteMongoCollection<Document> coll =
//                mongoClient.getDatabase("FitTrak-DB").getCollection("CLIENTS");
//
//        stitchClient.getAuth().loginWithCredential(new AnonymousCredential()).continueWithTask(
//                new Continuation<StitchUser, Task<RemoteUpdateResult>>() {
//
//                    @Override
//                    public Task<RemoteUpdateResult> then(@NonNull Task<StitchUser> task) throws Exception {
//                        if (!task.isSuccessful()) {
//                            Log.e("STITCH", "Login failed!");
//                            throw task.getException();
//                        }
//
//                        final Document updateDoc = new Document(
//                                "owner_id",
//                                task.getResult().getId()
//                        );
//
//                        updateDoc.put("number", 42);
//                        return coll.updateOne(
//                                null, updateDoc, new RemoteUpdateOptions().upsert(true)
//                        );
//                    }
//                }
//        ).continueWithTask(new Continuation<RemoteUpdateResult, Task<List<Document>>>() {
//            @Override
//            public Task<List<Document>> then(@NonNull Task<RemoteUpdateResult> task) throws Exception {
//                if (!task.isSuccessful()) {
//                    Log.e("STITCH", "Update failed!");
//                    throw task.getException();
//                }
//                List<Document> docs = new ArrayList<>();
//                return coll
//                        .find(new Document("owner_id", stitchClient.getAuth().getUser().getId()))
//                        .limit(100)
//                        .into(docs);
//            }
//        }).addOnCompleteListener(new OnCompleteListener<List<Document>>() {
//            @Override
//            public void onComplete(@NonNull Task<List<Document>> task) {
//                if (task.isSuccessful()) {
//                    Log.d("STITCH", "Found docs: " + task.getResult().toString());
//                    return;
//                }
//                Log.e("STITCH", "Error: " + task.getException().toString());
//                task.getException().printStackTrace();
//            }
//        });
//
//    }


    }
}
