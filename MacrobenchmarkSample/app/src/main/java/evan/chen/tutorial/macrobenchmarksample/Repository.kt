package evan.chen.tutorial.macrobenchmarksample

import androidx.tracing.trace

class Repository {
    fun getData(): String {
        //some process t data
        return "Data"
    }
}