package com.example.e_learningapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class frm_SplashScreen extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private Boolean firstTime;

    private Animation topAnim, bottomAnim;
    ImageView ssImg;
    TextView title, subtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);


        topAnim =  AnimationUtils.loadAnimation(this, R.anim.splash_screen_anim_top);
        bottomAnim =  AnimationUtils.loadAnimation(this, R.anim.splash_screen_anim_bottom);

        ssImg = findViewById(R.id.imgTop);
        title = findViewById(R.id.lblBottom1);
        subtitle = findViewById(R.id.lblBottom2);

        ssImg.setAnimation(topAnim);
        title.setAnimation(bottomAnim);
        subtitle.setAnimation(bottomAnim);



        sharedPreferences = getSharedPreferences("MyPref", MODE_PRIVATE);

        firstTime = sharedPreferences.getBoolean("firstTime", true);

        if(firstTime)
        {
            new Handler().postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    firstTime = false;
                    editor.putBoolean("firstTime", firstTime);
                    editor.apply();

                    startActivity(new Intent(frm_SplashScreen.this, frm_OnBoarding1.class));
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    finish();
                }
            }, 5000);
        }
        else
        {
            startActivity(new Intent(this, frm_Home.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
        }
    }
}