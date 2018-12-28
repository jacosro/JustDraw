package com.jacosro.justdraw.actions;

import android.graphics.Bitmap;

public class Originator {

    private Bitmap state;

    public void setState(Bitmap state) {
        this.state = state;
    }

    public Bitmap getState() {
        return state;
    }

    public CanvasState saveToCanvasState() {
        return new CanvasState(state);
    }

    public void restoreFromCanvasState(CanvasState memento) {
        state = memento.getState();
    }
}
