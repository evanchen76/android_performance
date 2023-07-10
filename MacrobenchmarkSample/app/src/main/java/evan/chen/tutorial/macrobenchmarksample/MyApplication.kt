package evan.chen.tutorial.macrobenchmarksample

import android.app.Application

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
//        Thread.sleep(1000)
    }
}