package com.example.upmobiletest.Adapter;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class ElonAdapter implements Parcelable {

    private String Manzil, KetishVaqti, Narxi, Ismi, Tel, Joyi, Id, IdUser, OdamSoni, CarName, CarNum, YopishVaqti;
    int position;

    public String getCarNum() {
        return CarNum;
    }

    public void setCarNum(String carNum) {
        CarNum = carNum;
    }

    public String getJoyi() {
        return Joyi;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setJoyi(String joyi) {
        Joyi = joyi;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getIdUser() {
        return IdUser;
    }

    public void setIdUser(String idUser) {
        IdUser = idUser;
    }

    public String getOdamSoni() {
        return OdamSoni;
    }

    public void setOdamSoni(String odamSoni) {
        OdamSoni = odamSoni;
    }

    public String getCarName() {
        return CarName;
    }

    public void setCarName(String carName) {
        CarName = carName;
    }

    public String getYopishVaqti() {
        return YopishVaqti;
    }

    public void setYopishVaqti(String yopishVaqti) {
        this.YopishVaqti = yopishVaqti;
    }

    public String getManzil() {
        return Manzil;
    }

    public void setManzil(String manzil) {
        Manzil = manzil;
    }

    public String getKetishVaqti() {
        return KetishVaqti;
    }

    public void setKetishVaqti(String ketishVaqti) {
        KetishVaqti = ketishVaqti;
    }

    public String getNarxi() {
        return Narxi;
    }

    public void setNarxi(String narxi) {
        Narxi = narxi;
    }

    public String getIsmi() {
        return Ismi;
    }

    public void setIsmi(String ismi) {
        Ismi = ismi;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
    }

    public ElonAdapter() {
    }


    protected ElonAdapter(Parcel in) {
        Manzil = in.readString();
        KetishVaqti = in.readString();
        Narxi = in.readString();
        Ismi = in.readString();
        Tel = in.readString();
        Joyi = in.readString();
        Id = in.readString();
        IdUser = in.readString();
        OdamSoni = in.readString();
        CarName = in.readString();
        CarNum = in.readString();
        YopishVaqti = in.readString();
        position = in.readInt();
    }



    public static final Creator<ElonAdapter> CREATOR = new Creator<ElonAdapter>() {
        @Override
        public ElonAdapter createFromParcel(Parcel in) {
            return new ElonAdapter(in);
        }

        @Override
        public ElonAdapter[] newArray(int size) {
            return new ElonAdapter[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Manzil);
        dest.writeString(KetishVaqti);
        dest.writeString(Narxi);
        dest.writeString(Ismi);
        dest.writeString(Tel);
        dest.writeString(Joyi);
        dest.writeString(Id);
        dest.writeString(IdUser);
        dest.writeString(OdamSoni);
        dest.writeString(CarName);
        dest.writeString(CarNum);
        dest.writeString(YopishVaqti);
        dest.writeInt(position);
    }

    public ArrayList<String> getAllData() {
        ArrayList<String> mydata = new ArrayList<>();
        mydata.add(Manzil);
        mydata.add(KetishVaqti);
        mydata.add(Narxi);
        mydata.add(Ismi);
        mydata.add(Tel);
        mydata.add(Joyi);
        mydata.add(Id);
        mydata.add(IdUser);
        mydata.add(OdamSoni);
        mydata.add(CarName);
        mydata.add(CarNum);
        mydata.add(YopishVaqti);

        return mydata;
    }
}