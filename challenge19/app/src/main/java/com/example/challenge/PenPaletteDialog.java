package com.example.challenge;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

import androidx.annotation.Nullable;

public class PenPaletteDialog extends Activity {
    GridView grid;
    Button closeBtn;
    PenDataAdapter adapter;

    public static OnPenSelectedListener listener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog);
        this.setTitle("Pen Selection");

        grid = findViewById(R.id.colorGrid);
        closeBtn = findViewById(R.id.closeBtn);
        grid.setColumnWidth(14);
        grid.setBackgroundColor(Color.GRAY);
        grid.setVerticalSpacing(4);
        grid.setHorizontalSpacing(4);

        adapter = new PenDataAdapter(this);
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

class PenDataAdapter extends BaseAdapter {
    Context mContext;

    public static final int[] pens = new int[]{
            1, 2, 3, 4, 5,
            6, 7, 8, 9, 10,
            11, 13, 15, 17, 20
    };
    int rowCount;
    int columnCount;

    public PenDataAdapter(Context context) {
        super();
        mContext = context;
        rowCount = 3;
        columnCount = 5;
    }

    public int getNumColumns() {
        return columnCount;
    }

    public int getCount() {
        return rowCount * columnCount;
    }

    public Object getItem(int position) {
        return pens[position];
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View view, ViewGroup parent) {

        GridView.LayoutParams params = new GridView.LayoutParams(GridView.LayoutParams.MATCH_PARENT, GridView.LayoutParams.MATCH_PARENT);

        int areaWidth = 10;
        int areaHeight = 20;

        Bitmap penBitmap = Bitmap.createBitmap(areaWidth, areaHeight, Bitmap.Config.ARGB_8888);
        Canvas penCanvas = new Canvas();
        penCanvas.setBitmap(penBitmap);

        Paint mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        penCanvas.drawRect(0, 0, areaWidth, areaHeight, mPaint);

        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth((float) pens[position]);
        penCanvas.drawLine(0, areaHeight / 2, areaWidth - 1, areaHeight / 2, mPaint);
        BitmapDrawable penDrawable = new BitmapDrawable(mContext.getResources(), penBitmap);

        Button altem = new Button(mContext);
        altem.setText(" ");
        altem.setLayoutParams(params);
        altem.setPadding(4, 4, 4, 4);
        altem.setBackgroundDrawable(penDrawable);
        altem.setHeight(parent.getHeight() / 3);
        altem.setTag(pens[position]);

        altem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (PenPaletteDialog.listener != null) {
                    PenPaletteDialog.listener.onPenselected(((Integer) view.getTag()).intValue());
                }
                ((PenPaletteDialog) mContext).fileList();
            }
        });
        return altem;
    }
}