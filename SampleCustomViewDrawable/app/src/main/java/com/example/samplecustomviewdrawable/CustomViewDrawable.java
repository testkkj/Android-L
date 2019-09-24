package com.example.samplecustomviewdrawable;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;

public class CustomViewDrawable extends View {

    private ShapeDrawable upperDrawable;
    private ShapeDrawable lowerDrawable;

    public CustomViewDrawable(Context context) {
        super(context);

        init(context);
    }

    public CustomViewDrawable(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    private void init(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();

        Resources resources = getResources();
        int blackColor = resources.getColor(R.color.color01);
        int grayColor = resources.getColor(R.color.color02);
        int darkGrayColor = resources.getColor(R.color.color03);

        upperDrawable = new ShapeDrawable();

        RectShape rectShape = new RectShape();
        rectShape.resize(width, height * 2 / 3);
        upperDrawable.setShape(rectShape);
        upperDrawable.setBounds(0, 0, width, height * 2 / 3);

        LinearGradient linearGradient = new LinearGradient(0, 0, 0, height * 2 / 3, grayColor, blackColor, Shader.TileMode.CLAMP);

        Paint paint = upperDrawable.getPaint();

        paint.setShader(linearGradient);

        lowerDrawable = new ShapeDrawable();

        RectShape rectShape1 = new RectShape();
        rectShape1.resize(width, height * 1 / 3);
        lowerDrawable.setShape(rectShape1);
        lowerDrawable.setBounds(0, height * 2 / 3, width, height);

        LinearGradient linearGradient1 = new LinearGradient(0, 0, 0, height * 1 / 3, blackColor, darkGrayColor, Shader.TileMode.CLAMP);

        Paint paint1 = lowerDrawable.getPaint();
        paint1.setShader(linearGradient1);

    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        upperDrawable.draw(canvas);
        lowerDrawable.draw(canvas);

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.YELLOW);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(16.0F);
        paint.setStrokeCap(Paint.Cap.BUTT);
        paint.setStrokeJoin(Paint.Join.MITER);

        Path path = new Path();
        path.moveTo(20,20);
        path.lineTo(120,20);
        path.lineTo(160,90);
        path.lineTo(180,80);
        path.lineTo(200,120);

        canvas.drawPath(path, paint);

        paint.setColor(Color.WHITE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeJoin(Paint.Join.ROUND);

        path.offset(30,120);
        canvas.drawPath(path, paint);

        paint.setColor(Color.CYAN);
        paint.setStrokeCap(Paint.Cap.SQUARE);
        paint.setStrokeJoin(Paint.Join.BEVEL);

        path.offset(30,120);
        canvas.drawPath(path, paint);
    }
}
