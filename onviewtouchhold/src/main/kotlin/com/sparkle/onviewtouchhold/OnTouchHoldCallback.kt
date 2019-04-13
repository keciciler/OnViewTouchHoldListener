package com.sparkle.onviewtouchhold

interface OnTouchHoldCallback {
    /**
     * Called when a view has been touch down and held.
     *
     * @return Time elapsed since touched down,called every polling rate.
     */
    fun onTouchHold(timeElapsed: Long)
}