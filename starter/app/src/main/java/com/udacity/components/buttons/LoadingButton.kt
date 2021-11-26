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

    private lateinit var extraCanvas: Canvas
    private lateinit var extraBitmap: Bitmap
    private var paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        textAlign = Paint.Align.CENTER
        textSize = 55.0f
        typeface = Typeface.DEFAULT_BOLD
        strokeWidth = 3f
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
        //Create a bitmap above Canvas, draw the on the canvas.
        extraBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        extraCanvas = Canvas(extraBitmap)
        extraCanvas.drawColor(Color.BLUE)
    }

    override fun onDraw(canvas: Canvas?) {
        Log.d(LOG_TAG, "onDraw: run.")
        super.onDraw(canvas)
        canvas?.drawBitmap(extraBitmap, 0f, 0f, null)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        Log.d(LOG_TAG, "onMeasure: run.")
        Log.d(LOG_TAG, "The width is ${MeasureSpec.getSize(widthMeasureSpec)}")
        Log.d(LOG_TAG, "The height is ${MeasureSpec.getSize(heightMeasureSpec)}")
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
//        val minw: Int = paddingLeft + paddingRight + suggestedMinimumWidth
//        val w: Int = resolveSizeAndState(minw, widthMeasureSpec, 1)
//        val h: Int = resolveSizeAndState(
//            MeasureSpec.getSize(w),
//            heightMeasureSpec,
//            0
//        )
//        widthSize = w
//        heightSize = h
//        setMeasuredDimension(w, h)
    }


//------------------------------------- Button State Change Function -------------------------------


    fun changeState(mButtonState: ButtonState) {
        buttonState = mButtonState
        Log.d(LOG_TAG, "The button state is $buttonState")
    }


}