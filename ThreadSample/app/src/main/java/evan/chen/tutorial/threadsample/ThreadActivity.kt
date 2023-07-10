package evan.chen.tutorial.threadsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import evan.chen.tutorial.threadsample.databinding.ActivityThreadBinding

class ThreadActivity : AppCompatActivity() {
    private val binding by lazy { ActivityThreadBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.start.setOnClickListener {
            Thread.sleep(2000)
        }
    }
}