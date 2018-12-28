package com.jacosro.justdraw.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.graphics.Bitmap
import android.graphics.Canvas
import java.util.Date

@Entity
data class Drawing(
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        val name: String,
        val date: Date,
        val bitmap: Bitmap)