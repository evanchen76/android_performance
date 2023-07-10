package evan.chen.toturial.memoryleaksample.leak

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import evan.chen.toturial.memoryleaksample.databinding.ActivityThreadBinding

class ThreadActivity : AppCompatActivity() {
    private val binding by lazy { ActivityThreadBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //未執行完離開App會造成memory leak
        object : Thread() {
            override fun run() {
                sleep(10000)
                runOnUiThread {
                    binding.result.text = "Done"
                }
            }
        }.start()

    }
}
