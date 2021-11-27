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
    private var buttonState: ButtonState by Delegates.observable(ButtonState.TO_CLICK) { property, oldValue, newValue ->
        /** The function to be executed when state changing. */
        Log.d(LOG_TAG, "The property is ${property.name}")
        Log.d(LOG_TAG, "The old value is $oldValue")
        Log.d(LOG_TAG, "The new value is $newValue")
    }


//------------------------------------- Initialization ---------------------------------------------


    init {
        isClickable = true
    }


//------------------------------------- Override Functions -----------------------------------------

    /**
     * The method is used to execute the click function and UI renew.
     * For the super.performClick(), will run the onClickListener function on the Main Activity. Therefore,
     * we should not run the return function directly. After that the invalidate() function will call the
     * onDraw function compulsory to draw new style.
     *
     * @return true for running successfully.
     * @see invalidate
     * @see onDraw
     * */
    override fun performClick(): Boolean {
        super.performClick()
        invalidate()
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

        paint.color = when (buttonState) {
            ButtonState.TO_CLICK -> Color.BLUE
            ButtonState.LOADING -> Color.GREEN
            else -> Color.RED
        }

        canvas?.drawRect(0f,0f, width.toFloat(),height.toFloat(),paint)
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