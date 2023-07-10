package evan.chen.toturial.memoryleaksample.solve

import android.content.Context

object Class1 {
    private lateinit var applicationContext: Context

    fun initApp(context:Context){
        applicationContext = context.applicationContext
    }
}