package com.example.challenge;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class fragment1 extends Fragment {
    EditText editText, editText1, editText2;
    OnDatabaseCallback callback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callback = (OnDatabaseCallback) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_fragment1, container, false);
        editText = (EditText) viewGroup.findViewById(R.id.editText);
        editText1 = (EditText) viewGroup.findViewById(R.id.editText1);
        editText2 = (EditText) viewGroup.findViewById(R.id.editText2);

        Button button = viewGroup.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editText.getText().toString();
                String author = editText1.getText().toString();
                String contents = editText2.getText().toString();

                callback.insert(name, author, contents);
                Toast.makeText(getContext(), "책 정보를 추가했습니다.", Toast.LENGTH_LONG).show();
            }
        });
        return viewGroup;
    }
}