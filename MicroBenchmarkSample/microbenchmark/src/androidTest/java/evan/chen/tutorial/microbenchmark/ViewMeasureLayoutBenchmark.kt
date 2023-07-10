
package evan.chen.tutorial.microbenchmark

import android.view.LayoutInflater
import android.view.View.MeasureSpec
import androidx.benchmark.junit4.BenchmarkRule
import androidx.benchmark.junit4.measureRepeated
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import evan.chen.tutorial.benchmarkable.R

@LargeTest
@RunWith(AndroidJUnit4::class)
class ViewMeasureLayoutBenchmark {

    @get:Rule
    val benchmarkRule = BenchmarkRule()

    @Test
    fun constraintLayoutAtMost() {
        benchmarkMeasureLayout(R.layout.activity_constraintlayout, MeasureSpec.AT_MOST)
    }

    @Test
    fun constraintLayoutExactly() {
        benchmarkMeasureLayout(R.layout.activity_constraintlayout, MeasureSpec.EXACTLY)
    }

    @Test
    fun linearLayoutAtMost() {
        benchmarkMeasureLayout(R.layout.activity_linearlayout, MeasureSpec.AT_MOST)
    }

    @Test
    fun linearLayoutExactly() {
        benchmarkMeasureLayout(R.layout.activity_linearlayout, MeasureSpec.EXACTLY)
    }

    private fun benchmarkMeasureLayout(layoutRes: Int, mode: Int) {
        val context = InstrumentationRegistry.getInstrumentation().context
        val inflater = LayoutInflater.from(context)

        benchmarkRule.measureRepeated {
            val container = runWithTimingDisabled { inflater.inflate(layoutRes, null) }

            val widthMeasureSpec = MeasureSpec.makeMeasureSpec(1080, mode)
            val heightMeasureSpec = MeasureSpec.makeMeasureSpec(1920, mode)

            container.measure(widthMeasureSpec, heightMeasureSpec)
            container.layout(0, 0, container.measuredWidth, container.measuredHeight)
        }
    }
}
