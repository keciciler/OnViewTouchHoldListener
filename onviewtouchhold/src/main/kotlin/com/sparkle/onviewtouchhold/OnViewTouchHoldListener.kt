package com.sparkle.onviewtouchhold

import android.annotation.SuppressLint
import android.graphics.Rect
import android.os.Handler
import android.view.MotionEvent
import android.view.View

const val INVALID_FINGER_INDEX = -1

class OnViewTouchHoldListener @JvmOverloads constructor(
        private val onTouchHoldCallback: OnTouchHoldCallback,
        private val config: TouchHoldConfig = TouchHoldConfig())
    : View.OnTouchListener {

    private val elapsedTimeStopWatch by lazy(::TouchHeldStopwatch)

    private var callbackHandler: Handler = Handler()

    private val onTouchHoldCallbackRunnable: Runnable by lazy {
        Runnable {
            onTouchHoldCallback.onTouchHold(elapsedTimeStopWatch.elapsed())
            callbackHandler.postDelayed(onTouchHoldCallbackRunnable, config.pollingRate)
        }
    }

    private var firstFingerIndex = INVALID_FINGER_INDEX

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
        // to dispatch click / long click event,
        // we must pass the event to it's default callback View.onTouchEvent
        val defaultResult = view.onTouchEvent(motionEvent)

        //We care only about first finger, this definitely needs improvements open for pull request.
        if (firstFingerIndex != INVALID_FINGER_INDEX &&
                firstFingerIndex != motionEvent.getPointerId(motionEvent.actionIndex)) {
            return defaultResult
        }

        when (motionEvent.action) {

            MotionEvent.ACTION_DOWN -> {
                firstFingerIndex = motionEvent.getPointerId(motionEvent.actionIndex)
                elapsedTimeStopWatch.reset()
                elapsedTimeStopWatch.start()
                callbackHandler.postDelayed(onTouchHoldCallbackRunnable, config.startDelay)
            }

            MotionEvent.ACTION_MOVE -> {
                val viewRect = Rect()
                view.getGlobalVisibleRect(viewRect)
                if (config.excludePadding) {
                    viewRect.top += view.paddingTop
                    viewRect.bottom -= view.paddingBottom
                    viewRect.left += view.paddingLeft
                    viewRect.right -= view.paddingRight
                }
                if (!viewRect.contains(motionEvent.rawX.toInt(), motionEvent.rawY.toInt())) {
                    touchDismissed()
                }
            }
            else -> {
                touchDismissed()
            }
        }
        return defaultResult
    }

    private fun touchDismissed() {
        firstFingerIndex = INVALID_FINGER_INDEX
        callbackHandler.removeCallbacks(onTouchHoldCallbackRunnable)
    }

}
