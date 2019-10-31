package com.rng13.fittrak_android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class View_Trainers_Adapter extends RecyclerView.Adapter<View_Trainers_Adapter.MyViewHolder> {

    private ArrayList<TRAINER_OBJ> DATASET;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser user = firebaseAuth.getCurrentUser();
    private Context ctx;
    public OnItemClickListener mListener;

    public View_Trainers_Adapter(ArrayList<TRAINER_OBJ> trainers) {
        DATASET = trainers;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView trainer_info;
        ImageButton more_info;
        public MyViewHolder(View v, final OnItemClickListener listener) {
            super(v);
            trainer_info = v.findViewById(R.id.trainer_infocard);
            more_info = v.findViewById(R.id.more_trainerinfo);

            more_info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });

        }

    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_trainers_layout, viewGroup, false);
        MyViewHolder vh = new MyViewHolder(v, mListener);
        ctx = viewGroup.getContext();
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        final TRAINER_OBJ obj = DATASET.get(i);
        myViewHolder.trainer_info.setId(i);
        myViewHolder.trainer_info.setText(obj.FIRST_NAME + " " + obj.LAST_NAME + "\n"
                                        + "Years of Experience: " + obj.EXPERIENCE);
        myViewHolder.more_info.setId(i);
    }

    @Override
    public int getItemCount() {
        return DATASET.size();
    }


}
