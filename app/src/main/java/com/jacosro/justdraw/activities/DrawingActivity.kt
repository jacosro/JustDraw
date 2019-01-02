package com.jacosro.justdraw.activities

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.PorterDuff
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.*
import com.flask.colorpicker.ColorPickerView
import com.flask.colorpicker.OnColorSelectedListener
import com.flask.colorpicker.builder.ColorPickerClickListener
import com.flask.colorpicker.builder.ColorPickerDialogBuilder
import com.jacosro.justdraw.DrawingView
import com.jacosro.justdraw.OnActionListener
import com.jacosro.justdraw.R
import com.jacosro.justdraw.drawers.Drawers
import com.jacosro.justdraw.util.Paints

class DrawingActivity : AppCompatActivity() {

    private lateinit var drawerButton: ImageButton
    private lateinit var colorButton: ImageButton
    private lateinit var drawingView: DrawingView
    private lateinit var seekBar: SeekBar

    private lateinit var undoOptionMenu: MenuItem
    private lateinit var redoOptionMenu: MenuItem

    private var paint: Paint = Paints.getDefault() // Modified with color and size

    private val activeDrawers = listOf( // Same order than drawers string array
        Drawers.Type.ERASER,
        Drawers.Type.FREE,
        Drawers.Type.LINE
    )

    private lateinit var drawerDialog: AlertDialog
    private lateinit var colorPickerDialog: android.support.v7.app.AlertDialog

    private val defaultSeekBarProgress = Paints.DEFAULT_STROKE_WIDTH.toInt()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawing)

        findViews()
        initialize()
        setListeners()
    }

    private fun findViews() {
        drawerButton = findViewById(R.id.drawerButton)
        colorButton = findViewById(R.id.colorButton)
        drawingView = findViewById(R.id.drawingView)
        seekBar = findViewById(R.id.seekBar)
    }

    private fun initialize() {
        val listView = ListView(this)

        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                resources.getStringArray(R.array.drawers))

        listView.adapter = adapter

        listView.onItemClickListener = AdapterView.OnItemClickListener {
            _: AdapterView<*>, _: View, i: Int, _: Long ->

            val drawerType = activeDrawers[i]

            drawingView.drawer = Drawers.newDrawer(this, drawerType, paint)

            drawerButton.setImageDrawable(resources.getDrawable(drawerType.iconId))

            drawerDialog.dismiss()
        }

        drawerDialog = AlertDialog.Builder(this)
                .setMessage(R.string.select_drawer)
                .setCancelable(true)
                .setNegativeButton(getString(android.R.string.cancel)) { dialog, _ -> dialog!!.dismiss() }
                .setView(listView)
                .create()

        colorPickerDialog = ColorPickerDialogBuilder.with(this)
                .setTitle(getString(R.string.select_color))
                .initialColor(Color.WHITE)
                .wheelType(ColorPickerView.WHEEL_TYPE.CIRCLE)
                .setPositiveButton(android.R.string.ok) { _, selectedColor, _ ->
                    paint.color = selectedColor
                    drawingView.drawer.paint.color = selectedColor
                    colorButton.drawable.setColorFilter(selectedColor, PorterDuff.Mode.MULTIPLY)
                }.setNegativeButton(android.R.string.cancel) { dialog, _ ->
                    dialog.dismiss()
                }.build()

        seekBar.progress = defaultSeekBarProgress
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setListeners() {
        drawerButton.setOnClickListener {
            drawerDialog.show()
        }

        colorButton.setOnClickListener {
            colorPickerDialog.show()
        }

        seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val min = 1
                val max = 60

                val value = when {
                    progress < min -> min
                    progress > max -> max
                    else -> progress
                }

                paint.strokeWidth = value.toFloat()
                drawingView.drawer.paint.strokeWidth = value.toFloat()
                colorButton.drawable.setBounds(
                        40 - value*2/3,
                        40 - value*2/3,
                        40 + value*2/3,
                        40 + value*2/3)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}

        })

        drawingView.setOnActionListener(object: OnActionListener {
            override fun onAction() {
                updateUI()
            }

        })
    }

    override fun onStart() {
        super.onStart()

        colorButton.drawable.setBounds(
                40 - defaultSeekBarProgress,
                40 - defaultSeekBarProgress,
                40 + defaultSeekBarProgress,
                40 + defaultSeekBarProgress
        )
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_drawing, menu)

        undoOptionMenu = menu.findItem(R.id.action_undo)
        redoOptionMenu = menu.findItem(R.id.action_redo)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        when (id) {
            R.id.action_clear -> drawingView.clearCanvas()
            R.id.action_undo -> drawingView.undoLastAction()
            R.id.action_redo -> drawingView.redoLastAction()
        }

        updateUI()

        return super.onOptionsItemSelected(item)
    }

    private fun updateUI() {
        undoOptionMenu.isVisible = drawingView.canUndoLastAction()
        redoOptionMenu.isVisible = drawingView.canRedoLastAction()
    }
}
