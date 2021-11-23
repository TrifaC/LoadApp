package com.udacity.components.buttons

/**
 * The sealed class contains three different states of the button.
 *
 * */
sealed class ButtonState {
    object Clicked : ButtonState()
    object Loading : ButtonState()
    object Completed : ButtonState()
}