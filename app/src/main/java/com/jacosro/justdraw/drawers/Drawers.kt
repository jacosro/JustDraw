package com.jacosro.justdraw.drawers

import android.content.Context
import android.graphics.Paint
import com.jacosro.justdraw.R
import com.jacosro.justdraw.drawers.shapes.DrawerEllipse
import com.jacosro.justdraw.drawers.shapes.DrawerRectangle
import com.jacosro.justdraw.util.Paints

class Drawers {

    enum class Type(val iconId: Int) {
        LINE(R.drawable.ic_line),
        FREE(R.drawable.ic_scribble),
        ERASER(R.drawable.ic_eraser),
        RECTANGLE(R.drawable.ic_rectangles),
        ELLIPSE(R.drawable.ic_plain_circle)
    }

    companion object {

        fun newDrawer(context: Context, type: Type, paint: Paint): Drawer {
            return when (type) {
                Type.LINE -> DrawerLine(paint)
                Type.FREE -> DrawerFree(paint)
                Type.ERASER -> {
                    val erasePaint = Paints.erasePaint
                    erasePaint.strokeWidth = paint.strokeWidth
                    DrawerFree(erasePaint)
                }
                Type.RECTANGLE -> DrawerRectangle(context, paint)
                Type.ELLIPSE -> DrawerEllipse(context, paint)
            }
        }
    }
}