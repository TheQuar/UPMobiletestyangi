package com.example.upmobiletest.Activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import com.example.upmobiletest.DB.TinyDB;
import com.example.upmobiletest.Fragment.DriveBlankFragment;
import com.example.upmobiletest.Fragment.HomeFragment;
import com.example.upmobiletest.Fragment.MyData;
import com.example.upmobiletest.Fragment.MyElon;
import com.example.upmobiletest.Fragment.PassangerBlankFragment;
import com.example.upmobiletest.Quar;
import com.example.upmobiletest.R;

import android.os.Handler;
import android.telephony.PhoneNumberUtils;
import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class HomeNavigation extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView navUserName;
    private TextView navUserPhone;

    private boolean exit = false;
    private boolean userHavebase = false;

    private TinyDB tinyDB;
    private View headerView;

    private Quar quar;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_navigation);

        quar = new Quar();

        Toolbar toolbar = findViewById(R.id.myToolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);

        navUserName = (TextView) headerView.findViewById(R.id.nav_user_name_text);
        navUserPhone = (TextView) headerView.findViewById(R.id.nav_user_phone_text);

        tinyDB = new TinyDB(HomeNavigation.this);
        if (!tinyDB.getString("status").equals("10")) {
            Intent intent = new Intent(HomeNavigation.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            userHavebase = true;
        }

        if (userHavebase) {
            navUserName.setText(tinyDB.getString("name"));
            navUserPhone.setText(PhoneNumberUtils.
                    formatNumber("+998" + tinyDB.getString("phone"),
                            String.valueOf(PhoneNumberUtils.getFormatTypeForLocale(Locale.US)))
            );

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.FramLayoutForFragment, new HomeFragment());
            ft.commit();

        }


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (exit) {
            finish(); // finish activity
//            System.exit(0);
        } else {
            Toast.makeText(this, "Chiqish uchun Orqaga yana bosing.",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.home_navigation, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            FragmentTransaction home = getSupportFragmentManager().beginTransaction();
            home.replace(R.id.FramLayoutForFragment, new HomeFragment());
            home.commit();
        } else if (id == R.id.nav_elon) {
            FragmentTransaction elon = getSupportFragmentManager().beginTransaction();
            elon.replace(R.id.FramLayoutForFragment, new MyElon());
            elon.commit();

        } else if (id == R.id.nav_my_info) {
            FragmentTransaction myinfo = getSupportFragmentManager().beginTransaction();
            myinfo.replace(R.id.FramLayoutForFragment, new MyData());
            myinfo.commit();

        } else if (id == R.id.nav_elon_add) {

            final Dialog dialog = new Dialog(HomeNavigation.this);
            dialog.setContentView(R.layout.alert_dialog);
          //  dialog.setTitle("E'lon berish");
            dialog.findViewById(R.id.alert_button);
            dialog.show();
            Button drive_button = dialog.findViewById(R.id.alert_button);
            Button passanger_button = dialog.findViewById(R.id.alert_button1);
            drive_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentTransaction addBlankDrive = getSupportFragmentManager().beginTransaction();
                    addBlankDrive.replace(R.id.FramLayoutForFragment, new DriveBlankFragment());
                    addBlankDrive.commit();
                    dialog.cancel();

                }
            });

            passanger_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentTransaction addblank = getSupportFragmentManager().beginTransaction();
                    addblank.replace(R.id.FramLayoutForFragment, new PassangerBlankFragment());
                    addblank.commit();
                    dialog.cancel();
                }
            });


        } else if (id == R.id.nav_exit) {
            tinyDB.clear();
            Intent intent = new Intent(HomeNavigation.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
