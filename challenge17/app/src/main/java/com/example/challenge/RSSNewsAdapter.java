package com.example.challenge;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RSSNewsAdapter extends RecyclerView.Adapter<RSSNewsAdapter.ViewHolder> {

    ArrayList<RSSNewsItem> items = new ArrayList<RSSNewsItem>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.node_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RSSNewsItem item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(RSSNewsItem item) {
        items.add(item);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mIcon;
        private TextView mtext01;
        private TextView mtext02;
        private TextView mtext03;
        private WebView mtext04;

        public ViewHolder(View itemView) {
            super(itemView);

            mIcon = itemView.findViewById(R.id.iconItem);
            mtext01 = itemView.findViewById(R.id.dataItem01);
            mtext02 = itemView.findViewById(R.id.dataItem02);
            mtext03 = itemView.findViewById(R.id.dataItem03);
            mtext04 = itemView.findViewById(R.id.dataItem04);
        }

        public void setItem(RSSNewsItem item) {
            mIcon.setImageDrawable(item.getmIcon());

            mtext01.setText(item.getTitle());
            mtext02.setText(item.getPubDate());
            mtext03.setText(item.getCategory());
            String category = item.getCategory();
            mtext04.loadDataWithBaseURL("http://localhost/", item.getDescription(), "text/html", "utf-8", null);
        }
    }
}
