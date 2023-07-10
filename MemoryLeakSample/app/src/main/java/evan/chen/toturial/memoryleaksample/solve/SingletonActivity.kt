package evan.chen.toturial.memoryleaksample.solve

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import evan.chen.toturial.memoryleaksample.databinding.ActivitySingletonBinding

class SingletonActivity : AppCompatActivity() {
    private val binding by lazy { ActivitySingletonBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        Class1.initApp(applicationContext)
    }
}
