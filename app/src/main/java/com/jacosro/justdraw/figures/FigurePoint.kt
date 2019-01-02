package com.jacosro.justdraw.figures

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Point

class FigurePoint(val point: Point, paint: Paint) : Figure(paint) {

    override fun draw(canvas: Canvas) {
        canvas.drawPoint(point.x.toFloat(), point.y.toFloat(), paint)
    }

    override fun isDrawable(): Boolean {
        return point != null
    }
}
