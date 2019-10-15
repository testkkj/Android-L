package com.example.challenge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    BestPaintBoard board;
    Button colorBtn, penBtn, eraserBtn, undoBtn;
    RadioButton radio01, radio02, radio03;

    LinearLayout legendLayout;
    Button colorLegendButton, sizeLegendButton;

    int mColor=0xff000000, mSize=2, oldColor, oldSize;
    boolean eraserSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final LinearLayout boardLayout = findViewById(R.id.boardLayout);

        colorBtn = findViewById(R.id.colorBtn);
        penBtn = findViewById(R.id.penBtn);
        eraserBtn = findViewById(R.id.eraserBtn);
        undoBtn = findViewById(R.id.undoBtn);

        radio01 = findViewById(R.id.radio01);
        radio02 = findViewById(R.id.radio02);
        radio03 = findViewById(R.id.radio03);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);

        board = new BestPaintBoard(this);
        board.setLayoutParams(params);
        board.setPadding(2,2,2,2);

        boardLayout.addView(board);

        legendLayout = findViewById(R.id.legendLayout);
        colorLegendButton = findViewById(R.id.colorLegendButton);
        sizeLegendButton = findViewById(R.id.sizeLegendButton);

        colorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ColorPaletteDialog.listener = new OnColorSelectedListener() {
                    @Override
                    public void onColorSelected(int color) {
                        mColor = color;
                        board.updatePaintProperty(mColor,mSize);
                        displayPaintProperty();
                    }
                };
                Intent intent = new Intent(getApplicationContext(), ColorPaletteDialog.class);
                startActivity(intent);
            }
        });

        penBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PenPaletteDialog.listener = new OnPenSelectedListener() {
                    @Override
                    public void onPenselected(int pen) {
                        mSize = pen;
                        board.updatePaintProperty(mColor,mSize);
                        displayPaintProperty();
                    }
                };
                Intent intent = new Intent(getApplicationContext(), PenPaletteDialog.class);
                startActivity(intent);
            }
        });

        eraserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eraserSelected=!eraserSelected;

                if (eraserSelected){
                    colorBtn.setEnabled(false);
                    penBtn.setEnabled(false);
                    undoBtn.setEnabled(false);

                    colorBtn.invalidate();
                    penBtn.invalidate();
                    undoBtn.invalidate();
                    oldColor=mColor;
                    oldSize=mSize;

                    mColor= Color.WHITE;
                    mSize=15;

                    board.updatePaintProperty(mColor,mSize);
                    displayPaintProperty();
                }else{
                    colorBtn.setEnabled(true);
                    penBtn.setEnabled(true);
                    undoBtn.setEnabled(true);

                    colorBtn.invalidate();
                    penBtn.invalidate();
                    undoBtn.invalidate();
                    oldColor=mColor;
                    oldSize=mSize;

                    mColor= Color.WHITE;
                    mSize=15;

                    board.updatePaintProperty(mColor,mSize);
                    displayPaintProperty();
                }
            }
        });

        undoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                board.undo();
            }
        });

        radio01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"RadioButton 01 clicked.", Toast.LENGTH_LONG).show();
                board.setCapStyle(Paint.Cap.BUTT);
            }
        });

        radio02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"RadioButton 02 clicked.", Toast.LENGTH_LONG).show();
                board.setCapStyle(Paint.Cap.ROUND);
            }
        });

        radio03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"RadioButton 03 clicked.", Toast.LENGTH_LONG).show();
                board.setCapStyle(Paint.Cap.SQUARE);
            }
        });
    }

    private void displayPaintProperty(){
        colorLegendButton.setBackgroundColor(mColor);
        sizeLegendButton.setText(""+mSize);
        legendLayout.invalidate();
    }
}
