package com.jacosro.justdraw.drawers.shapes

import android.graphics.Paint
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import com.jacosro.justdraw.drawers.Drawer
import com.jacosro.justdraw.figures.Figure
import com.jacosro.justdraw.figures.VoidFigure
import com.jacosro.justdraw.figures.shapes.FigureShape
import com.jacosro.justdraw.util.FiguresQueue
import com.jacosro.justdraw.util.OnFiguresClearedListener

abstract class DrawerShape(paint: Paint) : Drawer(paint), OnFiguresClearedListener {

    private var registeredListener = false

    protected abstract val scaleGestureDetector: ScaleGestureDetector

    protected abstract val gestureDetector: GestureDetector

    protected val shapes: MutableList<FigureShape> = mutableListOf()

    protected var newFigure: Figure = VoidFigure()
        get() {
            val value = field
            field = VoidFigure()
            return value
        }

    protected var removeFigure: Figure = VoidFigure()
        get() {
            val value = field
            field = VoidFigure()
            return value
        }

    override fun onTouchEvent(event: MotionEvent, figures: FiguresQueue): Boolean {
        var retVal = scaleGestureDetector.onTouchEvent(event)

        if (!scaleGestureDetector.isInProgress) {
            retVal = gestureDetector.onTouchEvent(event) || retVal
        }

        if (!registeredListener)
            figures.addOnFiguresClearedListener(this)

        val toAdd = newFigure
        val toRemove = removeFigure

        if (toAdd !is VoidFigure) {
            figures += toAdd
        }

        if (toRemove !is VoidFigure) {
            figures.remove(toRemove)
        }

        return retVal
    }

//    abstract fun handleNewFigures(figures: FiguresQueue)

    override fun onCleared() {
        shapes.clear()
    }
}
