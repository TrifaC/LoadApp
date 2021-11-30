package com.udacity.util

import android.graphics.Paint
import android.graphics.RectF

/**
 * Function to calculate the baseline position, which can make the text draw on the center.
 *
 * @param rectF
 * @param fontMetrics
 * @return Float
 * */
fun calculateBaseline(rectF: RectF, fontMetrics: Paint.FontMetrics): Float {
    val distance: Float = (fontMetrics.bottom - fontMetrics.top)/2 - fontMetrics.bottom
    return rectF.centerY()+distance
}

/**
 * Function to calculate the radius of arc.
 * */
fun calculateRadius(height: Float): Float {
    return (height * 0.8f)/2
}

/**
 * Function to calculate the Rectangle to draw the arc.
 * */
fun calculateArcRectangle() {

}

