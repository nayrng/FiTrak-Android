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

public class View_Workout_Adapter extends RecyclerView.Adapter<View_Workout_Adapter.workout_viewholder> {

    private ArrayList<WORKOUT_OBJ> dataset;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser user = firebaseAuth.getCurrentUser();
    private Context ctx;
    public OnItemClickListener mListener;

    public View_Workout_Adapter(ArrayList<WORKOUT_OBJ> workouts) {
        dataset = workouts;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public static class workout_viewholder extends RecyclerView.ViewHolder {
        ImageButton more_info;
        TextView workout_title;
        TextView workout_shortdesc;
        public workout_viewholder(View v, final OnItemClickListener listener) {
            super(v);
            more_info = v.findViewById(R.id.more_workoutinfo);
            workout_title = v.findViewById(R.id.workout_infocard);
            workout_shortdesc = v.findViewById(R.id.workout_somedetails);

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
    public workout_viewholder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_workouts_layout, viewGroup, false);
        workout_viewholder vh = new workout_viewholder(v, mListener);
        ctx = viewGroup.getContext();
        return vh;
    }

    @Override
    public void onBindViewHolder(workout_viewholder viewHolder, int i) {
        final WORKOUT_OBJ obj = dataset.get(i);
        viewHolder.workout_title.setId(i);
        viewHolder.workout_title.setText(obj.WORKOUT_TITLE);
        viewHolder.workout_shortdesc.setId(i);
        viewHolder.workout_shortdesc.setText(obj.WORKOUT_DETAILS);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

}
