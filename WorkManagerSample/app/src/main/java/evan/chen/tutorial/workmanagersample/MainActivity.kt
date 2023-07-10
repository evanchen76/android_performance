package evan.chen.tutorial.workmanagersample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {

            //建立Work
            createWork()

            //建立條件約束Work
//            createConstraintWork()

            //建立週期性Work
//            createPeriodicWork()

            //傳遞資料
//            createPassDataWork()
        }

    }

    private fun createWork() {
        val workRequest: OneTimeWorkRequest =
            OneTimeWorkRequestBuilder<MyWorker>()
                .setInitialDelay(5, TimeUnit.SECONDS)
                .build()

        val workManager = WorkManager
            .getInstance(this)
        workManager.enqueue(workRequest)
    }

    private fun createPeriodicWork() {
        //建立週期性Work，每15分重覆
        val workRequest =
            PeriodicWorkRequest.Builder(
                MyWorker::class.java,
                15,
                TimeUnit.MINUTES
            ).build()

        WorkManager
            .getInstance(this)
            .enqueue(workRequest)
    }

    private fun createConstraintWork() {
        //建立條件約束的Work
        val constraints = Constraints.Builder()
            .setRequiresCharging(true) //充電中才執行
            .setRequiredNetworkType(NetworkType.UNMETERED) //非計費網路才執行
            .setRequiresBatteryNotLow(true) //電量不足時不執行
            .setRequiresStorageNotLow(true) //儲存空間不足時不執行
            .setRequiresDeviceIdle(true) //閒置時才執行
            .build()

        val workRequest = OneTimeWorkRequest.Builder(MyWorker::class.java)
            .setConstraints(constraints)
            .build()

        WorkManager
            .getInstance(this)
            .enqueue(workRequest)
    }

    private fun createPassDataWork() {
        //建立傳遞參數的Work
        val data = Data.Builder()
            .putString("KEY1", "Test")
            .build()

        val workRequest = OneTimeWorkRequest.Builder(MyWorker::class.java)
            .setInputData(data)
            .build()

        WorkManager
            .getInstance(this)
            .enqueue(workRequest)
    }

    private fun createExpeditedWork() {
        val workRequest: OneTimeWorkRequest =
            OneTimeWorkRequestBuilder<MyWorker>()
                .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
                .build()

        val workManager = WorkManager
            .getInstance(this)
        workManager.enqueue(workRequest)
    }
}
