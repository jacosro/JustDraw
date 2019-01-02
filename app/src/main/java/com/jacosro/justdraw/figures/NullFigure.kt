package com.jacosro.justdraw.figures

import android.graphics.Canvas
import com.jacosro.justdraw.util.Paints

class NullFigure : Figure(Paints.getDefault()) {

    override fun draw(canvas: Canvas) {

    }

    override fun isDrawable(): Boolean {
        return false
    }

}