package com.jacosro.justdraw.drawers.shapes

import android.content.Context
import android.graphics.Paint
import android.graphics.Point
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import com.jacosro.justdraw.drawers.Drawers
import com.jacosro.justdraw.figures.shapes.FigureEllipse
import com.jacosro.justdraw.figures.shapes.FigureRectangle
import com.jacosro.justdraw.util.OnFiguresClearedListener

class DrawerEllipse(val context: Context, paint: Paint): DrawerShape(paint), OnFiguresClearedListener {
    override val type: Drawers.Type = Drawers.Type.ELLIPSE

    override val scaleGestureDetector: ScaleGestureDetector = ScaleGestureDetector(context, object: ScaleGestureDetector.OnScaleGestureListener {

        private var ellipse: FigureEllipse? = null

        override fun onScaleBegin(detector: ScaleGestureDetector?): Boolean {
            for (ellipse in shapes) {
                val x = detector!!.focusX.toInt()
                val y = detector.focusY.toInt()

                if ((ellipse as FigureEllipse).contains(x, y)) {
                    this.ellipse = ellipse

                    return true
                }
            }

            return false
        }

        override fun onScaleEnd(detector: ScaleGestureDetector?) {
        }

        override fun onScale(detector: ScaleGestureDetector?): Boolean {
            val centerX = this.ellipse!!.centerX
            val centerY = this.ellipse!!.centerY

            val side = (this.ellipse!!.width * detector!!.scaleFactor).toInt()
            val distanceFromCenter = side / 2

            val left = centerX - distanceFromCenter
            val top = centerY - distanceFromCenter
            val right = centerX + distanceFromCenter
            val bottom = centerY + distanceFromCenter

            this.ellipse!!.set(
                    left,
                    top,
                    right,
                    bottom
            )

            return true
        }
    })

    override val gestureDetector: GestureDetector = GestureDetector(context, object: GestureDetector.SimpleOnGestureListener() {

        private var touchedEllipse: FigureEllipse? = null

        private var onScrollingCreatingEllipse: FigureEllipse? = null

        override fun onDown(e: MotionEvent?): Boolean {
            touchedEllipse = null
            onScrollingCreatingEllipse = null

            shapes.forEach {
                if ((it as FigureEllipse).contains(e!!.x.toInt(), e.y.toInt())) {
                    touchedEllipse = it
                    return true
                }
            }

            return true
        }

        override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
            if (touchedEllipse != null) { // We are moving a ellipse
                touchedEllipse!!.setNewCenter(Point(e2!!.x.toInt(), e2.y.toInt()))
            } else { // We are creating a ellipse
                if (onScrollingCreatingEllipse == null) {
                    onScrollingCreatingEllipse = FigureEllipse(0, 0, 0, 0, paint)
                    shapes.add(onScrollingCreatingEllipse!!)
                    newFigure = onScrollingCreatingEllipse!!
                }

                val e1x = e1!!.x.toInt()
                val e1y = e1.y.toInt()
                val e2x = e2!!.x.toInt()
                val e2y = e2.y.toInt()

                val left = if (e1x < e2x) e1x else e2x // left always at the left
                val top = if (e1y < e2y) e1y else e2y // top always at the top
                val right = if (left == e1x) e2x else e1x // same with right
                val bottom = if (top == e1y) e2y else e1y // and bottom

                onScrollingCreatingEllipse!!.set(
                        left,
                        top,
                        right,
                        bottom
                )
            }

            return true
        }

        override fun onLongPress(e: MotionEvent?) {
            if (touchedEllipse != null)
                return

            val currentPoint = Point(e!!.x.toInt(), e.y.toInt())

            val ellipse = FigureEllipse(
                    currentPoint,
                    FigureRectangle.defaultVerticalSize,
                    FigureRectangle.defaultHorizontalSize,
                    paint
            )

            shapes += ellipse
            newFigure = ellipse
        }

        override fun onDoubleTap(e: MotionEvent?): Boolean {
            if (touchedEllipse == null)
                return true

            shapes.remove(touchedEllipse!!)
            removeFigure = touchedEllipse!!

            return true
        }

    })
}
