package com.example.challenge;

import android.app.DatePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class form_fragment extends Fragment {
    EditText editText;
    EditText editText1;
    DatePickerDialog dialog;
    Date selectDate;
    Button button;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_form_fragment, container, false);

        editText = viewGroup.findViewById(R.id.editText);
        editText1 = viewGroup.findViewById(R.id.editText2);
        button = viewGroup.findViewById(R.id.button);
        Date date = new Date();

        button.setText(dateFormat.format(date));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });

        Button button1 = viewGroup.findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editText.getText().toString();
                String age = editText1.getText().toString();
                String birth = button.getText().toString();

                if (name.equals("") || age.equals("")) {
                    Toast.makeText(getContext(), "이름/나이 확인", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getContext(), "이름:"+name+"\n나이:"+age+"\n생일:"+birth, Toast.LENGTH_LONG).show();                }
            }
        });
        return viewGroup;
    }

    private void showDateDialog() {
        String birthDate = button.getText().toString();
        Calendar calendar = Calendar.getInstance();
        Date curBirth = new Date();

        try{
            curBirth = dateFormat.parse(birthDate);
        }catch (Exception e) {
            e.printStackTrace();
        }
        calendar.setTime(curBirth);

        int curYear = calendar.get(Calendar.YEAR);
        int curMonth = calendar.get(Calendar.MONTH);
        int curDay = calendar.get(Calendar.DAY_OF_MONTH);

        dialog = new DatePickerDialog(getContext(), onDateSetListener, curYear, curMonth, curDay);
        dialog.show();
    }

    private DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            Calendar select = Calendar.getInstance();
            select.set(Calendar.YEAR, i);
            select.set(Calendar.MONTH, i1);
            select.set(Calendar.DAY_OF_MONTH, i2);

            Date curDate = select.getTime();
            button.setText(dateFormat.format(curDate));
        }
    };
}
