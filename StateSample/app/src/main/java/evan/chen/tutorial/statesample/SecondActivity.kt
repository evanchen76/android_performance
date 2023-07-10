package evan.chen.tutorial.statesample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import evan.chen.tutorial.statesample.databinding.ActivityMainBinding
import evan.chen.tutorial.statesample.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private val binding by lazy { ActivitySecondBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val id = intent.getStringExtra("ID")
        println("oncreate_id:$id")

        binding.result.text = id
    }
}