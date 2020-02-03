package com.example.upmobiletest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigInteger;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static android.text.TextUtils.isEmpty;
import static android.text.TextUtils.makeSafeForPresentation;

public class MainActivity extends AppCompatActivity {

    //MySql connect variable
    private static String MYSQL_URL = "upitak.uz:3306";
    private static String MYSQL_DB_NAME = "upitakuz_driver";
    private static String MYSQL_USERNAME = "upitakuz_driveruser";
    private static String MYSQL_PASSWORD = "zEAbRii3";

    private EditText LoginEdit, PasswordEdit;
    private Button SiginButton, RegButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoginEdit = findViewById(R.id.LoginEdit);
        PasswordEdit = findViewById(R.id.PasswordEdit);
        RegButton = findViewById(R.id.RegButton);
        SiginButton = findViewById(R.id.SiginButton);

        SiginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEmpty(LoginEdit.getText())) {
                    LoginEdit.setError("Please enter your User name");
                } else if (isEmpty(PasswordEdit.getText())) {
                    PasswordEdit.setError("Please enter your Password");
                } else {
                    if (haveNetwork()) {
                        // Calling Async Task
                        new Task().execute();

                    } else {
                        Toast.makeText(MainActivity.this, "Do you have not internet",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        RegButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Regestration.class);
                startActivity(intent);
            }
        });


    }

    //md5 encrypte
    String toMD5(String text) {
        byte[] md5Input = text.getBytes();
        BigInteger md5Data = null;

        try {
            md5Data = new BigInteger(1, md5.encryptMD5(md5Input));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return md5Data.toString(32);
    }

    //task
    class Task extends AsyncTask<Void, Void, Void> {

        ResultSet resultSet;

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://" + MYSQL_URL + '/' + MYSQL_DB_NAME,
                        MYSQL_USERNAME, MYSQL_PASSWORD);

                Statement statement = connection.createStatement();
                resultSet = statement.executeQuery("Select * From user");
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            boolean userhave = false;

            try {
                while (resultSet.next()) {
                    if (LoginEdit.getText().toString().equals(
                            resultSet.getString(2)) && PasswordEdit.getText().toString().equals(
                                    resultSet.getString(4))) {
                        userhave = true;
                        break;
                    }
                }
                if (!userhave) {
                    LoginEdit.setText("");
                    PasswordEdit.setText("");
                    LoginEdit.setError("Error your User name");
                    PasswordEdit.setError("Error your Password");
                } else {
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            super.onPostExecute(aVoid);
        }
    }

    //have network
    private boolean haveNetwork() {
        boolean haveWifi = false;
        boolean haveMobileData = false;

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo[] networkInfos = connectivityManager.getAllNetworkInfo();

        for (NetworkInfo info : networkInfos) {
            if (info.getTypeName().equalsIgnoreCase("WIFI"))
                if (info.isConnected())
                    haveWifi = true;
            if (info.getTypeName().equalsIgnoreCase("MOBILE"))
                if (info.isConnected())
                    haveMobileData = true;
        }
        return haveMobileData || haveWifi;
    }

}