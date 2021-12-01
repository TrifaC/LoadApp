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
<<<<<<< HEAD
 * To calculate the radius of the arc.
 *
 * TODO: Calculate the radius with padding.
=======
 * Function to calculate the radius of arc.
>>>>>>> 90c1178a6a92fa036d1d1cd6c4a91bb767f64365
 * */
fun calculateRadius(height: Float): Float {
    return (height * 0.8f)/2
}

/**
 * Function to calculate the Rectangle to draw the arc.
 * */
fun calculateArcRectangle() {

}

