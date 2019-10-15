package com.example.singlediary4;

import android.view.View;

public interface OnNoteItemClickListener {
    public void onItemClick(NoteAdapter.ViewHolder viewHolder, View view, int position);
}
