package com.example.upmobiletest.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.upmobiletest.R;
import com.example.upmobiletest.PostRetrofit.RegistrationRetrofit;
import com.google.android.material.textfield.TextInputLayout;
import com.santalu.maskedittext.MaskEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.text.TextUtils.isEmpty;

public class Regestration extends AppCompatActivity {

    private EditText userLogin, userName, userEmail, userPassword;
    private MaskEditText userPhoneNumber;
    private TextInputLayout inputuserEmail;
    private String userLoginStr, userNameStr, userEmailStr, userPasswordStr, userPhoneStr;
    private Button regBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regestration);

        userLogin = findViewById(R.id.UserLgin);
        userName = findViewById(R.id.UserName);
        userEmail = findViewById(R.id.UserEmail);
        userPassword = findViewById(R.id.UserPassword);
        userPhoneNumber = findViewById(R.id.UserPhoneNumber);

        inputuserEmail = findViewById(R.id.inputUserEmail);


        regBtn = findViewById(R.id.regbtn);

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String user data
                userNameStr = userName.getText().toString();
                userLoginStr = userLogin.getText().toString();
                userPasswordStr = userPassword.getText().toString();
                userEmailStr = userEmail.getText().toString();
                userPhoneStr = userPhoneNumber.getRawText();

                if (isEmpty(userLoginStr)) {
                    userLogin.setError("Iltimos Login ni kiriting !");
                } else if (isEmpty(userNameStr)) {
                    userName.setError("Iltimos Ismingizni kiriting !");
                } else if (isEmpty(userEmailStr)) {
                    userEmail.setError("Iltimos Email pochtangizni kiriting !");
                } else if (isEmpty(userPasswordStr)) {
                    userPassword.setError("Iltimos parolni kiritng !");
                } else if (isEmpty(userPhoneStr)) {
                    userPhoneNumber.setError("Iltimos telefon raqamni kiritng !");
                } else {

                    Call<ResponseBody> call = RegistrationRetrofit
                            .getInstance()
                            .getRegistrationAPI()
                            .createUser(userNameStr, userLoginStr, userPasswordStr, userEmailStr, userPhoneStr);

                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                            try {
                                String s = response.body().string();

                                JSONObject getResultPostJsonObject = new JSONObject(s);
                                String postResult = getResultPostJsonObject.getString("status");
                                JSONObject getDataPostJsonObject = getResultPostJsonObject.getJSONObject("data");
                                //JSONArray jsonArray = getDataPostJsonObject.getJSONArray("username");


                                if (postResult.equals("true")) {
                                    Intent intent = new Intent(Regestration.this, HomeNavigation.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(Regestration.this, "error ! \n" + getDataPostJsonObject.toString()
                                            , Toast.LENGTH_LONG).show();
                                }


                            } catch (IOException | JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(Regestration.this, e.getMessage(), Toast.LENGTH_LONG).show();
                            }


                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(Regestration.this, t.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });

                }
            }
        });


    }


}
