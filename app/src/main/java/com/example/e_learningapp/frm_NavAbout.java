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
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class frm_NavAbout extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private NavigationView navView;
    private Toolbar toolbar;

    private Boolean isPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_about);

        drawerLayout = findViewById(R.id.drawLayout);
        navView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.tlbUpper);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        navView.bringToFront();
        navView.setCheckedItem(R.id.nav_About);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.openNavDrawer,
                R.string.closeNavDrawer);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.nav_myCourses:
            {
                startActivity(new Intent(this, frm_Home.class));
                break;
            }
            case R.id.nav_expCourses:
            {
                startActivity(new Intent(this, frm_NavExploreCourse.class));
                break;
            }
            case R.id.nav_myAcc:
            {
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