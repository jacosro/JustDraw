package com.jacosro.justdraw.actions;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class CareTaker {

    private Queue<CanvasState> storedCanvasStates;

    public CareTaker() {
        storedCanvasStates = new ArrayDeque<>();
    }

    public void add(CanvasState canvasState) {
        storedCanvasStates.add(canvasState);
    }

    public CanvasState poll() {
        return storedCanvasStates.poll();
    }

}
