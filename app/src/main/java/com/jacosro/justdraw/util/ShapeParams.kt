package com.jacosro.justdraw.util

import android.graphics.Point

class ShapeParams(
        private val center: Point,
        private val size: Int? = null,
        private val verticalSize: Int? = null,
        private val horizontalSize: Int? = null) {

    companion object {
        fun forRectangle(center: Point, verticalSize: Int, horizontalSize: Int) = ShapeParams(center, verticalSize=verticalSize, horizontalSize=horizontalSize)

        fun forCircle(center: Point, size: Int) = ShapeParams(center, size=size)

    }
}