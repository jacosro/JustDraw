package com.jacosro.justdraw.figures

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Point
import java.util.ArrayList

open class FigureCurve constructor(
        private val points: MutableList<Point> = ArrayList(),
        paint: Paint
    ) : Figure(paint) {


    override fun draw(canvas: Canvas) {
        for (i in 1..(points.size - 2)) {
            val from = points[i - 1]
            val to = points[i]

            canvas.drawLine(
                    from.x.toFloat(), from.y.toFloat(),
                    to.x.toFloat(), to.y.toFloat(),
                    paint
            )
        }
    }

    fun addPoint(point: Point) {
        points += point
    }

    override fun isDrawable(): Boolean {
        return points.size > 1
    }

    operator fun plusAssign(point: Point) = addPoint(point)
}
