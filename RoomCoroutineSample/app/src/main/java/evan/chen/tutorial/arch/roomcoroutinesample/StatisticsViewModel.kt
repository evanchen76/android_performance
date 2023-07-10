package evan.chen.tutorial.arch.roomcoroutinesample

import android.app.Application
import androidx.lifecycle.*
import evan.chen.tutorial.arch.roomcoroutinesample.data.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.YearMonth

class StatisticsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: AccountRepository

    var month: MutableLiveData<YearMonth> = MutableLiveData()

    init {
        month.value = YearMonth.from(LocalDate.now())

        val savingDao = AccountDatabase.getDatabase(application).accountRecordDao()
        repository = AccountRepository(savingDao)
    }

    var accountStatistics: LiveData<List<AccountStatistics>> =
        Transformations.switchMap(month) { month ->
            //本月第一天
            val startDate = month.atDay(1)
            //本月最後一天
            val endDate = month.atEndOfMonth()
            //依起迄日查詢統計資料

            val list :MutableLiveData<List<AccountStatistics>> = MutableLiveData()

            viewModelScope.launch (Dispatchers.IO) {
                repository.getAccountStatistics(startDate, endDate).collect{
                    list.postValue(it)
                }
            }

            list
        }

    fun nextMonth() {
        month.value = month.value!!.plusMonths(1)
    }

    fun lastMonth() {
        month.value = month.value!!.minusMonths(1)
    }

}