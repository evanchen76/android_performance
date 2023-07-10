package evan.chen.tutorial.macrobenchmark

import androidx.benchmark.macro.*
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Until
import junit.framework.TestCase
import org.junit.Assert.fail
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExampleStartupBenchmark {
    @get:Rule
    val benchmarkRule = MacrobenchmarkRule()

    @Test
    fun startup() = benchmarkRule.measureRepeated(
        packageName = "evan.chen.tutorial.macrobenchmarksample",
        metrics = listOf(
            StartupTimingMetric()
        ),
        iterations = 5,
        startupMode = StartupMode.COLD
    ) {
        startActivityAndWait()
    }

    @Test
    fun warmStartup() = benchmarkRule.measureRepeated(
        packageName = "evan.chen.tutorial.macrobenchmarksample",
        metrics = listOf(
            StartupTimingMetric()
        ),
        iterations = 5,
        startupMode = StartupMode.WARM
    ) {
        pressHome()
        startActivityAndWait()
    }

    @Test
    fun hotStartup() = benchmarkRule.measureRepeated(
        packageName = "evan.chen.tutorial.macrobenchmarksample",
        metrics = listOf(
            StartupTimingMetric()
        ),
        iterations = 5,
        startupMode = StartupMode.HOT
    ) {
        pressHome()
        startActivityAndWait()
    }

    @OptIn(ExperimentalMetricApi::class)
    @Test
    fun loadDataTest() = benchmarkRule.measureRepeated(
        packageName = "evan.chen.tutorial.macrobenchmarksample",
        metrics = listOf(
            TraceSectionMetric("LoadData"),
        ),
        iterations = 5,
        startupMode = StartupMode.WARM,
        setupBlock = {
            pressHome()
            startActivityAndWait()
        }
    ) {
        //取得按鈕Button
        clickOnId("loadDataButton")

        //等待顯示Data
        waitForTextShown("Data")
    }

    private fun MacrobenchmarkScope.waitForTextShown(text: String) {
        check(device.wait(Until.hasObject(By.text(text)), 500)) {
            "View showing '$text' not found after waiting 500 ms."
        }
    }

    private fun MacrobenchmarkScope.clickOnId(resourceId: String) {
        val selector = By.res(packageName, resourceId)
        println("package: $packageName, resource: $resourceId")
        if (!device.wait(Until.hasObject(selector), 500)) {
            TestCase.fail("Did not find object with id $resourceId")
        }
        device
            .findObject(selector)
            .click()
        // Chill to ensure we capture the end of the click span in the trace.
        Thread.sleep(100)
    }

}