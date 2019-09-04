package com.example.challenge;

import android.os.Parcel;
import android.os.Parcelable;

public class ConsumerData implements Parcelable {
    String name;
    int age;
    String gender;

    public ConsumerData(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public ConsumerData(Parcel parcel) {
        name = parcel.readString();
        age = parcel.readInt();
        gender = parcel.readString();
    }

    public static final Parcelable.Creator<ConsumerData> CREATOR = new Creator<ConsumerData>() {
        @Override
        public ConsumerData createFromParcel(Parcel parcel) {
            return new ConsumerData(parcel);
        }

        @Override
        public ConsumerData[] newArray(int i) {
            return new ConsumerData[0];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(age);
        parcel.writeString(gender);
    }
}
