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

public class View_Appointments_Adapter extends RecyclerView.Adapter<View_Appointments_Adapter.MyViewHolder> {

    private ArrayList<String> DATASET;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser user = firebaseAuth.getCurrentUser();
    private Context ctx;
    public OnItemClickListener mListener;

    public View_Appointments_Adapter(ArrayList<String> appts) {
        DATASET = appts;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView appt;
        public MyViewHolder(View v, final OnItemClickListener listener) {
            super(v);
            appt = v.findViewById(R.id.appt_infocard);

        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_appointments_layout, viewGroup, false);
        MyViewHolder vh = new MyViewHolder(v, mListener);
        ctx = viewGroup.getContext();
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        String appt = DATASET.get(i);
        myViewHolder.appt.setId(i);
        myViewHolder.appt.setText(appt);
    }

    @Override
    public int getItemCount() {
        return DATASET.size();
    }


}
