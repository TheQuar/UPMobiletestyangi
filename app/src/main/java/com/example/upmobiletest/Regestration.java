package com.example.upmobiletest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.santalu.maskedittext.MaskEditText;

import java.util.regex.Pattern;

import static android.text.TextUtils.isEmpty;

public class Regestration extends AppCompatActivity {
    private EditText userLogin, userName, userEmail, userPassword;
    private MaskEditText userPhoneNumber;
    private TextInputLayout inputuserEmail;

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

                if (isEmpty(userLogin.getText())) {
                    userLogin.setError("Iltimos Login ni kiriting !");
                } else if (isEmpty(userName.getText())) {
                    userName.setError("Iltimos Ismingizni kiriting !");
                } else if (isEmpty(userEmail.getText())) {
                    userEmail.setError("Iltimos Email pochtangizni kiriting !");
                } else if (isEmpty(userPassword.getText())) {
                    userPassword.setError("Iltimos parolni kiritng !");
                } else if (isEmpty(userPhoneNumber.getText())) {
                    userPhoneNumber.setError("Iltimos telefon raqamni kiritng !");
                } else if (!Patterns.EMAIL_ADDRESS.matcher((CharSequence)inputuserEmail).matches()) {
                    userEmail.setError("Bunday email pochta mavjud emas !");
                } else {
                    Intent intent = new Intent(Regestration.this, HomeActivity.class);
                    startActivity(intent);

                }
            }
        });


    }
}
