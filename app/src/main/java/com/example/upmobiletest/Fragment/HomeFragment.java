package com.example.upmobiletest.Fragment;


import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.TableLayout;
import android.widget.Toast;

import com.example.upmobiletest.Activity.HomeNavigation;
import com.example.upmobiletest.Adapter.ViewPageAdapter;
import com.example.upmobiletest.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private Context mContext;
    private View rootView;
    private SwipeRefreshLayout swiper1;

    public HomeFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        swiper1 = rootView.findViewById(R.id.swiper1);

        if (!LoadData()) {
            Toast.makeText(mContext, "Ma'lumot yuklashda xatolik", Toast.LENGTH_SHORT).show();
        }

        swiper1.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(LoadData())
                swiper1.setRefreshing(false);
            }
        });
        return rootView;
        //  return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    private boolean LoadData() {
        String[] s = {"Xaydovchi", "Yo`lovchi"};
        TabLayout tabLayout = (TabLayout) rootView.findViewById(R.id.tablayout);
        ViewPager viewPager = (ViewPager) rootView.findViewById(R.id.viewpage);

        try {
            viewPager.setAdapter(new ViewPageAdapter(mContext, s));
            tabLayout.setupWithViewPager(viewPager);
        }catch (Exception e){ return false;}

        return true;
        }
    }
