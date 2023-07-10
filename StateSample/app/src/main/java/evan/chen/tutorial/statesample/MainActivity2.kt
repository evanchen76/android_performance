package evan.chen.tutorial.statesample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import evan.chen.tutorial.statesample.databinding.ActivityMain2Binding
import evan.chen.tutorial.statesample.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity2 : AppCompatActivity() {

    private val binding by lazy { ActivityMain2Binding.inflate(layoutInflater) }

    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (savedInstanceState != null){
            count = savedInstanceState.getInt("count")
            display()
        }

        binding.add.setOnClickListener {
            count ++
            display()
        }

        binding.minus.setOnClickListener {
            count --
            display()
        }

        display()

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("count", count)
    }

    private fun display(){
        binding.result.text = "Count:$count"
    }
}