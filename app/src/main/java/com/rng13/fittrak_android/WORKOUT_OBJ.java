package com.rng13.fittrak_android;

public class WORKOUT_OBJ {

    public String WORKOUT_TITLE;
    public String WORKOUT_TRAINER;
    public String WORKOUT_DETAILS;

    public WORKOUT_OBJ(){}

    public WORKOUT_OBJ(String title, String desc, String trainer) {
        this.WORKOUT_TITLE = title;
        this.WORKOUT_DETAILS = desc;
        this.WORKOUT_TRAINER = trainer;
    }

}
