package evan.chen.tutorial.arch.roomcoroutinesample

import android.app.Application
import androidx.lifecycle.*
import evan.chen.tutorial.arch.roomcoroutinesample.data.*
import evan.chen.tutorial.arch.roomcoroutinesample.utils.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import java.time.LocalDate

class AccountViewModel(application: Application) : AndroidViewModel(application) {

    //使用AndroidViewModel，可取得application context
    private val repository: AccountRepository

    var today: MutableLiveData<LocalDate> = MutableLiveData()

    init {
        today.value = LocalDate.now()

        val savingDao = AccountDatabase.getDatabase(application).accountRecordDao()
        repository =
            AccountRepository(savingDao)
    }

    var openItemEvent: MutableLiveData<Event<Int>> = MutableLiveData()

    var todayRecords: LiveData<List<DailyRecord>> = Transformations.switchMap(today) { date ->
        val list: MutableLiveData<List<DailyRecord>> = MutableLiveData()

        viewModelScope.launch (Dispatchers.IO) {
            try {
                repository.getTodayRecord(date).collect {
                    list.postValue(it)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        list

    }

    var todaySpending: LiveData<Int> = Transformations.map(todayRecords) { todayRecords ->
        todayRecords.filter { it.type == "支出" }.sumBy { it.money }
    }

    var todaySaving: LiveData<Int> = Transformations.map(todayRecords) { todayRecords ->
        todayRecords.filter { it.type == "收入" }.sumBy { it.money }
    }

    fun insert(accountRecord: AccountRecord) {
        viewModelScope.launch (Dispatchers.IO) {
            repository.insert(accountRecord)
        }
    }

    fun nextDay() {
        today.value = today.value!!.plusDays(1)
    }

    fun lastDay() {
        today.value = today.value!!.minusDays(1)
    }

    fun openItem(id: Int) {
        openItemEvent.value = Event(id)
    }

}