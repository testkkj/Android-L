package com.example.printout;

import android.os.Parcel;
import android.os.Parcelable;

public class BookData implements Parcelable {

    int number;
    String title;
    String author;
    String publisher;
    int price;

    public BookData(int num, String tit, String aut, String pub, int pri){
        number = num;
        title = tit;
        author = aut;
        publisher = pub;
        price = pri;
    }
    public BookData(Parcel parcel){
        number = parcel.readInt();
        title = parcel.readString();
        author = parcel.readString();
        publisher = parcel.readString();
        price = parcel.readInt();
    }

    public static final Parcelable.Creator CREATER = new Parcelable.Creator(){

        @Override
        public Object createFromParcel(Parcel parcel) {
            return new BookData(parcel);
        }

        @Override
        public Object[] newArray(int i) {
            return new Object[0];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(number);
        parcel.writeString(title);
        parcel.writeString(author);
        parcel.writeString(publisher);
        parcel.writeInt(price);
    }
}
