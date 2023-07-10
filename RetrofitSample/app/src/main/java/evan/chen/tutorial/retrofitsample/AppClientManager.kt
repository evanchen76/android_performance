package evan.chen.tutorial.retrofitsample

import android.util.Log
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class AppClientManager private constructor() {
    private val retrofit: Retrofit

    init {
        val okHttpClient = OkHttpClient().newBuilder()
            .addInterceptor(NoGzipInterceptor())
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(Config.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    companion object {
        private val manager = AppClientManager()
        val client: Retrofit
            get() = manager.retrofit
    }

    class NoGzipInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request().newBuilder()
                .addHeader("Accept-Encoding", "identity")
                .build()
            return chain.proceed(request)
        }
    }
}

