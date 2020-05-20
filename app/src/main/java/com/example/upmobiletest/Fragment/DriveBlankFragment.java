package com.example.upmobiletest.Fragment;

import android.annotation.SuppressLint;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.upmobiletest.Activity.HomeNavigation;
import com.example.upmobiletest.DB.TinyDB;
import com.example.upmobiletest.PostRetrofit.AddBlanDriveRetroFit;
import com.example.upmobiletest.PostRetrofit.LoginRetrofit;
import com.example.upmobiletest.Quar;
import com.example.upmobiletest.R;
import com.google.android.material.textfield.TextInputEditText;
import com.santalu.maskedittext.MaskEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class DriveBlankFragment extends Fragment {

    private Context mcontext;
    private View rootView;
    private Spinner spinner1, spinner2;
    private TextInputEditText manzil, narxi, joyTuri, odamSoni, avtTuri, avtRaqami;
    private MaskEditText ketishVaqti;
    private Button backBtn, addBtn;

    private String s_userid, s_manzil, s_narxi, s_joyTuri, s_odamSoni, s_avtTuri, s_ketishVaqti, s_avtRaqam, s_viloyat, s_ketishViloyat;

    public DriveBlankFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ClickableViewAccessibility")

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_drive_blank, container, false);

        spinner1 = rootView.findViewById(R.id.spinner1);
        spinner2 = rootView.findViewById(R.id.spinner2);

        manzil = rootView.findViewById(R.id.drBlank_manzil);
        narxi = rootView.findViewById(R.id.drBlank_narxi);
        joyTuri = rootView.findViewById(R.id.drBlank_joyTuri);
        odamSoni = rootView.findViewById(R.id.drBlank_odamSoni);
        avtTuri = rootView.findViewById(R.id.drBlank_avtTur);

        ketishVaqti = rootView.findViewById(R.id.drBlank_ketish_vaqti);
        avtRaqami = rootView.findViewById(R.id.drBlank_avtraqam);

        backBtn = rootView.findViewById(R.id.drBlank_back_btn);
        addBtn = rootView.findViewById(R.id.drBlank_add_btn);

        Quar quar = new Quar(mcontext);

        @SuppressLint("ResourceType")
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mcontext, android.R.layout.simple_spinner_item, quar.Viloyatlar);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    s_viloyat = String.valueOf(position);
                } else s_viloyat = "";
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    s_ketishViloyat = String.valueOf(position);
                } else s_ketishViloyat = "";

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        ketishVaqti.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                final int DRAWABLE_RIGHT = 2;
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    if (motionEvent.getRawX() >= (ketishVaqti.getRight() - ketishVaqti.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        quar.getDateTimeText(ketishVaqti);
                    }
                    return true;
                }
                return false;
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoHome();
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkUserData()) {
                    Call<ResponseBody> call = AddBlanDriveRetroFit
                            .getInstance()
                            .AddBlanDriveData()
                            .CreateBlankDriver(s_userid, s_viloyat + '-' + s_ketishViloyat + ',' + s_manzil,
                                    s_ketishVaqti, s_narxi, s_joyTuri, s_odamSoni, s_avtTuri, s_avtRaqam);

                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            try {
                                String s = response.body().string();
                                Log.i("status", s);

                                JSONObject status = new JSONObject(s);

                                if (status.getString("status").equals("true")) {
                                   gotoHome();
                                }
                            } catch (IOException | JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                        }
                    });
                }
            }
        });

        return rootView;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mcontext = context;
    }


    private void gotoHome() {
        FragmentTransaction myinfo = getParentFragmentManager().beginTransaction();
        myinfo.replace(R.id.FramLayoutForFragment, new HomeFragment());
        myinfo.commit();
    }

    private boolean checkUserData() {
        s_manzil = manzil.getText().toString();
        s_narxi = narxi.getText().toString();
        s_joyTuri = joyTuri.getText().toString();
        s_odamSoni = odamSoni.getText().toString();
        s_avtTuri = avtTuri.getText().toString();
        s_ketishVaqti = ketishVaqti.getText().toString();
        s_avtRaqam = avtRaqami.getText().toString();



        TinyDB tinyDB = new TinyDB(mcontext);
        s_userid = tinyDB.getString("id");

        if (s_manzil.equals(""))
            manzil.setError("Iltimos bo\'sh joyni to\'ldirng");
        if (s_narxi.equals(""))
            narxi.setError("Iltimos bo\'sh joyni to\'ldirng");
        if (s_joyTuri.equals(""))
            joyTuri.setError("Iltimos bo\'sh joyni to\'ldirng");
        if (s_odamSoni.equals(""))
            odamSoni.setError("Iltimos bo\'sh joyni to\'ldirng");
        if (s_avtTuri.equals(""))
            avtTuri.setError("Iltimos bo\'sh joyni to\'ldirng");
        if (s_ketishVaqti.equals(""))
            ketishVaqti.setError("Iltimos bo\'sh joyni to\'ldirng");
        if (s_avtRaqam.equals(""))
            avtRaqami.setError("Iltimos bo\'sh joyni to\'ldirng");
        if (!s_manzil.equals("") && !s_narxi.equals("") && !s_joyTuri.equals("") && !s_odamSoni.equals("") &&
                !s_avtTuri.equals("") && !s_ketishVaqti.equals("") && !s_avtRaqam.equals("") && !s_viloyat.equals("") &&
                !s_ketishViloyat.equals("")) {
            return true;
        } else return false;
    }

}
