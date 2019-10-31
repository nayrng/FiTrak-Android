package com.rng13.fittrak_android;

import java.util.ArrayList;

public class CLIENT_OBJ {
    String USER_NAME;

    String USER_ID;
    String EMAIL;
    String FIRST_NAME;
    String LAST_NAME;
    String TRAINER_USERNAME;
    ArrayList<String> WORKOUTS;
    ArrayList<String> APPOINTMENTS;

    public CLIENT_OBJ(){}
    public CLIENT_OBJ(String username, String uid, String email, String fname, String lname, String tid, ArrayList<String> workout, ArrayList<String> appts) {
        this.USER_NAME = username;
        this.USER_ID = uid;
        this.EMAIL = email;
        this.FIRST_NAME = fname;
        this.LAST_NAME = lname;
        this.TRAINER_USERNAME = tid;
        this.WORKOUTS = workout;
        this.APPOINTMENTS = appts;
    }
}