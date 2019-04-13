package example.ontouchheld.altugkeciciler.com.onviewtouchheldexample

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.sparkle.onviewtouchhold.OnTouchHoldCallback
import com.sparkle.onviewtouchhold.OnViewTouchHoldListener
import com.sparkle.onviewtouchhold.onTouchHold
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val continuesValue = MutableLiveData<Int>().apply {
        value = 0
    }

    private val exponentialValue = MutableLiveData<Int>().apply {
        value = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        continuesValue.observe(this, Observer<Int> {
            continuesValueTextView.text = it.toString()
        })

        exponentialValue.observe(this, Observer<Int> {
            exponentialValueTextView.text = it.toString()
        })

        passedTimeButton.setOnTouchListener(OnViewTouchHoldListener(object : OnTouchHoldCallback {
            override fun onTouchHold(timeElapsed: Long) {
                passedTimeValueTextView.text = timeElapsed.toString()
            }
        }))

        continuesIncreaseButton.setOnTouchListener(OnViewTouchHoldListener(object : OnTouchHoldCallback {
            override fun onTouchHold(timeElapsed: Long) {
                continuesValue.value = continuesValue.value!!.plus(1)
            }
        }))

        continuesDecreaseButton.setOnTouchListener(OnViewTouchHoldListener(object : OnTouchHoldCallback {
            override fun onTouchHold(timeElapsed: Long) {
                continuesValue.value = continuesValue.value!!.minus(1)
            }
        }))

        exponentialIncreaseButton.onTouchHold({
            exponentialValue.value = exponentialValue.value!!.plus(it.toInt() / 500)
        })

        exponentialDecreaseButton.onTouchHold({
            exponentialValue.value = exponentialValue.value!!.minus(it.toInt() / 500)
        })

        passedTimeButton.setOnClickListener {
            Toast.makeText(
                    this@MainActivity,
                    "Try Me Clicked!",
                    Toast.LENGTH_SHORT
            ).show()
        }

    }
}
