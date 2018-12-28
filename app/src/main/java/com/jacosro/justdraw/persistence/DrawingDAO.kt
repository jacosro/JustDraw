package com.jacosro.justdraw.persistence

import android.arch.persistence.room.*
import android.graphics.Bitmap
import android.graphics.Canvas
import com.jacosro.justdraw.entities.Drawing
import io.reactivex.Flowable
import java.util.*


@Dao
interface DrawingDAO {

    @Query("SELECT * FROM DRAWING")
    fun getAll(): Flowable<List<Drawing>>

    @Insert
    fun insert(drawing: Drawing)

    @Delete
    fun remove(drawing: Drawing)

    class Converters {
        @TypeConverter
        fun fromTimestamp(value: Long?): Date? {
            return if (value == null) null else Date(value)
        }

        @TypeConverter
        fun dateToTimestamp(date: Date?): Long? {
            return date?.time
        }

        @TypeConverter
        fun fromBytes(value: ByteArray?): Bitmap? {
            return null
        }
    }
}