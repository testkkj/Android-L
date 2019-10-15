package com.example.singlediary;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import lib.kingja.switchbutton.SwitchMultiButton;

public class Fragment1 extends Fragment {

    RecyclerView recyclerView;
    NoteAdapter noteAdapter;

    Context context;
    OnTabItemSelectedListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        this.context = context;

        if (context instanceof OnTabItemSelectedListener) {
            listener = (OnTabItemSelectedListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        if (context != null) {
            context = null;
            listener = null;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment1, container, false);

        initUI(viewGroup);

        return viewGroup;
    }

    private void initUI(ViewGroup viewGroup) {
        Button todayWriteButton = viewGroup.findViewById(R.id.todayWriteButton);
        todayWriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onTabSelected(1);
                }
            }
        });

        SwitchMultiButton switchButton = viewGroup.findViewById(R.id.switchButton);
        switchButton.setOnSwitchListener(new SwitchMultiButton.OnSwitchListener() {
            @Override
            public void onSwitch(int position, String tabText) {
                Toast.makeText(getContext(), tabText, Toast.LENGTH_LONG).show();

                noteAdapter.switchLayout(position);
                noteAdapter.notifyDataSetChanged();
            }
        });

        recyclerView = viewGroup.findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        noteAdapter = new NoteAdapter();

        noteAdapter.addItem(new Note(0, "0", "강남구 삼성동", "", "", "오늘 너무 행복해!", "0", "capture1.jpg", "2월10일"));
        noteAdapter.addItem(new Note(1, "1", "강남구 삼성동", "", "", "친구와 재미있게 놀았어", "0", "capture1.jpg", "2월11일"));
        noteAdapter.addItem(new Note(2, "0", "강남구 삼성동", "", "", "집에 왔는데 너무 피곤해 ㅠㅠ", "0", null, "2월13일"));

        recyclerView.setAdapter(noteAdapter);

        noteAdapter.setOnItemClickListener(new OnNoteItemClickListener() {
            @Override
            public void onItemClick(NoteAdapter.ViewHolder viewHolder, View view, int position) {
                Note item = noteAdapter.getItem(position);
                Toast.makeText(getContext(), "아이템 선택됨: " + item.getContents(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
