package evan.chen.toturial.memoryleaksample

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import evan.chen.toturial.memoryleaksample.databinding.ActivityLoadImageBinding

class LoadImageActivity : AppCompatActivity() {
    private val binding by lazy { ActivityLoadImageBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.loadImage.setOnClickListener {
            val options = BitmapFactory.Options().apply {
                inJustDecodeBounds = true
            }
            //在圖片載入到記憶體前，先取得圖片的寬高
            BitmapFactory.decodeResource(resources, R.mipmap.scenery, options)
            val imageHeight: Int = options.outHeight
            val imageWidth: Int = options.outWidth
            val imageType: String = options.outMimeType

            //在ImageView呈現的寬
            val smallImageWidth = 200
            //在ImageView呈現的高
            val smallImageHeight = 200

            //記算Size，縮小幾倍
            options.inSampleSize = calculateInSampleSize(options, smallImageWidth, smallImageHeight)
            options.inJustDecodeBounds = false

            binding.imageView.setImageBitmap(
                BitmapFactory.decodeResource(resources, R.mipmap.scenery, options)
//                BitmapFactory.decodeResource(resources, R.mipmap.scenery)
            )
        }
    }

    fun calculateInSampleSize(options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Int {
        val (height: Int, width: Int) = options.run { outHeight to outWidth }
        var inSampleSize = 1

        if (height > reqHeight || width > reqWidth) {

            val halfHeight: Int = height / 2
            val halfWidth: Int = width / 2

            while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth) {
                inSampleSize *= 2
            }
        }

        return inSampleSize
    }
}