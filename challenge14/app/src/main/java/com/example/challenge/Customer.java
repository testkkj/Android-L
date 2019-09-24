package com.example.challenge;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Customer extends LinearLayout {
    TextView name, mobile, address;
    ImageView imageView;

    public Customer(Context context) {
        super(context);
        init(context);
    }

    public Customer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.customer, this, true);

        name = findViewById(R.id.name);
        mobile = findViewById(R.id.mobile);
        address = findViewById(R.id.address);
        imageView = findViewById(R.id.image);
    }

    public void setName(String n){
        name.setText(n);
    }

    public void setMobile(String m) {
        mobile.setText(m);
    }

    public void setAddress(String a) {
        address.setText(a);
    }

    public void setImageView(int i) {
        imageView.setImageResource(i);
    }
}
