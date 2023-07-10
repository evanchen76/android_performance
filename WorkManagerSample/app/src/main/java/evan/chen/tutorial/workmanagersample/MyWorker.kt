package evan.chen.tutorial.workmanagersample

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyWorker(appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {

    override fun doWork(): Result {

        val key1Value = inputData.getString("KEY1")
        println("key1:$key1Value")

        println("執行任務")

        return Result.success()
    }
}