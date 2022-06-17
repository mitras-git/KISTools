package com.kis.scheduleon;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    ImageView bgapp, leaf, pic3, pic2, pic1;
    TextView text1, text2, text3, username, goodmorning, kistools, kistoolssubtitle;
    LinearLayout textSplash, textHome, buttons;
    ConstraintLayout icons;
    Animation bganim, leafanim, frombottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashto_home_anim_schedule);

        text1 = (TextView) findViewById(R.id.text1);
        text2 = (TextView) findViewById(R.id.text2);
        text3 = (TextView) findViewById(R.id.text3);
        username = (TextView) findViewById(R.id.username);
        goodmorning = (TextView) findViewById(R.id.goodmorning);
        kistools = (TextView) findViewById(R.id.kistools);
        kistoolssubtitle = (TextView) findViewById(R.id.kistoolssubtitle);
        bgapp = (ImageView) findViewById(R.id.bgapp);
        leaf = (ImageView) findViewById(R.id.leaf);
        textSplash = (LinearLayout) findViewById(R.id.textSplash);
        textHome = (LinearLayout) findViewById(R.id.textHome);
        buttons = (LinearLayout) findViewById(R.id.buttons);
        pic1 = (ImageView) findViewById(R.id.pic1);
        pic2 = (ImageView) findViewById(R.id.pic2);
        pic3 = (ImageView) findViewById(R.id.pic3);
        frombottom = AnimationUtils.loadAnimation(this, R.anim.frombottom);

        Calendar time = Calendar.getInstance();
        int timeOfDay = time.get(Calendar.HOUR_OF_DAY);

        if(timeOfDay >= 0 && timeOfDay <= 12){
            goodmorning.setText("Good Morning");
        }
        else if(timeOfDay >= 12 && timeOfDay < 16){
            goodmorning.setText("Good Afternoon");
        }
        else{
            goodmorning.setText("Good Evening");
        }

        Typeface ProductSansLight = Typeface.createFromAsset(getAssets(), "fonts/Product Sans Regular.ttf");
        Typeface ProductSansMedium = Typeface.createFromAsset(getAssets(), "fonts/Product Sans Bold.ttf");


        text1.setTypeface(ProductSansMedium);
        text2.setTypeface(ProductSansMedium);
        text3.setTypeface(ProductSansMedium);
        kistools.setTypeface(ProductSansMedium);
        kistoolssubtitle.setTypeface(ProductSansLight);
        goodmorning.setTypeface(ProductSansMedium);
        username.setTypeface(ProductSansLight);

        //fixing the different screen size problem
        DisplayMetrics dimension = new DisplayMetrics();

        getWindowManager().getDefaultDisplay().getMetrics(dimension);

        int width = dimension.widthPixels;
        int height = dimension.heightPixels;



        bgapp.animate().translationY(-2200).setDuration(800).setStartDelay(2000);
        leaf.animate().alpha(0).setDuration(800).setStartDelay(1300);
        textSplash.animate().translationY(140).alpha(0).setDuration(800).setStartDelay(2000);



        bganim = AnimationUtils.loadAnimation(this,R.anim.bganim);

        textHome.startAnimation(frombottom);
        buttons.startAnimation(frombottom);

        pic1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(MainActivity.this, Calculator.class);
                startActivity(a);
            }
        });


        pic2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(MainActivity.this, SplashtoHomeAnimSchedule.class);
                startActivity(a);
            }
        });

    }

    public void openTest(View view) {
        Intent a = new Intent(MainActivity.this, NotificationTest.class);
        startActivity(a);
    }

    public void openNotes(View view) {
        Intent a = new Intent(MainActivity.this, Notes.class);
        startActivity(a);
    }

    public void save(View view) {
        Intent save = new Intent(this, SplashtoHomeAnimSchedule.class);
        startActivity(save);
    }
}
