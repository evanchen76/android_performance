package evan.chen.toturial.memoryleaksample.leak

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import evan.chen.toturial.memoryleaksample.databinding.ActivitySingletonBinding

class SingletonActivity : AppCompatActivity() {
    private val binding by lazy { ActivitySingletonBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //Activity 被一個static 參考
        Class1.context = this
    }
}
