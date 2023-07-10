package evan.chen.tutorial.threadsample

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import evan.chen.tutorial.threadsample.databinding.ActivityThreadPoolBinding
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class ThreadPoolActivity : AppCompatActivity() {
    private val binding by lazy { ActivityThreadPoolBinding.inflate(layoutInflater) }
    private val TAG = "ThreadPoolActivity"
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            start()
        }
    }

    fun updateUI(taskId: Int) {
    }

    private fun start() {
        //步驟1：建立執行緒池，並指定執行緒數量為3
        val executorService = Executors.newFixedThreadPool(3)

        for (i in 1..10) {
            //步驟2：將任務加入執行緒池
            executorService.execute {
                downloadImage("https://www.example.com/$i.jpg")
                Log.d(TAG, "Task $i, thread:${Thread.currentThread().name} is completed.")

                handler.post { updateUI(i) }
            }
        }

        //步驟3：關閉執行緒池
        executorService.shutdown()
    }

    private fun downloadImage(url:String) {
        //模擬依url下載圖片
        Thread.sleep(1000)
    }
}