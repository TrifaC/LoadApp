package com.udacity.util

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface

object CustomButtonPaint {
    val buttonBackgroundPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = Color.BLUE
    }

    val buttonBackgroundCoverPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = Color.GREEN
    }

    val defaultTextPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 55.0f
        textAlign = Paint.Align.CENTER
        strokeWidth = 3f
        typeface = Typeface.DEFAULT_BOLD
        color = Color.WHITE
    }

    val defaultArcPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        strokeWidth = 3f
        color = Color.YELLOW
    }
}
