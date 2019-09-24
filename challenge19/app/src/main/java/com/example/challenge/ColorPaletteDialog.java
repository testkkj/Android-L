package com.example.challenge;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

import androidx.annotation.Nullable;

public class ColorPaletteDialog extends Activity {
    GridView grid;
    Button closeBtn;
    ColorDataAdapter adapter;

    public static OnColorSelectedListener listener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog);

        this.setTitle("Color Selection");
        grid = findViewById(R.id.colorGrid);
        closeBtn = findViewById(R.id.closeBtn);
        grid.setColumnWidth(14);
        grid.setBackgroundColor(Color.GRAY);
        grid.setVerticalSpacing(4);
        grid.setHorizontalSpacing(4);

        adapter = new ColorDataAdapter(this);
        grid.setAdapter(adapter);
        grid.setNumColumns(adapter.getNumColumns());
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}

class ColorDataAdapter extends BaseAdapter {
    Context mContext;
    public static final int[] color = new int[]{
            0xff000000, 0xff00007f, 0xff0000ff, 0xff007f00, 0xff007f7f, 0xff00ff00, 0xff00ff7f,
            0xff00ffff, 0xff7f007f, 0xff7f00ff, 0xff7f7f00, 0xff7f7f7f, 0xffff0000, 0xffff007f,
            0xffff00ff, 0xffff7f00, 0xffff7f7f, 0xffff7fff, 0xffffff00, 0xffffff7f, 0xffffffff
    };
    int rowCount;
    int columnCount;

    public ColorDataAdapter(Context context) {
        super();
        mContext = context;
        rowCount = 3;
        columnCount = 7;
    }

    public int getNumColumns() {
        return columnCount;
    }

    public int getCount() {
        return rowCount * columnCount;
    }

    public Object getItem(int position) {
        return colors[position];
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View view, ViewGroup parent) {
        GridView.LayoutParams params = new GridView.LayoutParams(GridView.LayoutParams.MATCH_PARENT, GridView.LayoutParams.MATCH_PARENT);

        Button altem = new Button(mContext);
        altem.setText(" ");
        altem.setLayoutParams(params);
        altem.setPadding(4, 4, 4, 4);
        altem.setBackgroundColor(colors[position]);
        altem.setHeight(parent.getHeight() / 3);
        altem.setTag(colors[position]);

        altem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ColorPaletteDialog.listener != null) {
                    ColorPaletteDialog.listener.onColorSelected((Integer) view.getTag()).intValue();
                }
                ((ColorPaletteDialog) mContext).fileList();
            }
        });
        return altem;
    }
}