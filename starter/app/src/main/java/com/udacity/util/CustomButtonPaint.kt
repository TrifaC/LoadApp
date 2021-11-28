package com.udacity.util

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface

object CustomButtonPaint {
    val defaultPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        textAlign = Paint.Align.CENTER
        textSize = 55.0f
        typeface = Typeface.DEFAULT_BOLD
        strokeWidth = 3f
    }
}