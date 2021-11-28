package com.udacity.components.buttons

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.udacity.util.CustomButtonPaint
import kotlin.properties.Delegates

class LoadingButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    companion object {
        private const val LOG_TAG = "Loading Button"
    }

    private var paint = CustomButtonPaint.defaultPaint
    private var background = Color.BLUE
    private var textColor = Color.WHITE

    /** The animator is used for appearance changing. */
    private val valueAnimator = ValueAnimator()

    /**
     * The button state with observable attribute, the invalidate will execute and call the onDraw function.
     * */
    private var buttonState: ButtonState by Delegates.observable(ButtonState.TO_CLICK) { property, oldValue, newValue ->
        background = when (newValue) {
            ButtonState.TO_CLICK -> Color.BLUE
            ButtonState.LOADING -> Color.GREEN
        }
        invalidate()
    }


//------------------------------------- Initialization ---------------------------------------------


    init {
        isClickable = true
    }


//------------------------------------- Override Functions -----------------------------------------

    /**
     * Add the method to rewrite the process when the button has been clicked.
     *
     * @return true for running successfully.
     * */
    override fun performClick(): Boolean {
        super.performClick()
        return true
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        Log.d(LOG_TAG, "onSizeChanged: run.")
        super.onSizeChanged(w, h, oldw, oldh)
    }

    /**
     * The method to update the UI of the button.
     * */
    override fun onDraw(canvas: Canvas?) {
        Log.d(LOG_TAG, "onDraw: run.")
        super.onDraw(canvas)

        paint.color = background
        canvas?.drawRect(0f,0f, width.toFloat(),height.toFloat(),paint)
        paint.color = textColor
        canvas?.drawText(buttonState.name, (width.toFloat()/2), (height.toFloat()/2), paint)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        Log.d(LOG_TAG, "onMeasure: run.")
        Log.d(LOG_TAG, "The width is ${MeasureSpec.getSize(widthMeasureSpec)}")
        Log.d(LOG_TAG, "The height is ${MeasureSpec.getSize(heightMeasureSpec)}")
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }


//------------------------------------- Button State Change Function -------------------------------


    /**
     * The function will be called to change the state of button.
     *
     * @param mButtonState is the state of button which passed from view model.
     * */
    fun changeState(mButtonState: ButtonState) {
        buttonState = mButtonState
        // Log.d(LOG_TAG, "The button state is $buttonState")
    }


}