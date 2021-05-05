package com.example.e_learningapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class frm_Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DBHelper DB = new DBHelper(this);
    private DrawerLayout drawerLayout;
    private NavigationView navView;
    private Toolbar toolbar;

    private TextView userNameHome;
    private Boolean isPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        drawerLayout = findViewById(R.id.drawLayout);
        navView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.tlbUpper);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        navView.bringToFront();
        navView.setCheckedItem(R.id.nav_myCourses);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.openNavDrawer,
                R.string.closeNavDrawer);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navView.setNavigationItemSelectedListener(this);


//        getUserName();
    }

     /*
    ################################################################################################
                                               METHODS
    ################################################################################################
     */

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.nav_myCourses:
            {
                break;
            }
            case R.id.nav_expCourses:
            {
                startActivity(new Intent(this, frm_NavExploreCourse.class));
                break;
            }
            case R.id.nav_myAcc:
            {
                startActivity(new Intent(this, frm_NavMyAccount.class));
                break;
            }
            case R.id.nav_About:
            {
                startActivity(new Intent(this, frm_NavAbout.class));
                break;
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

//    public void getUserName()
//    {
//        String setFName;
//        userNameHome = findViewById(R.id.lblUser);
//        setFName = getIntent().getStringExtra("getFName");
//        userNameHome.setText(setFName);
//    }

    public void onBackPressed() {

        if (isPressed) {
            finishAffinity();
            System.exit(0);
        } else {
            Toast.makeText(this, "Please click back again to exit!", Toast.LENGTH_SHORT).show();

            isPressed = true;
        }

        Runnable runnable = new Runnable()
        {
            @Override
            public void run()
            {
                isPressed = false;
            }
        };

        new Handler().postDelayed(runnable, 2000);

    }


}