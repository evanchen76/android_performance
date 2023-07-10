package evan.chen.tutorial.arch.roomcoroutinesample

import android.app.Application
import androidx.lifecycle.*
import evan.chen.tutorial.arch.roomcoroutinesample.data.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecordAddViewModel(application: Application, type: String) : AndroidViewModel(application) {

    private val repository: AccountCategoryRepository
    private val recordRepository: AccountRepository

    var categories: MutableLiveData<List<AccountCategory>> = MutableLiveData()
    var category: AccountCategory? = null

    init {
        val categoryDao = AccountDatabase.getDatabase(application).categoryDao()
        val accountRecordDao = AccountDatabase.getDatabase(application).accountRecordDao()
        repository =
            AccountCategoryRepository(categoryDao)

        recordRepository = AccountRepository(accountRecordDao)

        viewModelScope.launch (Dispatchers.IO) {
            repository.getCategoryListByType(type).collect {
                categories.postValue(it)
            }
        }
    }

    fun insert(record: AccountRecord) {
        viewModelScope.launch (Dispatchers.IO) {
            recordRepository.insert(record)
        }
    }

}