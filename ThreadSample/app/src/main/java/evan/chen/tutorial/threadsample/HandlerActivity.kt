package evan.chen.tutorial.threadsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import evan.chen.tutorial.threadsample.databinding.ActivityHandlerBinding

class HandlerActivity() : AppCompatActivity() {
    private val binding by lazy { ActivityHandlerBinding.inflate(layoutInflater) }
    private val TAG = "MainActivity"
    val messageStart: Int = 1
    val messageProcess: Int = 2
    val messageEnd: Int = 3

    private val handler = object : Handler(Looper.myLooper()!!) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            Log.d(TAG, "msg = $msg")

            if (msg.what == messageStart) {
                binding.result.text = "Start"
            }else if (msg.what == messageProcess) {
                binding.result.text = msg.obj.toString()
            }else if (msg.what == messageEnd) {
                binding.result.text = "End"
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            //開始執行任務
            Thread {
                for (i in 1..10) {
                    longProcess()
                    val msg = Message()
                    if ( i == 1){
                        //處理第1筆時，通知UI已經開始
                        msg.what = messageStart
                    }else if (i == 10){
                        //處理第10筆時，通知UI已經完成
                        msg.what = messageEnd
                    }else {
                        //通知UI處理中
                        msg.what = messageProcess
                    }
                    //傳遞的資料內容為第i筆
                    msg.obj = i
                    handler.sendMessage(msg) // 傳送訊息
                }

            }.start()
        }

    }

    private fun longProcess() {
        Thread.sleep(500)
    }
}
