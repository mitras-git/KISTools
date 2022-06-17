package com.kis.scheduleon;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;

public class SplashtoHomeAnimSchedule extends AppCompatActivity {

    TextView titlepage;
    TextView subtitlepage;
    TextView endpage;
    Button btnAddNew, btnHome;

    DatabaseReference reference;
    RecyclerView ourschedule;
    ArrayList<ScheduleOn> list;
    ScheduleAdapter scheduleAdapter;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel channel =
                    new NotificationChannel("myFirebaseChannel", "myFirebaseChannel", NotificationManager.IMPORTANCE_DEFAULT);

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);

        }

        FirebaseMessaging.getInstance().subscribeToTopic("general")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Successful";
                        if (!task.isSuccessful()) {
                            msg = "Failed";
                        }

                    }
                });

        titlepage = findViewById(R.id.titlepage);
        subtitlepage = findViewById(R.id.subtitlepage);
        endpage = findViewById(R.id.endpage);
        btnAddNew = findViewById(R.id.btnAddNew);
        btnHome = (Button) findViewById(R.id.btnHome);

        Typeface ProductSansLight = Typeface.createFromAsset(getAssets(), "fonts/Product Sans Regular.ttf");
        Typeface ProductSansMedium = Typeface.createFromAsset(getAssets(), "fonts/Product Sans Bold.ttf");


        titlepage.setTypeface(ProductSansMedium);
        subtitlepage.setTypeface(ProductSansLight);
        endpage.setTypeface(ProductSansLight);
        btnHome.setTypeface(ProductSansMedium);
        btnAddNew.setTypeface(ProductSansLight);

        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(SplashtoHomeAnimSchedule.this, NewTaskAct.class);
                startActivity(a);
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(SplashtoHomeAnimSchedule.this, MainActivity.class);
                startActivity(a);
            }
        });


        ourschedule = findViewById(R.id.ourschedule);
        ourschedule.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<ScheduleOn>();

        reference = FirebaseDatabase.getInstance().getReference().child("ScheduleOn");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {

                    ScheduleOn p = dataSnapshot1.getValue(ScheduleOn.class);
                    list.add(p);

                }

                scheduleAdapter = new ScheduleAdapter(SplashtoHomeAnimSchedule.this, list);
                ourschedule.setAdapter(scheduleAdapter);
                scheduleAdapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();


            }
        });

    }


}