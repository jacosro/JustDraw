package com.jacosro.justdraw.drawers

import android.content.Context
import android.graphics.Paint
import com.jacosro.justdraw.R
import com.jacosro.justdraw.util.Paints

class Drawers {

    enum class Type(val iconId: Int) {
        LINE(R.drawable.ic_line),
        FREE(R.drawable.ic_scribble),
        ERASER(R.drawable.ic_eraser)
    }

    companion object {

        fun newDrawer(context: Context, type: Type, paint: Paint): Drawer {
            return when (type) {
                Type.LINE -> DrawerLine(context, paint)
                Type.FREE -> DrawerFree(context, paint)
                Type.ERASER -> {
                    val erasePaint = Paints.getErasePaint()
                    erasePaint.strokeWidth = paint.strokeWidth
                    DrawerFree(context, erasePaint)
                }
            }
        }
    }
}