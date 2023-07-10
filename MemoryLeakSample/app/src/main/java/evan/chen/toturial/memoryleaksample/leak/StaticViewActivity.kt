package evan.chen.toturial.memoryleaksample.leak

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import evan.chen.toturial.memoryleaksample.R

class StaticViewActivity : AppCompatActivity() {

    companion object {
        private var textView: TextView? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_static_view)

        textView = findViewById(R.id.textView)
    }


}