package com.sparkle.onviewtouchhold

import java.util.concurrent.TimeUnit

internal class TouchHeldStopwatch {

    private val ticker = object : Ticker() {
        override fun read(): Long {
            return System.nanoTime()
        }
    }

    var isRunning: Boolean = false
        private set
    private var elapsedNanos: Long = 0
    private var startTick: Long = 0

    fun start(): TouchHeldStopwatch {
        checkState(!this.isRunning, "This stopwatch is already running.")
        this.isRunning = true
        this.startTick = this.ticker.read()
        return this
    }

    fun stop(): TouchHeldStopwatch {
        val tick = this.ticker.read()
        checkState(this.isRunning, "This stopwatch is already stopped.")
        this.isRunning = false
        this.elapsedNanos += tick - this.startTick
        return this
    }

    fun reset(): TouchHeldStopwatch {
        this.elapsedNanos = 0L
        this.isRunning = false
        return this
    }

    private fun elapsedNanos(): Long {
        return if (this.isRunning) this.ticker.read() - this.startTick + this.elapsedNanos else this.elapsedNanos
    }

    fun elapsed(desiredUnit: TimeUnit = TimeUnit.MILLISECONDS): Long {
        return desiredUnit.convert(this.elapsedNanos(), TimeUnit.NANOSECONDS)
    }

    private fun checkState(expression: Boolean, errorMessage: Any?) {
        if (!expression) {
            throw IllegalStateException(errorMessage.toString())
        }
    }

    abstract inner class Ticker protected constructor() {
        abstract fun read(): Long
    }

}