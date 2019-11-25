package com.rng13.fittrak_android;

public class WORKOUT_OBJ {

    String WORKOUT_TITLE;
    String WORKOUT_TRAINER;
    String WORKOUT_CLIENT;
    String WORKOUT_DAY;
    String WORKOUT_DETAILS;

    public WORKOUT_OBJ(){};

    public WORKOUT_OBJ(String title, String trainer, String client, String day, String details) {
        this.WORKOUT_TITLE = title;
        this.WORKOUT_TRAINER = trainer;
        this.WORKOUT_CLIENT = client;
        this.WORKOUT_DAY = day;
        this.WORKOUT_DETAILS = details;
    }

}
