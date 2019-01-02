package com.jacosro.justdraw.figures

import android.graphics.Canvas
import android.graphics.Paint
import android.view.MotionEvent
import com.jacosro.justdraw.util.Paints

abstract class Figure(var paint: Paint) {

    var isToRemove = false
    private set

    abstract fun draw(canvas: Canvas)

    init {
        paint = Paint(paint)
    }

    fun markToRemove() {
        isToRemove = true
    }

    abstract fun isDrawable(): Boolean
}