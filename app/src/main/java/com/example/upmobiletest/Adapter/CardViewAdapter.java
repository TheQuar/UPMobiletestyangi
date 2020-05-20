package com.example.upmobiletest.Adapter;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.telecom.Call;
import android.telecom.Call.Details;
import android.telephony.PhoneNumberUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.upmobiletest.Fragment.MyElon;
import com.example.upmobiletest.Fragment.ViewDriveBlankFragment;
import com.example.upmobiletest.Fragment.ViewPassangerBlankFragment;
import com.example.upmobiletest.R;

import java.util.ArrayList;
import java.util.Locale;

public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.CardViewHolder> {

    private Context context;
    private ArrayList<ElonAdapter> lisMyData;
    View view;

    public CardViewAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<ElonAdapter> getLisMyData() {
        return lisMyData;
    }

    public void setLisMyData(ArrayList<ElonAdapter> lisMyData) {
        this.lisMyData = lisMyData;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_card_view, parent, false);

        return new CardViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull final CardViewHolder holder, final int position) {

        holder.textManzil.setText(getLisMyData().get(position).getManzil());
        holder.textDateTime.setText(getLisMyData().get(position).getKetishVaqti());
        holder.textMoney.setText(java.text.NumberFormat.getNumberInstance(Locale.US).format(Integer.parseInt(getLisMyData().get(position).getNarxi())) + " so\'m");
        holder.textUserFirstname.setText(getLisMyData().get(position).getIsmi());
        holder.textUserPhoneNumber.setText(PhoneNumberUtils
                .formatNumber("+998" + getLisMyData().get(position).getTel(),
                        String.valueOf(PhoneNumberUtils.getFormatTypeForLocale(Locale.US))));

        //Glide.with(context).load(getLisMyData().get(position).getPhoto()).into(holder.imgPhoto);

        //shu yer

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                if (getLisMyData().get(position).getPosition() == 0) {
                    ViewDriveBlankFragment myFragment = new ViewDriveBlankFragment();
                    myFragment.arrayList = getLisMyData().get(position).getAllData();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.FramLayoutForFragment, myFragment).addToBackStack(null).commit();
                } else if (getLisMyData().get(position).getPosition() == 1) {
                    ViewPassangerBlankFragment myFragment = new ViewPassangerBlankFragment();
                    myFragment.arrayList = getLisMyData().get(position).getAllData();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.FramLayoutForFragment, myFragment).addToBackStack(null).commit();

                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return getLisMyData().size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {

        //ImageView imgPhoto;
        TextView textManzil, textDateTime, textMoney, textUserFirstname, textUserPhoneNumber;
        Button button;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            //   imgPhoto = itemView.findViewById(R.id.img_card);
            textManzil = itemView.findViewById(R.id.textManzil);
            textDateTime = itemView.findViewById(R.id.textKetishvaqti);
            textMoney = itemView.findViewById(R.id.textNarxi);
            textUserFirstname = itemView.findViewById(R.id.textIsmi);
            textUserPhoneNumber = itemView.findViewById(R.id.textTel);

            button = itemView.findViewById(R.id.btn_batafsil);

        }
    }
}
