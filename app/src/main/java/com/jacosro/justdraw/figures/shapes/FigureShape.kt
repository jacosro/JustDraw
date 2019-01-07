package com.jacosro.justdraw.figures.shapes

import android.graphics.Paint
import com.jacosro.justdraw.figures.Figure

abstract class FigureShape(paint: Paint): Figure(paint) {

    override fun isDrawable(): Boolean {
        return true
    }
}