package evan.chen.tutorial.arch.roomcoroutinesample

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import evan.chen.tutorial.arch.roomcoroutinesample.data.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class RecordEditViewModel(application: Application, accountRecordId: Int) :
    AndroidViewModel(application) {

    private val repository: AccountCategoryRepository
    private val recordRepository: AccountRepository

    var accountRecord: MutableLiveData<AccountRecordDetail> = MutableLiveData()

    init {
        val categoryDao = AccountDatabase.getDatabase(application).categoryDao()
        val accountRecordDao = AccountDatabase.getDatabase(application).accountRecordDao()

        repository = AccountCategoryRepository(categoryDao)
        recordRepository = AccountRepository(accountRecordDao)

        viewModelScope.launch (Dispatchers.IO) {
            recordRepository.getAccountRecord(accountRecordId).collect{
                accountRecord.value = it
            }
        }

    }

    fun update(record: AccountRecord) {
        viewModelScope.launch (Dispatchers.IO) {
            recordRepository.update(record)
        }
    }

    fun delete(recordId: Int) {
        viewModelScope.launch (Dispatchers.IO) {
            recordRepository.delete(recordId)
        }
    }

}