package com.jacosro.justdraw.util

import com.jacosro.justdraw.figures.Figure
import java.util.*
import kotlin.collections.ArrayList

class FiguresQueue @JvmOverloads constructor(collection: Collection<Figure> = emptyList()) : AbstractCollection<Figure>(), Iterable<Figure> {

    override val size: Int
        get() = queue.size

    private val queue: Deque<Figure>

    init {
        queue = ArrayDeque(collection)
    }

    override fun add(element: Figure): Boolean {
        queue.addLast(element)
        return true
    }

    fun getLast(): Figure? {
        return queue.last ?: null
    }

    override fun remove(element: Figure): Boolean {
        return queue.remove(element)
    }

    fun remove(): Figure {
        return queue.removeLast()
    }

    fun cleanMarkedToRemove() {
        val toRemove = ArrayList<Figure>()

        queue.forEach { if (it.isToRemove) toRemove += it }

        queue.removeAll(toRemove)
    }

    fun isLastToRemove(): Boolean {
        return queue.isNotEmpty() && queue.last.isToRemove
    }

    override fun iterator(): MutableIterator<Figure> {
        return queue.iterator()
    }
}
