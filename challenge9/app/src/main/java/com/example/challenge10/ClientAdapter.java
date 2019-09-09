package com.example.challenge10;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ClientAdapter extends RecyclerView.Adapter<ClientAdapter.ViewHolder> {
    ArrayList<Client> items = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.client_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Client item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        TextView textView1;
        TextView textView2;

        public ViewHolder(View view) {
            super(view);

            textView = view.findViewById(R.id.textView);
            textView1 = view.findViewById(R.id.textView2);
            textView2 = view.findViewById(R.id.textView3);
        }

        public void setItem(Client item) {
            textView.setText(item.getName());
            textView1.setText(item.getBirth());
            textView2.setText(item.getMobile());
        }
    }

    public void addItem(Client client) {
        items.add(client);
        System.out.println(items);
    }

    public void setItems(ArrayList<Client> items) {
        this.items = items;
    }

    public Client getItem(int position) {
        return items.get(position);
    }

    public Client setItem(int position, Client client) {
        return items.set(position, client);
    }
}
