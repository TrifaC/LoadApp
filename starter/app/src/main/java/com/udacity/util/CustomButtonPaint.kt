package com.udacity.util

import android.content.Context
import android.graphics.Paint
import android.graphics.Typeface
import androidx.core.content.ContextCompat
import com.udacity.R

/**
 * The class contains different paint to be used in drawing button on the main page.
 * */
class CustomButtonPaint(context: Context) {

    /** The paint to draw the background of the button. */
    val buttonBackgroundPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = ContextCompat.getColor(context, R.color.colorPrimary)
    }

    /** The paint to draw the background(Loading Process) of the button. */
    val buttonBackgroundCoverPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = ContextCompat.getColor(context, R.color.colorPrimaryDark)
    }

    /** The paint to draw the text of button. */
    val defaultTextPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 55.0f
        textAlign = Paint.Align.CENTER
        strokeWidth = 3f
        typeface = Typeface.DEFAULT_BOLD
        color = ContextCompat.getColor(context, R.color.white)
    }

    /** The paint to draw the arc of button. */
    val defaultArcPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        strokeWidth = 3f
        color = ContextCompat.getColor(context, R.color.colorAccent)
    }
}
