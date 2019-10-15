package com.example.challenge;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class TouchBoard extends View {
    private Paint paint;
    private Paint bestPaint;
    int rectWidth = 200;
    int rectHeight = 200;
    int curX=100;
    int curY=100;

    public TouchBoard(Context context) {
        super(context);
        init(context);
    }

    public TouchBoard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(Context context){
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);

        bestPaint = new Paint();
        bestPaint.setStyle(Paint.Style.FILL);
        bestPaint.setColor(Color.WHITE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0,0,getWidth(),getHeight(),bestPaint);
        canvas.drawRect(curX,curY,curX+rectWidth,curY+rectHeight,paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action==MotionEvent.ACTION_DOWN){
            curX = (int) event.getX();
            curY = (int) event.getY();
            invalidate();
        }else if (action==MotionEvent.ACTION_MOVE){
            curX = (int) event.getX();
            curY = (int) event.getY();
            invalidate();
        } else if (action==MotionEvent.ACTION_UP){

        }
        return true;
    }
}