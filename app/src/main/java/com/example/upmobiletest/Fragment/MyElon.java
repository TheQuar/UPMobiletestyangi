package com.example.upmobiletest.Fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.upmobiletest.Adapter.MyElonViewPageAdapter;
import com.example.upmobiletest.Adapter.ViewPageAdapter;
import com.example.upmobiletest.R;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyElon extends Fragment {
    private Context mContext;
    private View rootView;


    public MyElon() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_my_elon, container, false);

        String[] s = {"Xaydovchi", "Yo\'lovchi"};

        TabLayout tabLayout = (TabLayout) rootView.findViewById(R.id.my_elon_tab_layout);
        ViewPager viewPager = (ViewPager) rootView.findViewById(R.id.my_elon_viewpage);

        viewPager.setAdapter(new MyElonViewPageAdapter(mContext, s));
        tabLayout.setupWithViewPager(viewPager);

        return rootView;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }
}
