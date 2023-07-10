package evan.chen.tutorial.arch.roomcoroutinesample

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import evan.chen.tutorial.arch.roomcoroutinesample.data.AccountDatabase
import evan.chen.tutorial.arch.roomcoroutinesample.data.AccountCategoryRepository
import evan.chen.tutorial.arch.roomcoroutinesample.data.AccountCategory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryAddViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: AccountCategoryRepository

    init {
        val dao = AccountDatabase.getDatabase(application).categoryDao()
        repository = AccountCategoryRepository(dao)
    }

    fun insert(category: AccountCategory) {
        viewModelScope.launch (Dispatchers.IO) {
            repository.insert(category)
        }
    }

}