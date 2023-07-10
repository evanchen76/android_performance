package evan.chen.tutorial.arch.roomcoroutinesample

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class RecordAddViewModelFactory(private val application: Application, private val type: String) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RecordAddViewModel(application, type) as T
    }
}