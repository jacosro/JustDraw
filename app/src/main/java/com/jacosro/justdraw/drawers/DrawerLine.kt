package com.jacosro.justdraw.drawers

import android.content.Context
import android.graphics.Paint
import android.graphics.Point
import android.view.GestureDetector
import android.view.MotionEvent
import com.jacosro.justdraw.R
import com.jacosro.justdraw.figures.Figure
import com.jacosro.justdraw.figures.FigureLine
import com.jacosro.justdraw.figures.FigurePoint
import com.jacosro.justdraw.figures.NullFigure
import com.jacosro.justdraw.util.FiguresQueue

class DrawerLine(context: Context, paint: Paint) : Drawer(paint) {

    override val type = Drawers.Type.LINE
    private lateinit var figure: FigureLine

    private var newFigure = false

    private val gestureDetector: GestureDetector

    init {
        gestureDetector = GestureDetector(context, object: GestureDetector.SimpleOnGestureListener() {
            override fun onDown(e: MotionEvent?): Boolean {
                val startPoint = Point(e!!.x.toInt(), e.y.toInt())

                figure = FigureLine(startPoint, null, paint)
                return true
            }

            override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
                newFigure = !figure.isDrawable()

                figure.end = Point(e2!!.x.toInt(), e2.y.toInt())

                return true
            }

        })
    }

    override fun onTouchEvent(event: MotionEvent, figures: FiguresQueue) {
        newFigure = false

        gestureDetector.onTouchEvent(event)

        if (newFigure) {
            figures += figure
        }
    }
}
