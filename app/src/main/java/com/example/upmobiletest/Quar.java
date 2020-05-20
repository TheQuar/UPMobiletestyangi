package com.example.upmobiletest;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.google.i18n.phonenumbers.nano.Phonemetadata;
import com.santalu.maskedittext.MaskEditText;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import static android.content.Context.CONNECTIVITY_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;

public class Quar {

    private Context mContext;
    public final String[] Viloyatlar = {"Viloyatlar", "Andijon", "Farg\'ona",
            "Namangan", "Toshkent", "Buxoro", "Jizzax", "Xorazm",
            "Navoiy", "Qashqadaryo", "Samarqand ", "Sirdaryo", "Surxondaryo", "Maskva"};


    public Quar() {
    }

    public Quar(Context context) {
        this.mContext = context;
    }

    public String getFormattedNumber(String phoneNumber) {
        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
        Phonemetadata.NumberFormat numberFormat = new Phonemetadata.NumberFormat();
        numberFormat.pattern = "(\\d{1})(\\d{2})(\\d{3})(\\d{4})";
        numberFormat.format = "($1) $3-$4";
        List<Phonemetadata.NumberFormat> newNumberFormats = new ArrayList<>();
        newNumberFormats.add(numberFormat);
        Phonenumber.PhoneNumber phoneNumberPN = null;
        try {
            phoneNumberPN = phoneNumberUtil.parse(phoneNumber, Locale.JAPAN.getCountry());
            phoneNumber = phoneNumberUtil.formatByPattern(phoneNumberPN,
                    PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL, newNumberFormats);
        } catch (NumberParseException e) {
            e.printStackTrace();
        }
        return phoneNumber;
    }

    public static byte[] encryptMD5(byte[] data) throws Exception {

        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(data);
        return md5.digest();
    }

    public String toMD5(String text) {
        byte[] md5Input = text.getBytes();
        BigInteger md5Data = null;

        try {
            md5Data = new BigInteger(1, encryptMD5(md5Input));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return md5Data.toString(32);
    }


    public void getDateTimeText(final MaskEditText maskEditText) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minut = calendar.get(Calendar.MINUTE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String mDay = String.valueOf(day);
                String mMonth = String.valueOf(month);
                String mYear = String.valueOf(year);

                if (mDay.length() == 1) mDay = "0" + mDay;
                if (mMonth.length() == 1) mMonth = "0" + mMonth;

                String date = mYear + "-" + mMonth + "-" + mDay;
                maskEditText.setText(date);

                TimePickerDialog timePicker = new TimePickerDialog(mContext,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view,
                                                  int hourOfDay, int minute) {
                                String mHour = String.valueOf(hourOfDay);
                                String mMinute = String.valueOf(minute);

                                if (mHour.length() == 1) mHour = "0" + mHour;
                                if (mMinute.length() == 1) mMinute = "0" + mMinute;

                                String date = " " + mHour + ":" + mMinute;
                                maskEditText.append(date);
                            }
                        }, hour, minut, true);

                timePicker.show();
            }


        }, year, month, day);

        datePickerDialog.show();

    }


}