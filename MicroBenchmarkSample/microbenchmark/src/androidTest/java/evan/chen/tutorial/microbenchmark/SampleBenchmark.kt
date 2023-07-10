package evan.chen.tutorial.microbenchmark

import androidx.benchmark.junit4.BenchmarkRule
import androidx.benchmark.junit4.measureRepeated
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SampleBenchmark {

    @get:Rule
    val benchmarkRule = BenchmarkRule()

    @Test
    fun benchMarkStringBuilder() {
        benchmarkRule.measureRepeated {
            stringBuilder()
        }
    }

    @Test
    fun benchmarkStringPlus() {
        benchmarkRule.measureRepeated {
            stringPlus()
        }
    }

    private fun stringBuilder(){
        val stringBuilder = StringBuilder()
        repeat(50) {
            stringBuilder.append("Hello")
        }
        val result = stringBuilder.toString()
    }

    private fun stringPlus(){
        var result = ""
        repeat(50) {
            result += "Hello"
        }
    }
}