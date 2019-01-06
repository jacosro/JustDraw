package com.jacosro.justdraw.util

import com.jacosro.justdraw.figures.Figure
import com.jacosro.justdraw.figures.VoidFigure
import java.util.*

class FiguresQueue @JvmOverloads constructor(collection: Collection<Figure> = emptyList()) : AbstractCollection<Figure>(), Iterable<Figure> {

    override val size: Int
        get() = queue.size

    private val queue: Deque<Figure>
    private val removedQueue: Deque<Figure>

    init {
        queue = ArrayDeque(collection)
        removedQueue = ArrayDeque()
    }

    override fun add(element: Figure): Boolean {
        queue.addLast(element)
        removedQueue.clear()
        return true
    }

    fun getLast(): Figure {
        return queue.last ?: VoidFigure()
    }

    fun getLastRemoved(): Figure {
        return removedQueue.last ?: VoidFigure()
    }

    fun recoverLastRemoved() {
        queue.addLast(removedQueue.removeLast())
    }

    override fun remove(element: Figure): Boolean {
        return queue.remove(element)
    }

    fun remove(): Figure {
        val removed = queue.removeLast()
        removed.isToRemove = false
        removedQueue.addLast(removed)
        return removed
    }

    fun isLastToRemove(): Boolean {
        return queue.isNotEmpty() && queue.last.isToRemove
    }

    fun removedSize(): Int = removedQueue.size

    override fun clear() {
        queue.clear()
        removedQueue.clear()
    }

    override fun iterator(): MutableIterator<Figure> {
        return queue.iterator()
    }
}
