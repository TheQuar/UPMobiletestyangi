package com.example.upmobiletest.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.telephony.PhoneNumberUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.upmobiletest.R;

import java.util.ArrayList;
import java.util.Locale;

public class ViewPassangerBlankFragment extends Fragment {

    private Context mcontext;
    private View rootview;
    public ArrayList<String> arrayList = new ArrayList<>();
    private TextView elon_1, elon_2, elon_3, elon_4, elon_5, elon_6, elon_7;


    public ViewPassangerBlankFragment() {
        // Required empty public constructor
    }


    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_view_passanger_blank, container, false);

        elon_1 = rootview.findViewById(R.id.ps_elon_1);
        elon_2 = rootview.findViewById(R.id.ps_elon_2);
        elon_3 = rootview.findViewById(R.id.ps_elon_3);
        elon_4 = rootview.findViewById(R.id.ps_elon_4);
        elon_5 = rootview.findViewById(R.id.ps_elon_5);
        elon_6 = rootview.findViewById(R.id.ps_elon_6);
        elon_7 = rootview.findViewById(R.id.ps_elon_7);


        elon_1.setText(arrayList.get(3)); //ismi
        elon_2.setText(arrayList.get(0)); //manzili
        elon_3.setText(arrayList.get(1)); //ketish vaqti
        elon_4.setText(java.text.NumberFormat
                .getNumberInstance(Locale.US)
                .format(Integer.parseInt(arrayList.get(2))) + " so\'m"); //narxi
        elon_5.setText(arrayList.get(8)); //joy soni
        elon_6.setText(arrayList.get(9)); //moshina nomi
        elon_7.setText(PhoneNumberUtils
                .formatNumber("+998" + arrayList.get(4),
                        String.valueOf(PhoneNumberUtils.getFormatTypeForLocale(Locale.US)))); //tel raqami


        return rootview;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mcontext = context;
    }
}
