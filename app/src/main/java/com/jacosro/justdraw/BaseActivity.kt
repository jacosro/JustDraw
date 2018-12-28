package com.jacosro.justdraw

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import com.jacosro.justdraw.persistence.DrawingDB

abstract class BaseActivity : AppCompatActivity() {

    protected var drawingDB: DrawingDB? = null
    get() {
        DrawingDB.init(applicationContext)
        return DrawingDB.getInstance()
    }

    protected fun startActivity(klass: Class<*>) {
        this.startActivity(klass, 0)
    }

    protected fun startActivityAndFinish(klass: Class<*>) {
        this.startActivity(klass)
        finish()
    }

    protected fun startActivity(klass: Class<*>, flag: Int, vararg moreFlags: Int) {
        val intent = Intent(this, klass)

        var flags = flag

        for (f in moreFlags) {
            flags = flags or f
        }

        intent.flags = flags

        super.startActivity(intent)
    }
}
