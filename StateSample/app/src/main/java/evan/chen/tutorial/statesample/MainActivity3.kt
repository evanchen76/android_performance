package evan.chen.tutorial.statesample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import evan.chen.tutorial.statesample.databinding.ActivityMain3Binding
import evan.chen.tutorial.statesample.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity3 : AppCompatActivity() {

    private val binding by lazy { ActivityMain3Binding.inflate(layoutInflater) }

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.add.setOnClickListener {
            viewModel.count ++
            display()
        }

        display()

    }

    private fun display(){
        binding.result.text = "Count:${viewModel.count}"
    }
}