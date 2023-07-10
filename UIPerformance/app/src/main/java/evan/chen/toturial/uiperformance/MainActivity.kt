package evan.chen.toturial.uiperformance

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import evan.chen.toturial.uiperformance.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }
}