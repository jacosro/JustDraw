package com.jacosro.justdraw;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.jacosro.justdraw.actions.CareTaker;
import com.jacosro.justdraw.actions.Originator;

import java.util.ArrayList;
import java.util.List;

public class DrawingView extends View {

    private boolean restoreLastState;

    private List<Drawable> drawables;

    public DrawingView(Context context) {
        super(context);

        init();
    }

    public DrawingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public DrawingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        drawables = new ArrayList<>();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (restoreLastState) {
            canvas.restore();
            restoreLastState = false;
            return;
        }


    }
}
