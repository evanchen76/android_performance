package evan.chen.toturial.memoryleaksample.solve

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import androidx.appcompat.app.AppCompatActivity
import evan.chen.toturial.memoryleaksample.R
import evan.chen.toturial.memoryleaksample.databinding.ActivityThreadBinding
import java.lang.ref.WeakReference


class ThreadActivity : AppCompatActivity() {
    private val binding by lazy { ActivityThreadBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    internal class MyHandler(activity: Activity) : Handler() {
        var activityReference: WeakReference<Activity>

        init {
            activityReference = WeakReference(activity)
        }

        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            val activity = activityReference.get()
            if (activity != null) {

            }
        }

    }

}


