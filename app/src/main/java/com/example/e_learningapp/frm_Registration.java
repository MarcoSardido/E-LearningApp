package com.example.e_learningapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

public class frm_Registration extends AppCompatActivity implements View.OnClickListener{

    private ConstraintLayout coordinatorLayout;

    private TextInputLayout fullName, email, password, confPass;
    private Button btnRegister;

    private String name, user, pass, confP;

    private DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        findViewById(R.id.lblSigninRegistration).setOnClickListener(this);
        findViewById(R.id.btnRegister).setOnClickListener(this);

        fullName = findViewById(R.id.txtFullName);
        email = findViewById(R.id.txtEmail);
        password = findViewById(R.id.txtPassword);
        confPass = findViewById(R.id.txtConfP);

        DB = new DBHelper(this);

    }
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.lblSigninRegistration:
            {
                startActivity(new Intent(this, frm_Login.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                break;
            }

            case R.id.btnRegister:
            {

                 name = fullName.getEditText().getText().toString();
                 user = email.getEditText().getText().toString();
                 pass = password.getEditText().getText().toString();
                 confP = confPass.getEditText().getText().toString();

                if(name.equals("") || user.equals("") || pass.equals("") || confP.equals(""))
                {
                    emptyInput();
                    clearInputs();
                }
                else if(pass.equals(confP))
                {
                    Boolean checkUser = DB.checkUser(user);

                    if(!checkUser)
                    {
                        Boolean insert = DB.insertData(name,user, pass);

                        if(insert)
                        {

                            String toastText = "Welcome " + name;
                            Toast.makeText(this,toastText, Toast.LENGTH_SHORT).show();

                            Intent next = new Intent(this, frm_Home.class);
                            next.putExtra("getFName", name);
                            startActivity(next);
                            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        }
                        else
                        {
                            String toastText = "Aww men, somethings wrong!";
                            Toast.makeText(this,toastText, Toast.LENGTH_SHORT).show();
                            clearInputs();
                        }
                        break;
                    }
                    else
                    {
                        String toastText = "Email already exist! Please login!";
                        Toast.makeText(this,toastText, Toast.LENGTH_SHORT).show();
                        clearInputs();
                    }
                    break;
                }
                else
                {
                    String toastText = "Password doesn't match!";
                    Toast.makeText(this,toastText, Toast.LENGTH_SHORT).show();
                    clearInputs();
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
        fullName.setError("error");
        email.setError("error");
        password.setError("error");
        confPass.setError("error");
    }

    public void clearInputs()
    {
        fullName.getEditText().setText("");
        email.getEditText().setText("");
        password.getEditText().setText("");
        confPass.getEditText().setText("");
    }
}