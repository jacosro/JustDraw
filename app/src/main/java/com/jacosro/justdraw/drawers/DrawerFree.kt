package com.jacosro.justdraw.drawers

import android.graphics.Paint
import android.graphics.Point
import android.view.MotionEvent
import com.jacosro.justdraw.figures.Figure
import com.jacosro.justdraw.figures.FigureCurve
import com.jacosro.justdraw.figures.FigurePoint
import com.jacosro.justdraw.util.FiguresQueue

open class DrawerFree(paint: Paint) : Drawer(paint) {

    override val type: Drawers.Type = Drawers.Type.FREE

    private val pointersFigures: MutableMap<Int, Figure> = mutableMapOf()

    override fun onTouchEvent(event: MotionEvent, figures: FiguresQueue): Boolean {
        val index = event.actionIndex
        val id = event.getPointerId(index)

        when {
            event.action == MotionEvent.ACTION_DOWN ||
                    event.actionMasked == MotionEvent.ACTION_POINTER_DOWN -> {
                pointersFigures[id] = FigurePoint(Point(event.getX(index).toInt(), event.getY(index).toInt()), paint)
            }

            event.action == MotionEvent.ACTION_MOVE -> {
                for (i in 0 until event.pointerCount) {
                    val pointerId = event.getPointerId(i)
                    val currentFigure = pointersFigures[pointerId]
                    val currentPoint = Point(event.getX(i).toInt(), event.getY(i).toInt())

                    if (currentFigure is FigurePoint) {
                        val newFigure = FigureCurve(mutableListOf(currentFigure.point), paint)
                        pointersFigures[pointerId] = newFigure
                        figures += newFigure
                    } else {
                        currentFigure as FigureCurve += currentPoint
                    }
                }
            }

            event.action == MotionEvent.ACTION_UP ||
                    event.actionMasked == MotionEvent.ACTION_POINTER_UP -> {
                val currentPoint = Point(event.getX(index).toInt(), event.getY(index).toInt())

                val currentFigure = pointersFigures[id]

                if (currentFigure is FigurePoint &&
                        currentFigure.isPoint(currentPoint)) {
                    figures += currentFigure
                }
            }
        }

        return true
    }

}