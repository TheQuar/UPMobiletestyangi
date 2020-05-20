package com.example.upmobiletest.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.example.upmobiletest.PostRetrofit.GetAdDataPassangerRetrofit;
import com.example.upmobiletest.PostRetrofit.GetDriveAdDataRetrofit;
import com.example.upmobiletest.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewPageAdapter extends PagerAdapter {

    private Context mContext;

    private String[] tabTitles;

    public ViewPageAdapter(Context context, String[] tabTitle) {
        mContext = context;
        tabTitles = tabTitle;
    }


    @Override
    public int getCount() {
        return tabTitles.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == (LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.view_pager, container, false);

        container.addView(view);

        final RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        final ArrayList<ElonAdapter> list = new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        final CardViewAdapter cardViewAdapter = new CardViewAdapter(mContext);

        if (position == 0) {
            //xaydo'vchi
            Call<ResponseBody> call = GetDriveAdDataRetrofit
                    .getInstance()
                    .getDriveAdData()
                    .CallGetDriveAdData("test");

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        String s = response.body().string();
                        if (!s.equals("null")) {
                            JSONObject status = new JSONObject(s);
                            JSONArray data = status.getJSONArray("data");
                            if (status.getString("status").equals("true")) {
                                for (int i = 0; i < data.length(); i++) {
                                    ElonAdapter elonAdapter = new ElonAdapter();

                                    elonAdapter.setIdUser(data.getJSONObject(i).getString("iduser"));
                                    elonAdapter.setManzil(data.getJSONObject(i).getString("addressfrom"));
                                    elonAdapter.setKetishVaqti(data.getJSONObject(i).getString("leavetime"));
                                    elonAdapter.setNarxi(data.getJSONObject(i).getString("price"));
                                    elonAdapter.setJoyi(data.getJSONObject(i).getString("places"));
                                    elonAdapter.setOdamSoni(data.getJSONObject(i).getString("passangers"));
                                    elonAdapter.setCarName(data.getJSONObject(i).getString("carname"));
                                    elonAdapter.setCarNum(data.getJSONObject(i).getString("carnum"));
                                    elonAdapter.setYopishVaqti(data.getJSONObject(i).getString("isclosed"));
                                    elonAdapter.setIsmi(data.getJSONObject(i).getString("name"));
                                    elonAdapter.setTel(data.getJSONObject(i).getString("phone"));

                                    elonAdapter.setPosition(position);

                                    list.add(elonAdapter);

                                }

                                cardViewAdapter.setLisMyData(list);
                                recyclerView.setAdapter(cardViewAdapter);

                            }
                        }
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                        Log.e("View page erorr: " , e.getMessage());
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });

//            list.addAll(GetAdDataDrive.getListData());
        } else {
            //yo'lo'vchi
            Call<ResponseBody> call = GetAdDataPassangerRetrofit
                    .getInstance()
                    .getAdDataPassangerRetrofit()
                    .CallGetAdDataPassanger("test");

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                    try {
                        String s = response.body().string();
                        if (!s.equals("null")) {
                            JSONObject status = new JSONObject(s);
                            JSONArray data = status.getJSONArray("data");
                            if (status.getString("status").equals("true")) {
                                for (int i = 0; i < data.length(); i++) {
                                    ElonAdapter elonAdapter = new ElonAdapter();
                                    elonAdapter.setIdUser(data.getJSONObject(i).getString("iduser"));
                                    elonAdapter.setManzil(data.getJSONObject(i).getString("addressfrom"));
                                    elonAdapter.setKetishVaqti(data.getJSONObject(i).getString("leavedate"));
                                    elonAdapter.setNarxi(data.getJSONObject(i).getString("price"));
                                    elonAdapter.setOdamSoni(data.getJSONObject(i).getString("passangers"));
                                    elonAdapter.setCarName(data.getJSONObject(i).getString("cartype"));
                                    elonAdapter.setYopishVaqti(data.getJSONObject(i).getString("isclosed"));
                                    elonAdapter.setIsmi(data.getJSONObject(i).getString("name"));
                                    elonAdapter.setTel(data.getJSONObject(i).getString("phone"));

                                    elonAdapter.setPosition(position);

                                    list.add(elonAdapter);

                                }

                                cardViewAdapter.setLisMyData(list);
                                recyclerView.setAdapter(cardViewAdapter);

                            }
                        }
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });
            // list.addAll(GetAdDataTrav.getListData());
        }


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
