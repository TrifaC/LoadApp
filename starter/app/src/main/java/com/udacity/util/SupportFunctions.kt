package com.udacity.util

import android.graphics.Paint
import android.graphics.RectF

/**
 * Function to calculate the baseline position, which can make the text draw on the center.
 *
 * @param rectF The rectangle area to draw the text.
 * @param fontMetrics The text area metrics
 * @return is the Y position of baseline.
 * */
fun calculateBaseline(rectF: RectF, fontMetrics: Paint.FontMetrics): Float {
    val distance: Float = (fontMetrics.bottom - fontMetrics.top)/2 - fontMetrics.bottom
    return rectF.centerY()+distance
}

/**
 * To calculate the radius of the arc.
 *
 * TODO: Calculate the radius with padding.
 * */
fun calculateRadius(height: Float): Float {
    return (height * 0.8f)/2
}

