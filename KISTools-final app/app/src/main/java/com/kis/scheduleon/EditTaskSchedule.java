package com.kis.scheduleon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditTaskSchedule extends AppCompatActivity {

    TextView titlepage;
    TextView addclass;
    TextView addTeacher;
    TextView addTime;
    EditText titleschedule;
    EditText teacherschedule;
    EditText timeschedule;
    Button btnSaveUpdate;
    Button btnDelete;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task_schedule);

        titleschedule = findViewById(R.id.titleschedule);
        teacherschedule = findViewById(R.id.teacherschedule);
        timeschedule = findViewById(R.id.timeschedule);

        btnSaveUpdate = findViewById(R.id.btnSaveUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        titleschedule.setText(getIntent().getStringExtra("titleschedule"));
        teacherschedule.setText(getIntent().getStringExtra("teacherschedule"));
        timeschedule.setText(getIntent().getStringExtra("timeschedule"));

        final String ididschedule = getIntent().getStringExtra("idschedule");

        reference = FirebaseDatabase.getInstance().getReference().child("ScheduleOn").child("Schedule" + ididschedule);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reference.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){

                            Intent a = new Intent(EditTaskSchedule.this,SplashtoHomeAnimSchedule.class);
                            startActivity(a);
                        } else{

                            Toast.makeText(getApplicationContext(), "Failed to Delete!", Toast.LENGTH_SHORT).show();

                        }
                    }
                });

            }
        });

        btnSaveUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        snapshot.getRef().child("titleschedule").setValue(titleschedule.getText().toString());
                        snapshot.getRef().child("teacherschedule").setValue(teacherschedule.getText().toString());
                        snapshot.getRef().child("timeschedule").setValue(timeschedule.getText().toString());
                        snapshot.getRef().child("idschedule").setValue(ididschedule);

                        Intent a = new Intent(EditTaskSchedule.this,SplashtoHomeAnimSchedule.class);
                        startActivity(a);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}