package com.rng13.fittrak_android;
public class CLIENT_OBJ {

    public String USER_ID;
    public String EMAIL;
    public String FIRST_NAME;
    public String LAST_NAME;
    public String TRAINER_USERNAME;

    public String INTERESTS;

    public CLIENT_OBJ(){}

    public CLIENT_OBJ(String uid, String email, String fname, String lname, String tid, String interests) {
        this.USER_ID = uid;
        this.EMAIL = email;
        this.FIRST_NAME = fname;
        this.LAST_NAME = lname;
        this.TRAINER_USERNAME = tid;
        this.INTERESTS = interests;
    }
}