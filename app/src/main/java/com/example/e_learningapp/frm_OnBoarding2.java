package com.example.e_learningapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class frm_OnBoarding2 extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boardin2);

        findViewById(R.id.btnRegister).setOnClickListener(this);
        findViewById(R.id.lblSkip).setOnClickListener(this);

    }
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnRegister:
            {
                startActivity(new Intent(this, frm_OnBoarding3.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                break;
            }

            case R.id.lblSkip:
            {
                startActivity(new Intent(this, frm_OnBoarding3.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                break;
            }
        }
    }

}