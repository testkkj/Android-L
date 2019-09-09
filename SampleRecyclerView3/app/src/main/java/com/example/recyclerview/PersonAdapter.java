package com.example.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> implements OnPersonItemClickListener {
    ArrayList<Person> items = new ArrayList<Person>();
    OnPersonItemClickListener onPersonItemClickListener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.person_item, parent, false);

        return new ViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Person item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        TextView textView1;

        public ViewHolder(View itemView, final OnPersonItemClickListener onPersonItemClickListener) {
            super(itemView);

            textView = itemView.findViewById(R.id.textView);
            textView1 = itemView.findViewById(R.id.textView2);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (onPersonItemClickListener != null) {
                        onPersonItemClickListener.onItemClick(ViewHolder.this, view, position);
                    }
                }
            });
        }

        public void setItem(Person item) {
            textView.setText(item.getName());
            textView1.setText(item.getMobile());
        }
    }

    public void setOnItemClickListener(OnPersonItemClickListener onItemClickListener) {
        this.onPersonItemClickListener = onItemClickListener;
    }

    @Override
    public void onItemClick(ViewHolder viewHolder, View view, int position) {
        if (onPersonItemClickListener != null) {
            onPersonItemClickListener.onItemClick(viewHolder, view, position);
        }
    }

    public void addItem(Person item) {
        items.add(item);
    }

    public void setItem(ArrayList<Person> items) {
        this.items = items;
    }

    public Person getItem(int position) {
        return items.get(position);
    }

    public Person setItem(int position, Person item) {
        return items.set(position, item);
    }
}
