package com.jacosro.justdraw.figures

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Point

class FigurePoint(val point: Point, paint: Paint) : Figure(paint) {

    override fun draw(canvas: Canvas) {
        canvas.drawPoint(point.x.toFloat(), point.y.toFloat(), paint)
    }

    override fun isDrawable(): Boolean {
        return true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other)
            return true

        if (other !is FigurePoint)
            return false

        return point == other.point && paint == other.paint
    }

    override fun hashCode(): Int {
        return point.hashCode() + paint.hashCode()
    }

    fun isPoint(point: Point) = this.point == point
}
