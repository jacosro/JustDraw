package com.jacosro.justdraw.figures.shapes

import android.graphics.*

class FigureEllipse(rect: Rect, paint: Paint): FigureRectangle(rect, paint) {

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
        canvas.drawOval(
                RectF(rect),
                paint
        )
    }
}