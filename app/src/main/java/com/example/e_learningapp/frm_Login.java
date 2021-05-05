package com.example.e_learningapp;

import
        androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

public class frm_Login extends AppCompatActivity implements View.OnClickListener{

    private ConstraintLayout coordinatorLayout;

    private TextInputLayout email, password;
    private Button btnLogin;

     private DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewById(R.id.lblSignupLogin).setOnClickListener(this);
        findViewById(R.id.btnLogin).setOnClickListener(this);

        email = findViewById(R.id.txtEmail);
        password = findViewById(R.id.txtPassword);

        DB = new DBHelper(this);

    }
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.lblSignupLogin:
            {
                startActivity(new Intent(this, frm_Registration.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                break;
            }
            case R.id.btnLogin:
            {
                String user = email.getEditText().getText().toString();
                String pass = password.getEditText().getText().toString();

                if(user.equals("") || pass.equals(""))
                {
                    emptyInput();
                    clearInputs();
                    break;
                }
                else
                {
                    String userAcc = user + "@gmail.com";
                    Boolean checkPass = DB.authorizeUser(userAcc, pass);

                    if(checkPass)
                    {
                        String toastText = "Welcome to Adaptive E-Learning! " + user;
                        Toast.makeText(this,toastText, Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(this, frm_Home.class));
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    }
                    else
                    {
                        String toastText = "Invalid Credentials!";
                        Toast.makeText(this,toastText, Toast.LENGTH_SHORT).show();
                        clearInputs();
                    }
                }
                break;
            }
        }
    }

//    public void showSnackbar(String details)
//    {
//        coordinatorLayout = findViewById(R.id.coordinatorLayout);
//        Snackbar.make(coordinatorLayout, details,Snackbar.LENGTH_LONG).show();
//    }

    public void emptyInput()
    {
        email.setError("error");
        password.setError("error");
    }

    public void clearInputs()
    {
        email.getEditText().setText("");
        password.getEditText().setText("");
    }
}