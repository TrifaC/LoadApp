package com.udacity.util

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface

object CustomButtonPaint {

    /** The paint to draw the background of the button. */
    val buttonBackgroundPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = Color.BLUE
    }

    /** The paint to draw the background(Loading Process) of the button. */
    val buttonBackgroundCoverPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = Color.GREEN
    }

    /** The paint to draw the text of button. */
    val defaultTextPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 55.0f
        textAlign = Paint.Align.CENTER
        strokeWidth = 3f
        typeface = Typeface.DEFAULT_BOLD
        color = Color.WHITE
    }

    /** The paint to draw the arc of button. */
    val defaultArcPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        strokeWidth = 3f
        color = Color.YELLOW
    }
}
