package com.example.upmobiletest.Fragment;


import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.upmobiletest.Activity.MainActivity;
import com.example.upmobiletest.DB.TinyDB;
import com.example.upmobiletest.R;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyData extends Fragment {
    private Context mContext;
    private View rootView;

    private TextView name, nick, email, create_time, phone_numbe, likes, dislikes;

    private ImageView imageView;

    public MyData() {
        // Required empty public constructor
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_my_data, container, false);

        imageView = rootView.findViewById(R.id.myDataImageView);
        name = rootView.findViewById(R.id.myDataName);
        nick = rootView.findViewById(R.id.myDataLogin);
        email = rootView.findViewById(R.id.myDataEmail);
        create_time = rootView.findViewById(R.id.myDataDate);
        phone_numbe = rootView.findViewById(R.id.myDataPhoneNumber);
        likes = rootView.findViewById(R.id.thumb_up_text);
        dislikes = rootView.findViewById(R.id.thumb_down_text);

        TinyDB myTinyDB = new TinyDB(mContext);

       // SimpleDateFormat myDate = new android.icu.text.SimpleDateFormat("yyyy/MM/dd"); //bu version 5.1 da ishlamaydi

        DateFormat myDate = new java.text.SimpleDateFormat(" dd MMM yyyy hh:mm");

        long timesnapp = Long.parseLong(myTinyDB.getString("created_at"));
        Timestamp ts = new Timestamp(timesnapp * 1000);
        Date date = new Date(ts.getTime());
       // myDate.applyPattern(" dd MMM yyyy hh:mm"); // bu ham
        create_time.setText(myDate.format(date));

        name.setText(myTinyDB.getString("name"));
        nick.setText(myTinyDB.getString("username"));
        email.setText(myTinyDB.getString("email"));
        phone_numbe.setText(PhoneNumberUtils.
                formatNumber("+998" + myTinyDB.getString("phone"),
                        String.valueOf(PhoneNumberUtils.getFormatTypeForLocale(Locale.US))));
        likes.setText(myTinyDB.getString("likes"));
        dislikes.setText(myTinyDB.getString("dislikes"));

        return rootView;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }
}
