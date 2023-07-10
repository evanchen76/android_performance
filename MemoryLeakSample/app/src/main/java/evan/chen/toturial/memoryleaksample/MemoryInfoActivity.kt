package evan.chen.toturial.memoryleaksample

import android.app.ActivityManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import evan.chen.toturial.memoryleaksample.databinding.ActivityMemoryInfoBinding

class MemoryInfoActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMemoryInfoBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (getAvailableMemory().lowMemory) {
            //處於低記憶體資源狀況
        }

        println("可用記憶體:${getAvailableMemory().availMem}")
        println("總記憶體:${getAvailableMemory().totalMem}")
    }

    private fun getAvailableMemory(): ActivityManager.MemoryInfo {
        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        return ActivityManager.MemoryInfo().also { memoryInfo ->
            activityManager.getMemoryInfo(memoryInfo)
        }
    }
}