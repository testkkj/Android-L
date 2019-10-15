package com.example.singlediary;

import android.view.View;

public interface OnNoteItemClickListener {
    public void onItemClick(NoteAdapter.ViewHolder viewHolder, View view, int position);
}
