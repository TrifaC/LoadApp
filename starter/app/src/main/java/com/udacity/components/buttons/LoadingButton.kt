package com.udacity.components.buttons

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import kotlin.properties.Delegates

class LoadingButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    companion object {
        private const val LOG_TAG = "Loading Button"
    }

    private var widthSize = 0
    private var heightSize = 0
    private var pointPosition: PointF = PointF(0.0f, 0.0f)
    private var paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        textAlign = Paint.Align.CENTER
        textSize = 55.0f
        typeface = Typeface.DEFAULT_BOLD
    }

    /** The animator is used for appearance changing. */
    private val valueAnimator = ValueAnimator()

    /** The button state with observable attribute */
    private var buttonState: ButtonState by Delegates.observable(ButtonState.TO_CLICK) { p, old, new ->
        /** The function to be executed when state changing. */
    }


//------------------------------------- Override Functions -----------------------------------------


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        Log.d(LOG_TAG, "onSizeChanged: run.")
        super.onSizeChanged(w, h, oldw, oldh)
    }

    override fun onDraw(canvas: Canvas?) {
        Log.d(LOG_TAG, "onDraw: run.")
        super.onDraw(canvas)
        paint.color = if ( buttonState == ButtonState.TO_CLICK ) Color.BLUE else Color.RED
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        Log.d(LOG_TAG, "onMeasure: run.")
        Log.d(LOG_TAG, "The width is ${MeasureSpec.getSize(widthMeasureSpec)}")
        Log.d(LOG_TAG, "The height is ${MeasureSpec.getSize(heightMeasureSpec)}")

        val minw: Int = paddingLeft + paddingRight + suggestedMinimumWidth
        val w: Int = resolveSizeAndState(minw, widthMeasureSpec, 1)
        val h: Int = resolveSizeAndState(
            MeasureSpec.getSize(w),
            heightMeasureSpec,
            0
        )
        widthSize = w
        heightSize = h
        setMeasuredDimension(w, h)
    }
}