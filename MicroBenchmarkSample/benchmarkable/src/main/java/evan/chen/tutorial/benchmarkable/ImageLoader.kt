package evan.chen.tutorial.benchmarkable

import android.content.Context
import android.graphics.BitmapFactory

class ImageLoader(private val context: Context) {

    fun loadImage(){
        BitmapFactory.decodeResource(context.resources, R.drawable.scenery)
    }

    fun loadImageResize(){
        val options = BitmapFactory.Options().apply {
            inJustDecodeBounds = true
        }
        BitmapFactory.decodeResource(context.resources, R.drawable.scenery, options)

        val smallImageWidth = 200
        val smallImageHeight = 200

        options.inSampleSize = calculateInSampleSize(options, smallImageWidth, smallImageHeight)
        options.inJustDecodeBounds = false

        BitmapFactory.decodeResource(context.resources, R.drawable.scenery, options)
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