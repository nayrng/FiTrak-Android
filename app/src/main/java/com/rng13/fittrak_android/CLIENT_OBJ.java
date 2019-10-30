package com.rng13.fittrak_android;

import java.util.ArrayList;

public class CLIENT_OBJ {
    String USER_ID;
    String EMAIL;
    String FIRST_NAME;
    String LAST_NAME;
    String TRAINER_ID;
    ArrayList<String> WORKOUTS;
    ArrayList<String> APPOINTMENTS;

    public CLIENT_OBJ(){}
    public CLIENT_OBJ(String uid, String email, String fname, String lname, String tid, ArrayList<String> workout, ArrayList<String> appts) {
        this.USER_ID = uid;
        this.EMAIL = email;
        this.FIRST_NAME = fname;
        this.LAST_NAME = lname;
        this.TRAINER_ID = tid;
        this.WORKOUTS = workout;
        this.APPOINTMENTS = appts;
    }
}