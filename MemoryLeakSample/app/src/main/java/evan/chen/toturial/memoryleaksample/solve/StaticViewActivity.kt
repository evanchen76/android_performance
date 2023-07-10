package evan.chen.toturial.memoryleaksample.solve

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import evan.chen.toturial.memoryleaksample.R
import java.lang.ref.WeakReference

class StaticViewActivity : AppCompatActivity() {

    companion object {
        //加上WeakReference避免 記憶體洩漏
        private var textView: WeakReference<TextView>? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_static_view)

        textView = WeakReference(findViewById(R.id.textView))
    }
}