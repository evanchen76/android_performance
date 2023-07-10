package evan.chen.tutorial.myapplication

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.LruCache
import androidx.appcompat.app.AppCompatActivity
import evan.chen.tutorial.myapplication.databinding.ActivityMainBinding
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL


class MainActivity : AppCompatActivity() {
    //建立快取
    private val memoryCache by lazy {
        LruCache<String, Bitmap>(
            (Runtime.getRuntime().maxMemory() / 1024 / 8).toInt()
        ) }

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.loadImage.setOnClickListener {
            downloadImage("https://www.google.com.tw/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png")
        }
    }

    private fun downloadImage(url: String) {
        val images = memoryCache.get(url)
        if (images != null) {
            println("有快取 => 直接顯示在ImageView")
            binding.imageView.setImageBitmap(images)
        } else {
            println("沒有快取 => 需下載圖片")
            var bitmap: Bitmap? = null
            val thread = Thread {
                bitmap = getBitmapFromUrl(url)
            }
            thread.start()
            thread.join()
            binding.imageView.setImageBitmap(bitmap)
            memoryCache.put(url, bitmap)
        }
    }

    private fun getBitmapFromUrl(imageUrl: String?): Bitmap? {
        return try {
            val url = URL(imageUrl)
            val connection =
                url.openConnection() as HttpURLConnection
            connection.doInput = true
            connection.connect()
            val input = connection.inputStream
            BitmapFactory.decodeStream(input)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }
}