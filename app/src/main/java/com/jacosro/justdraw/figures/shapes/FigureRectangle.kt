package com.jacosro.justdraw.figures.shapes

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Point
import android.graphics.Rect

open class FigureRectangle(protected val rect: Rect, paint: Paint): FigureShape(paint) {

    companion object {
        const val defaultVerticalSize = 100
        const val defaultHorizontalSize = 100
    }

    val centerX: Int
    get() = rect.centerX()

    val centerY: Int
    get() = rect.centerY()

    val width: Int
    get() = rect.width()

    constructor(center: Point, verticalSize: Int, horizontalSize: Int, paint: Paint)
            : this(
            Rect(
                    center.x - horizontalSize,
                    center.y - verticalSize,
                    center.x + horizontalSize,
                    center.y + verticalSize
            ),
            paint
    )

    constructor(left: Int, top: Int, right: Int, bottom: Int, paint: Paint)
            : this(Rect(left, top, right, bottom), paint)

    override fun draw(canvas: Canvas) {
        canvas.drawRect(
                rect,
                paint
        )
    }

    fun contains(x: Int, y: Int) = rect.contains(x, y)

    fun contains(point: Point) = contains(point.x, point.y)

    fun set(left: Int, top: Int, right: Int, bottom: Int) = rect.set(left, top, right, bottom)

    fun setNewCenter(point: Point) {
        val currentHorizontalSize = Math.abs(rect.left - rect.right) / 2
        val currentVerticalSize = Math.abs(rect.top - rect.bottom) / 2

        this.set(
                point.x - currentHorizontalSize,
                point.y - currentVerticalSize,
                point.x + currentHorizontalSize,
                point.y + currentVerticalSize
        )
    }
}