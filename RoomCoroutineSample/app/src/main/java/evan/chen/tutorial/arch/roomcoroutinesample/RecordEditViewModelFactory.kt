package evan.chen.tutorial.arch.roomcoroutinesample

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class RecordEditViewModelFactory(private val application: Application, private val id: Int) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RecordEditViewModel(application, id) as T
    }
}