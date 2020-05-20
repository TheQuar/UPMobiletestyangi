package com.example.upmobiletest.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.upmobiletest.DB.TinyDB;
import com.example.upmobiletest.PostRetrofit.LoginRetrofit;
import com.example.upmobiletest.Quar;
import com.example.upmobiletest.R;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.text.TextUtils.isEmpty;
import static androidx.core.content.ContextCompat.getSystemService;

public class MainActivity extends AppCompatActivity {
    private EditText LoginEdit, PasswordEdit;
    private Button SiginButton, RegButton;
    private Quar quar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quar = new Quar();

        LoginEdit = findViewById(R.id.LoginEdit);
        PasswordEdit = findViewById(R.id.PasswordEdit);
        RegButton = findViewById(R.id.RegButton);
        SiginButton = findViewById(R.id.SiginButton);

        //Toast.makeText(MainActivity.this, String.valueOf(Build.VERSION.SDK_INT), Toast.LENGTH_LONG).show();

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

                                            //write TinyDB user Data
                                            JSONObject userDataJsonObject = status.getJSONObject("data");

                                            TinyDB myTinyDB = new TinyDB(MainActivity.this);
                                            //writed get data to TinyDb
                                            myTinyDB.putString("id", userDataJsonObject.getString("id"));
                                            myTinyDB.putString("name", userDataJsonObject.getString("name"));
                                            myTinyDB.putString("username", userDataJsonObject.getString("username"));
                                            myTinyDB.putString("auth_key", userDataJsonObject.getString("auth_key"));
                                            myTinyDB.putString("password_hash", userDataJsonObject.getString("password_hash"));
                                            myTinyDB.putString("email", userDataJsonObject.getString("email"));
                                            myTinyDB.putString("phone", userDataJsonObject.getString("phone"));
                                            myTinyDB.putString("address", userDataJsonObject.getString("address"));
                                            myTinyDB.putString("birthday", userDataJsonObject.getString("birthday"));
                                            myTinyDB.putString("picture", userDataJsonObject.getString("picture"));
                                            myTinyDB.putString("passportno", userDataJsonObject.getString("passportno"));
                                            myTinyDB.putString("passportpic", userDataJsonObject.getString("passportpic"));
                                            myTinyDB.putString("likes", userDataJsonObject.getString("likes"));
                                            myTinyDB.putString("dislikes", userDataJsonObject.getString("dislikes"));
                                            myTinyDB.putString("updated_at", userDataJsonObject.getString("updated_at"));
                                            myTinyDB.putString("created_at", userDataJsonObject.getString("created_at"));
                                            myTinyDB.putString("status", userDataJsonObject.getString("status"));

                                            intent.putExtra("userRegResult", "true");
                                            startActivity(intent);
                                            finish();
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

    public boolean haveNetwork() {
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