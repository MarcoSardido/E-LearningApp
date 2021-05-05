package com.example.e_learningapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class frm_OnBoarding1 extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnRegister).setOnClickListener(this);
        findViewById(R.id.lblSkip).setOnClickListener(this);
    }

    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnRegister:
            {
                startActivity(new Intent(this, frm_OnBoarding2.class));
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

















//  REGISTRATION

