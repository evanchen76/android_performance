package evan.chen.toturial.uiperformance

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.os.trace
import evan.chen.toturial.uiperformance.databinding.ActivityLayerBinding
import java.lang.Thread.sleep

class LayerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLayerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLayerBinding.inflate(layoutInflater)

        setContentView(binding.root)

        trace("LayerActivity") {
            sleep(2000)
        }
//        binding.memberImage.visibility = View.INVISIBLE
//        binding.memberName.visibility = View.INVISIBLE
//
    }
}