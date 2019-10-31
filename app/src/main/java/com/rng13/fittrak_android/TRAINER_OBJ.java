package com.rng13.fittrak_android;

public class TRAINER_OBJ {

    // ------------------
    String USERNAME;
    // ------------------
    String EMAIL;
    String FIRST_NAME;
    String LAST_NAME;
    int AGE;
    int EXPERIENCE;
    String GENDER;
    String ABOUT_ME;

    public TRAINER_OBJ(){};

    public TRAINER_OBJ(String user, String email, String fname, String lname, int age, int exp, String gen, String abt) {
        // ------------------
        this.USERNAME = user;
        // ------------------
        this.EMAIL = email;
        this.FIRST_NAME = fname;
        this.LAST_NAME = lname;
        this.AGE = age;
        this.EXPERIENCE = exp;
        this.GENDER = gen;
        this.ABOUT_ME = abt;
    }


}
