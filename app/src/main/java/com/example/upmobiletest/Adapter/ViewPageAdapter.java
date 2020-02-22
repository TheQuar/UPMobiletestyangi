package com.example.upmobiletest.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;

import com.example.upmobiletest.R;

public class ViewPageAdapter extends PagerAdapter {

    private Context mContext;

    private String[] tabTitles;

    public ViewPageAdapter(Context context, String[] tabTitle){
        mContext = context;
        tabTitles = tabTitle;
    }


    @Override
    public int getCount() {
        return tabTitles.length;
    }

    @Override
    public boolean isViewFromObject( View view, Object object) {
        return (view==(LinearLayout)object);
    }

    @Override
    public Object instantiateItem( ViewGroup container, int position) {
        LayoutInflater inflater =(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.view_pager, container, false);

        TextView textView = view.findViewById(R.id.textItem);
        textView.setText(tabTitles[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
