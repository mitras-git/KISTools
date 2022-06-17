package com.kis.scheduleon;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Random;

public class NewTaskAct extends AppCompatActivity{ //implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {


    private Calendar Calender;
    Calendar now = Calender.getInstance();
    TimePickerDialog tpd;
    DatePickerDialog dpd;
    TextView titlepage;
    TextView addclass;
    TextView addTeacher;
    TextView addTime;
    EditText titleschedule;
    EditText teacherschedule;
    EditText timeschedule;
    Button btnSaveTask;
    Button btnCancel;
    DatabaseReference reference;
    Integer scheduleNum = new Random().nextInt();
    String idschedule = Integer.toString(scheduleNum);


   /* @Override
    public void onDataSet (DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        now.set(Calendar.YEAR, year);
        now.set(Calendar.MONTH,monthOfYear);
        now.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        tpd.show();


    }

    @Override
    public void onDataSet (DatePickerDialog view, int hourOfDay, int minute, int second){
        now.set(Calendar.HOUR_OF_DAY,hourOfDay);
        now.set(Calendar.MINUTE,minute);
        now.set(Calendar.SECOND,second);

        NotifyMe notifyMe = new NotifyMe.Builder(getApplicationContext())
                .title()


    }
    */





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        titlepage = findViewById(R.id.titlepage);

        addclass = findViewById(R.id.addclass);
        titleschedule = findViewById(R.id.titleschedule);

        addTeacher = findViewById(R.id.addTeacher);
        addTime = findViewById(R.id.addTime);
        teacherschedule = findViewById(R.id.teacherschedule);

        timeschedule = findViewById(R.id.timeschedule);

        btnSaveTask = findViewById(R.id.btnSaveTask);
        btnCancel = findViewById(R.id.btnCancel);

        btnSaveTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reference = FirebaseDatabase.getInstance().getReference().child("ScheduleOn").child("Schedule" + scheduleNum);
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        snapshot.getRef().child("titleschedule").setValue(titleschedule.getText().toString());
                        snapshot.getRef().child("teacherschedule").setValue(teacherschedule.getText().toString());
                        snapshot.getRef().child("timeschedule").setValue(timeschedule.getText().toString());
                        snapshot.getRef().child("idschedule").setValue(idschedule);

                        Intent a = new Intent(NewTaskAct.this,SplashtoHomeAnimSchedule.class);
                        startActivity(a);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });




        Typeface ProductSansLight = Typeface.createFromAsset(getAssets(), "fonts/Product Sans Regular.ttf");
        Typeface ProductSansMedium = Typeface.createFromAsset(getAssets(), "fonts/Product Sans Bold.ttf");



        titleschedule.setTypeface(ProductSansMedium);

        addclass.setTypeface(ProductSansLight);

        addTeacher.setTypeface(ProductSansLight);
        teacherschedule.setTypeface(ProductSansMedium);

        addTime.setTypeface(ProductSansLight);
        timeschedule.setTypeface(ProductSansMedium);

        btnSaveTask.setTypeface(ProductSansMedium);
        btnCancel.setTypeface(ProductSansLight);
    }

    public void cancelMethod(View view) {
        Intent a = new Intent (this, SplashtoHomeAnimSchedule.class);
        startActivity(a);
    }
}