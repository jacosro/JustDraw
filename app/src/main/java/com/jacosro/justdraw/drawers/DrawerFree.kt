package com.jacosro.justdraw.drawers

import android.content.Context
import android.graphics.Paint
import android.graphics.Point
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import com.jacosro.justdraw.figures.Figure
import com.jacosro.justdraw.figures.FigureCurve
import com.jacosro.justdraw.figures.FigurePoint
import com.jacosro.justdraw.figures.NullFigure
import com.jacosro.justdraw.util.FiguresQueue
import java.lang.NullPointerException

open class DrawerFree(context: Context, paint: Paint) : Drawer(paint) {

    override val type: Drawers.Type = Drawers.Type.FREE

    private val gestureDetector: GestureDetector
    private var figure: Figure = NullFigure()

    private var newFigure = false

    init {
        gestureDetector = GestureDetector(context, object: GestureDetector.SimpleOnGestureListener() {

            override fun onDown(e: MotionEvent?): Boolean {
                figure = NullFigure()
                newFigure = false
                return true
            }

            override fun onSingleTapUp(e: MotionEvent?): Boolean {
                val point = Point(e!!.x.toInt(), e.y.toInt())

                figure = FigurePoint(point, paint)
                newFigure = true
                return true
            }

            override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
                if (figure !is FigureCurve) {
                    val startPoint = Point(e1!!.x.toInt(), e1.y.toInt())
                    figure = FigureCurve(mutableListOf(startPoint), paint)
                    newFigure = true
                } else {
                    newFigure = false
                }

                figure as FigureCurve += Point(e2!!.x.toInt(), e2.y.toInt())

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