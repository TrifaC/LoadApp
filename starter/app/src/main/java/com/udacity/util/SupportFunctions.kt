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
 * Function to calculate the Rectangle to draw the arc.(Set the margin according to the button width and height)
 *
 * @param buttonRectF is the button rectangle
 * @return the rectangle to draw the arc
 * */
fun calculateArcRectangle(buttonRectF: RectF): RectF {
    val circleMargin: Float = 0.1f*(buttonRectF.bottom)
    val leftF: Float = buttonRectF.right - buttonRectF.bottom
    val rightF: Float = buttonRectF.right - circleMargin
    val topF: Float = circleMargin
    val bottomF: Float = buttonRectF.bottom - circleMargin
    return RectF(leftF,topF,rightF,bottomF)
}


