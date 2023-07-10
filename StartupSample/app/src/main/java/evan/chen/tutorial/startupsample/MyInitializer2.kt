package evan.chen.tutorial.startupsample

import android.content.Context
import androidx.startup.Initializer

class MyInitializer2 : Initializer<MyLibrary2> {
    override fun create(context: Context): MyLibrary2 {
        MyLibrary2.init(context)
        return MyLibrary2
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }
}