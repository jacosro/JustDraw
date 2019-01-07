package com.jacosro.justdraw.util

import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode

import java.util.Random

object Paints {

    const val DEFAULT_STROKE_WIDTH = 30f
    const val DEFAULT_COLOR = Color.BLACK

    val randomColorPaint: Paint
        get() {
            val random = Random()
            val paint = basePaint()
            paint.setARGB(random.nextInt(), random.nextInt(), random.nextInt(), random.nextInt())

            return paint
        }

    val erasePaint: Paint
        get() = getPaintWithColor(Color.WHITE)


    val default: Paint
        get() = getPaintWithColor(DEFAULT_COLOR)

    private fun basePaint(): Paint {
        val paint = Paint()
        paint.isAntiAlias = true
        paint.strokeWidth = DEFAULT_STROKE_WIDTH
        paint.style = Paint.Style.STROKE
        paint.strokeJoin = Paint.Join.ROUND
        paint.strokeCap = Paint.Cap.ROUND

        return paint
    }

    fun getPaintWithColor(color: Int): Paint {
        val paint = basePaint()
        paint.color = color

        return paint
    }
}