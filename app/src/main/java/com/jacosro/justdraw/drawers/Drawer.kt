package com.jacosro.justdraw.drawers

import android.graphics.Paint
import android.view.MotionEvent
import com.jacosro.justdraw.util.FiguresQueue

abstract class Drawer(var paint: Paint) {

    abstract val type: Drawers.Type

    abstract fun onTouchEvent(event: MotionEvent, figures: FiguresQueue)
}