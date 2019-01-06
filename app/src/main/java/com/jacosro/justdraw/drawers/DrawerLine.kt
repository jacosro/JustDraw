package com.jacosro.justdraw.drawers

import android.content.Context
import android.graphics.Paint
import android.graphics.Point
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import com.jacosro.justdraw.R
import com.jacosro.justdraw.figures.*
import com.jacosro.justdraw.util.FiguresQueue

class DrawerLine(paint: Paint) : Drawer(paint) {

    override val type = Drawers.Type.LINE

    private val pointersFigures: MutableMap<Int, Figure> = mutableMapOf()

    override fun onTouchEvent(event: MotionEvent, figures: FiguresQueue) {
        val index = event.actionIndex
        val id = event.getPointerId(index)

        when {
            event.action == MotionEvent.ACTION_DOWN ||
                    event.actionMasked == MotionEvent.ACTION_POINTER_DOWN -> {
                pointersFigures[id] = FigureLine(Point(event.getX(index).toInt(), event.getY(index).toInt()), null, paint)
            }

            event.action == MotionEvent.ACTION_MOVE -> {
               for (i in 0 until event.pointerCount) {
                   val pointerId = event.getPointerId(i)
                   val currentFigure = pointersFigures[pointerId]
                   val currentPoint = Point(event.getX(i).toInt(), event.getY(i).toInt())

                   currentFigure?.let {
                       val toAdd = !currentFigure.isDrawable()

                       (it as FigureLine).end = currentPoint

                       if (toAdd) {
                           figures += it
                       }
                   }
                }
            }
        }
    }
}
