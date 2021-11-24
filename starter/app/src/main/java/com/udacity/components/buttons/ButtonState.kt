package com.udacity.components.buttons

import com.udacity.R

/**
 * Change the sealed class to enum class.
 * */
enum class ButtonState(val label: Int) {
    TO_CLICK(R.string.custom_button_label_to_click),
    LOADING(R.string.custom_button_label_loading),
    COMPLETE(R.string.custom_button_label_complete);
}