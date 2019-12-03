package com.rng13.fittrak_android;

import java.util.ArrayList;

public class CLIENT_OBJ {
    String USER_NAME;

    public String USER_ID;
    public String EMAIL;
    public String FIRST_NAME;
    public String LAST_NAME;
    public String TRAINER_USERNAME;
    public ArrayList<String> WORKOUTS;
    public ArrayList<String> APPOINTMENTS;

    public String INTERESTS;

    public CLIENT_OBJ(){}
//    public CLIENT_OBJ(String username, String uid, String email, String fname, String lname, String tid, ArrayList<String> workout, ArrayList<String> appts) {
//        this.USER_NAME = username;
//        this.USER_ID = uid;
//        this.EMAIL = email;
//        this.FIRST_NAME = fname;
//        this.LAST_NAME = lname;
//        this.TRAINER_USERNAME = tid;
//        this.WORKOUTS = workout;
//        this.APPOINTMENTS = appts;
//    }

    public CLIENT_OBJ(String uid, String email, String fname, String lname, String tid, ArrayList<String> workout, ArrayList<String> appts) {
        this.USER_ID = uid;
        this.EMAIL = email;
        this.FIRST_NAME = fname;
        this.LAST_NAME = lname;
        this.TRAINER_USERNAME = tid;
        this.WORKOUTS = workout;
        this.APPOINTMENTS = appts;
    }

    public CLIENT_OBJ(String uid, String email, String fname, String lname, String tid, String interests) {
        this.USER_ID = uid;
        this.EMAIL = email;
        this.FIRST_NAME = fname;
        this.LAST_NAME = lname;
        this.TRAINER_USERNAME = tid;
        this.INTERESTS = interests;
    }
}