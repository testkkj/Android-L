package com.example.challenge;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

import java.util.Stack;

public class BestPaintBoard extends View {
    Stack undos = new Stack();
    public static int maxUndos = 10;
    Canvas mCanvas;
    Bitmap mBitmap;
    final Paint mPaint;

    float lastX;
    float lastY;

    private final Path mPath = new Path();
    private float mCurveEndX;
    private float mCurveEndY;

    private int minvalidateExtraBorder = 10;
    static final float TOUCH_TOLERANCE = 8;

    public BestPaintBoard(Context context) {
        super(context);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(0xff000000);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(2.0f);
        mPaint.setDither(true);

        lastX = -1;
        lastY = -1;
    }

    public void saveUndo() {
        if (mBitmap == null) {
            return;
        }
        while (undos.size() >= maxUndos) {
            Bitmap i = (Bitmap) undos.get(undos.size() - 1);
            i.recycle();
            undos.remove(i);
        }
        Bitmap img = Bitmap.createBitmap(mBitmap.getWidth(), mBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas();
        canvas.setBitmap(img);
        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
        undos.push(img);
    }

    public void undo() {
        Bitmap prev = null;
        try {
            prev = (Bitmap) undos.pop();
        } catch (Exception e) {

        }

        if (prev != null) {
            mCanvas.drawColor(Color.WHITE);
            mCanvas.drawBitmap(prev, 0, 0, mPaint);
            invalidate();
            prev.recycle();
        }
    }

    public void updatePaintProperty(int color, int size) {
        mPaint.setColor(color);
        mPaint.setStrokeWidth(size);
    }

    public void setCapStyle(Paint.Cap capStyle) {
        mPaint.setStrokeCap(capStyle);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w > 0 && h > 0) {
            Bitmap img = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas();
            canvas.setBitmap(img);
            mBitmap = img;
            mCanvas = canvas;
            mCanvas.drawColor(Color.WHITE);
            invalidate();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mBitmap != null) {
            canvas.drawBitmap(mBitmap, 0, 0, null);
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();

        switch (action) {
            case MotionEvent.ACTION_UP:
                Rect rect = processMove(event);
                if (rect != null) {
                    invalidate(rect);
                }
                mPath.rewind();
                return true;

            case MotionEvent.ACTION_DOWN:
                saveUndo();
                rect = touchDown(event);
                if (rect != null) {
                    invalidate(rect);
                }
                mPath.rewind();
                return true;

            case MotionEvent.ACTION_MOVE:
                rect = processMove(event);
                if (rect != null) {
                    invalidate(rect);
                }
                mPath.rewind();
                return true;
        }
        return false;
    }

    private Rect touchDown(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        lastX = x;
        lastY = y;

        Rect mInvalidRect = new Rect();
        mPath.moveTo(x, y);

        final int border = minvalidateExtraBorder;
        mInvalidRect.set((int) x - border, (int) y - border, (int) x + border, (int) y + border);

        mCurveEndX = x;
        mCurveEndY = y;
        mCanvas.drawPath(mPath, mPaint);
        return mInvalidRect;
    }

    private Rect processMove(MotionEvent event) {
        final float x = event.getX();
        final float y = event.getY();

        final float dx = Math.abs(x - lastX);
        final float dy = Math.abs(x - lastY);

        Rect mInvalidRect = new Rect();
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            final int border = minvalidateExtraBorder;
            mInvalidRect.set((int) mCurveEndX - border, (int) mCurveEndY - border, (int) mCurveEndX + border, (int) mCurveEndY + border);

            float cX = mCurveEndX = (x + lastX) / 2;
            float cY = mCurveEndY = (x + lastY) / 2;

            mPath.quadTo(lastX, lastY, cX, cY);

            mInvalidRect.union((int) lastX - border, (int) lastY - border, (int) lastX + border, (int) lastY + border);

            mInvalidRect.union((int) cX - border, (int) cY - border, (int) cX + border, (int) cY + border);

            lastX = x;
            lastY = y;

            mCanvas.drawPath(mPath, mPaint);
        }
        return mInvalidRect;
    }
}
