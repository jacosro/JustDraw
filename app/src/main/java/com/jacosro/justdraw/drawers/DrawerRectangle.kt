package com.jacosro.justdraw.drawers

import android.content.Context
import android.graphics.Paint
import android.view.MotionEvent
import com.jacosro.justdraw.util.FiguresQueue

class DrawerRectangle(context: Context, paint: Paint) : Drawer(paint) {

    override val type: Drawers.Type = Drawers.Type.RECTANGLE

    override fun onTouchEvent(event: MotionEvent, figures: FiguresQueue) {

    }
}