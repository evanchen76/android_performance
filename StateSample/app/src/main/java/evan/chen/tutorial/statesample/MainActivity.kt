package evan.chen.tutorial.statesample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import evan.chen.tutorial.statesample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        println("onCreate")

        binding.add.setOnClickListener {
//            count++
//            display()
            val intent = Intent(this, ProductActivity::class.java).apply {
                putExtra("productId", "1")
            }
            startActivity(intent)
        }

        binding.minus.setOnClickListener {
            count --
            display()
        }

        display()

    }

    override fun onStart() {
        super.onStart()
        println("onStart")
    }
    
    override fun onResume() {
        super.onResume()
        println("onResume")
    }

    override fun onPause() {
        super.onPause()
        println("onPause")
    }

    override fun onStop() {
        super.onStop()
        println("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("onDestroy")
    }

    private fun display(){
        binding.result.text = "Count:$count"
    }
}