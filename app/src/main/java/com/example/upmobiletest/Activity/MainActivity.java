package com.example.upmobiletest.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.upmobiletest.PostRetrofit.LoginRetrofit;
import com.example.upmobiletest.R;
import com.example.upmobiletest.md5;
import com.mysql.jdbc.MiniAdmin;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.text.TextUtils.isEmpty;

public class MainActivity extends AppCompatActivity {
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
                if (haveNetwork()) {
                    if (isEmpty(LoginEdit.getText())) {
                        LoginEdit.setError("Please enter your User name");
                    } else if (isEmpty(PasswordEdit.getText())) {
                        PasswordEdit.setError("Please enter your Password");
                    } else {
                        Call<ResponseBody> call = LoginRetrofit
                                .getInstance()
                                .getLoginApi()
                                .LoginUser(LoginEdit.getText().toString(), PasswordEdit.getText().toString());

                        call.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                try {
                                    String s = response.body().string();
                                    if (!s.equals("null")) {
                                        JSONObject status = new JSONObject(s);
                                        if (!status.getString("status").equals("false")) {
                                            Intent intent = new Intent(MainActivity.this, HomeNavigation.class);
                                            startActivity(intent);
                                        } else {
                                            LoginEdit.setText("");
                                            PasswordEdit.setText("");
                                            Toast.makeText(MainActivity.this, "Siz ro\'yxatdan o\'tmagansiz ", Toast.LENGTH_LONG).show();
                                        }
                                    } else {
                                        LoginEdit.setText("");
                                        PasswordEdit.setText("");
                                        Toast.makeText(MainActivity.this, "Siz ro\'yxatdan o\'tmagansiz ", Toast.LENGTH_LONG).show();
                                    }
                                } catch (IOException | JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                } else
                    Toast.makeText(MainActivity.this,
                            "You're not connected to the Internet", Toast.LENGTH_SHORT).show();
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
        return haveWifi || haveMobileData;
    }
}