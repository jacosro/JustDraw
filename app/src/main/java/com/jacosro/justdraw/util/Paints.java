package com.jacosro.justdraw.util;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;

import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class Paints {

    public static final float DEFAULT_STROKE_WIDTH = 30f;
    public static final int DEFAULT_COLOR = Color.BLACK;

    private static Paint basePaint() {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(DEFAULT_STROKE_WIDTH);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);

        return paint;
    }

    public static Paint getRandomColorPaint() {
        Random random = new Random();
        Paint paint = basePaint();
        paint.setARGB(random.nextInt(), random.nextInt(), random.nextInt(), random.nextInt());

        return paint;
    }

    public static Paint getPaintWithColor(int color) {
        Paint paint = basePaint();
        paint.setColor(color);

        return paint;
    }

    public static Paint getErasePaint() {
        return getPaintWithColor(Color.WHITE);
    }


    @NotNull
    public static Paint getDefault() {
        return getPaintWithColor(DEFAULT_COLOR);
    }
}