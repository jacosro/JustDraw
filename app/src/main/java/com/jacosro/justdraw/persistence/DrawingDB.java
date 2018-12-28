package com.jacosro.justdraw.persistence;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.support.annotation.NonNull;

import com.jacosro.justdraw.entities.Drawing;

@Database(entities = {Drawing.class}, version = 1)
@TypeConverters({DrawingDAO.Converters.class})
public abstract class DrawingDB extends RoomDatabase {

    private static DrawingDB ourInstance = null;

    @NonNull
    public static DrawingDB getInstance() {
        if (ourInstance == null)
            throw new IllegalStateException("DrawingDB not initialized!");

        return ourInstance;
    }

    public static void init(Context context) {
        if (ourInstance != null)
            return;

        ourInstance = Room.databaseBuilder(context, DrawingDB.class, "drawings").build();
    }

    private DrawingDB() {
    }

    public abstract DrawingDAO drawingDAO();
}
