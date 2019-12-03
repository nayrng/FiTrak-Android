package com.rng13.fittrak_android;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TRAINER_OBJ {

    // ------------------
    public String USERNAME;
    // ------------------
    public String EMAIL;
    public String FIRST_NAME;
    public String LAST_NAME;
    public Long AGE;
    public Long EXPERIENCE;
    public String GENDER;
    public String ABOUT_ME;
    ArrayList<String> CLIENTS;

    public TRAINER_OBJ(){};

    public TRAINER_OBJ(String user, String email, String fname, String lname, Long age, Long exp, String gen, String abt) {
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

    public TRAINER_OBJ(String user, String email, String fname, String lname, Long age, Long exp, String gen, String abt, ArrayList<String> cl) {
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
        this.CLIENTS = cl;
    }


}
