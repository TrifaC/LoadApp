package com.udacity.components.buttons

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.udacity.util.CustomButtonPaint
import com.udacity.util.calculateBaseline

class LoadingButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    companion object {
        private const val LOG_TAG = "Loading Button"
    }

    /** Button Drawing Rectangle, Button Cover Drawing Rectangle, Circle Drawing Rectangle. */
    private var rectButtonF = RectF()
    private var rectButtonCoverF = RectF()
    private var rectArcF = RectF()

    /** Text Paint, Background Paint, Act Paint. */
    private var textP = CustomButtonPaint.defaultTextPaint
    private var backgroundP = CustomButtonPaint.buttonBackgroundPaint
    private var backgroundCoverP = CustomButtonPaint.buttonBackgroundCoverPaint
    private var arcP = CustomButtonPaint.defaultArcPaint

    /** The coordinate of button and text. */
    private var buttonCenterX: Float = 0f
    private var buttonCenterY: Float = 0f
    private var buttonTextBaseline: Float = 0f

    /** The animator is used for appearance changing. */
    private var valueAnimator = ValueAnimator()
    private var sweepDegree: Float = 0f
    private var processPercent: Float = 0f

    /** The state of the button */
    private var buttonState: ButtonState = ButtonState.TO_CLICK


//------------------------------------- Initialization ---------------------------------------------

    /**
     * Initialization; Make button clickable, init the animator.
     * */
    init {
        isClickable = true
        valueAnimatorInit()
    }

    /**
     * The method is used to initialize the animator; Range Setting, Duration Setting, Update the sweep
     * degree of the circular bar, update the progress bar percent. Use invalidate to trigger the onDraw()
     * function.
     *
     * @see invalidate
     * */
    private fun valueAnimatorInit() {
        valueAnimator = ValueAnimator.ofFloat(0f, 360f)
        valueAnimator.duration = 2900
        valueAnimator.addUpdateListener { animation ->
            sweepDegree = animation.animatedValue as Float
            processPercent = (sweepDegree/360f)
            rectButtonCoverF.right = width*processPercent
            invalidate()
        }
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

    /**
     * The method is used to calculate the coordination of different component when size changing.
     *
     * @param w is the width of component
     * @param h is the height of component
     * @param oldh is the old version height of component
     * @param oldw is the old version width of component
     * */
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        Log.d(LOG_TAG, "onSizeChanged: run.")
        super.onSizeChanged(w, h, oldw, oldh)

        // The Rectangle area to draw the button.
        rectButtonF = RectF(0f, 0f, w.toFloat(), h.toFloat())
        // The Rectangle area to draw the button. (During the loading progress)
        rectButtonCoverF = RectF(0f, 0f,0f, h.toFloat())
        // The Rectangle area to draw the circular bar arc.
        rectArcF = RectF((w - h).toFloat(), 0f, w.toFloat(), h.toFloat())

        // The center of button (X Coordination)
        buttonCenterX = rectButtonF.centerX()
        // The center of button (Y Coordination)
        buttonCenterY = rectButtonF.centerY()
        // The baseline of the button text (Y Coordination)
        buttonTextBaseline = calculateBaseline(rectButtonF, textP.fontMetrics)
    }

    /**
     * The method to update the UI of the button.
     * */
    override fun onDraw(canvas: Canvas?) {
        Log.d(LOG_TAG, "onDraw: run.")
        super.onDraw(canvas)

        // Draw the background rectangle
        canvas?.drawRect(rectButtonF, backgroundP)
        // Draw the background cover rectangle (Loading Progress Cover)
        canvas?.drawRect(rectButtonCoverF, backgroundCoverP)
        // Draw the text
        canvas?.drawText(buttonState.name, buttonCenterX, buttonTextBaseline, textP)
        // Draw the circular progress bar (Arc)
        canvas?.drawArc(rectArcF, 0f, sweepDegree, true, arcP)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
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
        when (mButtonState) {
            ButtonState.TO_CLICK -> {
                rectButtonCoverF?.right = 0f
            }
            ButtonState.LOADING -> {
                valueAnimator.start()
            }
        }
        processPercent = 0f
        sweepDegree = 0f
        invalidate()
    }


}