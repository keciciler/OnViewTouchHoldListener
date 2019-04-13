package com.sparkle.onviewtouchhold

const val DEFAULT_STARTING_DELAY = 500L
const val DEFAULT_POLLING_RATE = 500L

data class TouchHoldConfig(
        val startDelay: Long = DEFAULT_STARTING_DELAY,
        val pollingRate: Long = DEFAULT_POLLING_RATE,
        val excludePadding: Boolean = false)