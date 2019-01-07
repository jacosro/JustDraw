package com.jacosro.justdraw

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.jacosro.justdraw.drawers.Drawer
import com.jacosro.justdraw.drawers.Drawers

import com.jacosro.justdraw.util.FiguresQueue
import com.jacosro.justdraw.util.Paints

class DrawingView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
    ) : View(context, attrs, defStyleAttr) {

    private val figures = FiguresQueue()
    var drawer: Drawer = Drawers.newDrawer(getContext(), Drawers.Type.FREE, Paints.default) // default

    private var clearCanvas = false
    private var actionListener: OnActionListener? = null

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val res = drawer.onTouchEvent(event, figures)

        invalidate()

        return res || super.onTouchEvent(event)
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawColor(Color.WHITE)

        if (clearCanvas) {
            figures.clear()
            clearCanvas = false

            actionListener?.onAction()
            return
        }


        if (figures.isLastToRemove()) {
            figures.remove()
        }

        figures.forEach {
            it.draw(canvas)
        }

        actionListener?.onAction()
    }

    fun undoLastAction() {
        figures.getLast().markToRemove()
        invalidate()
    }

    fun canUndoLastAction(): Boolean {
        return figures.isNotEmpty()
    }

    fun clearCanvas() {
        clearCanvas = true
        invalidate()
    }

    fun canRedoLastAction(): Boolean {
        return figures.removedSize() > 0
    }

    fun setOnActionListener(actionListener: OnActionListener) {
        this.actionListener = actionListener
    }

    fun redoLastAction() {
        figures.recoverLastRemoved()
        invalidate()
    }
}

@FunctionalInterface
interface OnActionListener {
    fun onAction()
}
