package evan.chen.tutorial.macrobenchmarksample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.os.TraceCompat
import androidx.lifecycle.ViewModelProvider
import androidx.tracing.Trace
import evan.chen.tutorial.macrobenchmarksample.databinding.ActivityMainBinding
import androidx.tracing.trace

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

//        Thread.sleep(500)

        binding.loadDataButton.setOnClickListener {
            trace("LoadData") {
                val result = Repository().getData()
                binding.resultTextView.text = result
            }
        }
    }

}