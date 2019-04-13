package com.sparkle.onviewtouchhold

import android.view.View

fun View.onTouchHold(action: (timeElapsed: Long) -> Unit,
                     startDelay: Long = DEFAULT_STARTING_DELAY,
                     pollingRate: Long = DEFAULT_POLLING_RATE,
                     excludePadding: Boolean = false) {
    this.setOnTouchListener(OnViewTouchHoldListener(object : OnTouchHoldCallback {
        override fun onTouchHold(timeElapsed: Long) {
            action.invoke(timeElapsed)
        }
    }, TouchHoldConfig(startDelay, pollingRate, excludePadding)))
}