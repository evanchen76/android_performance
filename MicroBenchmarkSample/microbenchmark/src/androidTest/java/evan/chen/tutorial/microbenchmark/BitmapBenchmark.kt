package evan.chen.tutorial.microbenchmark

import androidx.benchmark.junit4.BenchmarkRule
import androidx.benchmark.junit4.measureRepeated
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import evan.chen.tutorial.benchmarkable.ImageLoader
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class BitmapBenchmark {

    @get:Rule
    val benchmarkRule = BenchmarkRule()

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun loadImageResize() {
        benchmarkRule.measureRepeated {
            val imageLoader = ImageLoader(context)
            imageLoader.loadImageResize()
        }
    }

    @Test
    fun loadImage() {
        benchmarkRule.measureRepeated {
            val imageLoader = ImageLoader(context)
            imageLoader.loadImage()
        }
    }

}