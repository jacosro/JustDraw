package com.jacosro.justdraw.drawers.shapes

import android.content.Context
import android.graphics.Paint
import android.graphics.Point
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import com.jacosro.justdraw.drawers.Drawers
import com.jacosro.justdraw.figures.shapes.FigureRectangle
import com.jacosro.justdraw.util.OnFiguresClearedListener

class DrawerRectangle(val context: Context, paint: Paint): DrawerShape(paint), OnFiguresClearedListener {
    override val type: Drawers.Type = Drawers.Type.RECTANGLE

    override val scaleGestureDetector: ScaleGestureDetector = ScaleGestureDetector(context, object: ScaleGestureDetector.OnScaleGestureListener {

        private var rectangle: FigureRectangle? = null

        override fun onScaleBegin(detector: ScaleGestureDetector?): Boolean {
            for (rect in shapes) {
                val x = detector!!.focusX.toInt()
                val y = detector.focusY.toInt()

                if ((rect as FigureRectangle).contains(x, y)) {
                    this.rectangle = rect

                    return true
                }
            }

            return false
        }

        override fun onScaleEnd(detector: ScaleGestureDetector?) {
        }

        override fun onScale(detector: ScaleGestureDetector?): Boolean {
            val centerX = this.rectangle!!.centerX
            val centerY = this.rectangle!!.centerY

            val side = (this.rectangle!!.width * detector!!.scaleFactor).toInt()
            val distanceFromCenter = side / 2

            val left = centerX - distanceFromCenter
            val top = centerY - distanceFromCenter
            val right = centerX + distanceFromCenter
            val bottom = centerY + distanceFromCenter

            this.rectangle!!.set(
                    left,
                    top,
                    right,
                    bottom
            )

            return true
        }
    })

    override val gestureDetector: GestureDetector = GestureDetector(context, object: GestureDetector.SimpleOnGestureListener() {

        private var touchedRectangle: FigureRectangle? = null

        private var onScrollingCreatingRectangle: FigureRectangle? = null

        override fun onDown(e: MotionEvent?): Boolean {
            touchedRectangle = null
            onScrollingCreatingRectangle = null

            shapes.forEach {
                if ((it as FigureRectangle).contains(e!!.x.toInt(), e.y.toInt())) {
                    touchedRectangle = it
                    return true
                }
            }

            return true
        }

        override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
            if (touchedRectangle != null) { // We are moving a rectangle
                touchedRectangle!!.setNewCenter(Point(e2!!.x.toInt(), e2.y.toInt()))
            } else { // We are creating a rectangle
                if (onScrollingCreatingRectangle == null) {
                    onScrollingCreatingRectangle = FigureRectangle(0, 0, 0, 0, paint)
                    shapes.add(onScrollingCreatingRectangle!!)
                    newFigure = onScrollingCreatingRectangle!!
                }

                val e1x = e1!!.x.toInt()
                val e1y = e1.y.toInt()
                val e2x = e2!!.x.toInt()
                val e2y = e2.y.toInt()

                val left = if (e1x < e2x) e1x else e2x // left always at the left
                val top = if (e1y < e2y) e1y else e2y // top always at the top
                val right = if (left == e1x) e2x else e1x // same with right
                val bottom = if (top == e1y) e2y else e1y // and bottom

                onScrollingCreatingRectangle!!.set(
                        left,
                        top,
                        right,
                        bottom
                )
            }

            return true
        }

        override fun onLongPress(e: MotionEvent?) {
            if (touchedRectangle != null)
                return

            val currentPoint = Point(e!!.x.toInt(), e.y.toInt())

            val rectangle = FigureRectangle(
                    currentPoint,
                    FigureRectangle.defaultVerticalSize,
                    FigureRectangle.defaultHorizontalSize,
                    paint
            )

            shapes += rectangle
            newFigure = rectangle
        }

        override fun onDoubleTap(e: MotionEvent?): Boolean {
            if (touchedRectangle == null)
                return true

            shapes.remove(touchedRectangle!!)
            removeFigure = touchedRectangle!!

            return true
        }

    })
}
