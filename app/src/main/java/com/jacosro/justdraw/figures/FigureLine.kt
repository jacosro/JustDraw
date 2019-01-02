package com.jacosro.justdraw.figures

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Point

class FigureLine constructor(
        var start: Point?,
        var end: Point?,
        paint: Paint): Figure(paint) {

    override fun draw(canvas: Canvas) {
        if (!isDrawable()) {
            return
        }

        canvas.drawLine(
            start!!.x.toFloat(),
            start!!.y.toFloat(),
            end!!.x.toFloat(),
            end!!.y.toFloat(),
            paint
        )
    }

    override fun isDrawable(): Boolean {
        return start != null && end != null
    }
}
