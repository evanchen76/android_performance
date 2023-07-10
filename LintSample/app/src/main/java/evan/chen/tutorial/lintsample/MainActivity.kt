package evan.chen.tutorial.lintsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import evan.chen.tutorial.lintsample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //Activity 被一個static 參考
        Class1.context = this
    }
}
