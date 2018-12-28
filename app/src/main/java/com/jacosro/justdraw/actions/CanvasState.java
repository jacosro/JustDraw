package com.jacosro.justdraw.actions;

import android.graphics.Bitmap;

public class CanvasState {

    private Bitmap canvas;

    public CanvasState(Bitmap canvas) {
        this.canvas = canvas;
    }

    public Bitmap getState() {
        return canvas;
    }
}
