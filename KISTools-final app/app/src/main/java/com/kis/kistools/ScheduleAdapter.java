package com.kis.kistools;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.MyViewHolder> {

    Context context;
    ArrayList<ScheduleOn> scheduleOn;

    public ScheduleAdapter(Context c, ArrayList<ScheduleOn> p) {
        context = c;
        scheduleOn = p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_schedule, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        myViewHolder.titleschedule.setText(scheduleOn.get(position).getTitleschedule());
        myViewHolder.teacherschedule.setText(scheduleOn.get(position).getTeacherschedule());
        myViewHolder.timeschedule.setText(scheduleOn.get(position).getTimeschedule());

        final String getTitleSchedule = scheduleOn.get(position).getTitleschedule();
        final String getTeacherSchedule = scheduleOn.get(position).getTeacherschedule();
        final String getDateSchedule = scheduleOn.get(position).getTimeschedule();
        final String getIdSchedule = scheduleOn.get(position).getIdschedule();



        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aa = new Intent(context,EditTaskSchedule.class)
;               aa.putExtra("titleschedule", getTitleSchedule);
                aa.putExtra("teacherschedule", getTeacherSchedule);
                aa.putExtra("timeschedule", getDateSchedule);
                aa.putExtra("idschedule", getIdSchedule);
                context.startActivity(aa);
            }
        });


    }

    @Override
    public int getItemCount() {
        return scheduleOn.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView titleschedule;
        TextView teacherschedule;
        TextView timeschedule;
        TextView idschedule;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            titleschedule = (TextView) itemView.findViewById(R.id.titleschedule);
            teacherschedule = (TextView) itemView.findViewById(R.id.teacherschedule);
            timeschedule = (TextView) itemView.findViewById(R.id.timeschedule);

        }
    }

}
