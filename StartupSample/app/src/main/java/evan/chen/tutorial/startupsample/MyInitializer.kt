package evan.chen.tutorial.startupsample

import android.content.Context
import androidx.startup.Initializer

class MyInitializer : Initializer<MyLibrary> {
    override fun create(context: Context): MyLibrary {
        MyLibrary.init(context)
        return MyLibrary
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        //需要先載入MyInitializer2
        return mutableListOf(MyInitializer2::class.java)
    }
}