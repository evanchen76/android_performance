package evan.chen.tutorial.splashscreensample

import android.animation.ObjectAnimator
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.ViewTreeObserver
import android.view.animation.AccelerateInterpolator
import androidx.annotation.RequiresApi
import androidx.core.animation.doOnEnd
import androidx.lifecycle.ViewModelProvider
import evan.chen.tutorial.splashscreensample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var viewModel: MainViewModel

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        splashScreen.setOnExitAnimationListener { splashScreenView ->
            // 建立淡出動畫
            val fadeOut = ObjectAnimator.ofFloat(splashScreenView, View.ALPHA, 1f, 0f)
            fadeOut.interpolator = AccelerateInterpolator()
            fadeOut.duration = 500L // 500ms的淡出動畫

            fadeOut.doOnEnd {
                // 當動畫結束後，移除 Splash Screen View
                splashScreenView.remove()
            }

            // 開始動畫
            fadeOut.start()
        }

        val content: View = findViewById(android.R.id.content)
        content.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    //使用ViewModel.isReady來判斷資料是否已經準備好
                    return if (viewModel.isReady) {
                        //資料已經準備好，移除OnPreDrawListener
                        content.viewTreeObserver.removeOnPreDrawListener(this)
                        true
                    } else {
                        //資料還沒準備好，回傳false
                        false
                    }
                }
            }
        )

        viewModel.loadData()
    }
}