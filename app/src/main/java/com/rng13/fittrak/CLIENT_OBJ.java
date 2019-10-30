package com.rng13.fittrak;

import java.util.ArrayList;

public class CLIENT_OBJ {
    String user_id;
    String email;
    String first_name_last_name;
    String trainer_id;
    ArrayList<String> workouts;
    ArrayList<String> appointments;

    public CLIENT_OBJ(){};

    public CLIENT_OBJ(String user, String email_addy, String name, String trainer, ArrayList workouts_assigned, ArrayList trainer_appts) {
        this.user_id = user;
        this.email = email_addy;
        this.first_name_last_name = name;
        this.trainer_id = trainer;
        this.workouts = workouts_assigned;
        this.appointments = trainer_appts;
    }


}
