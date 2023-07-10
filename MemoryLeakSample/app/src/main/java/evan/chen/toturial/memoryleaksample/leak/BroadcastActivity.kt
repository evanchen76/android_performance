package evan.chen.toturial.memoryleaksample.leak

import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import evan.chen.toturial.memoryleaksample.MyBroadcastReceiver
import evan.chen.toturial.memoryleaksample.databinding.ActivityBroadcastBinding

class BroadcastActivity : AppCompatActivity() {
    private val binding by lazy { ActivityBroadcastBinding.inflate(layoutInflater) }

    private lateinit var broadcastReceiver: MyBroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        broadcastReceiver = MyBroadcastReceiver()
    }

    override fun onResume() {
        super.onResume()

        val intentFilter = IntentFilter("evan.chen.tutorial.broadcastsample.Action1")
        registerReceiver(broadcastReceiver, intentFilter)
    }

}