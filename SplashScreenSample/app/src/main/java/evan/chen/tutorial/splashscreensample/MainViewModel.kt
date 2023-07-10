package evan.chen.tutorial.splashscreensample

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    var isReady = false

    // 模擬與後端載入資料
    fun loadData() {
        Handler(Looper.getMainLooper()).postDelayed({
            //設定資料已經準備好
            isReady = true
        }, 1000)
    }
}