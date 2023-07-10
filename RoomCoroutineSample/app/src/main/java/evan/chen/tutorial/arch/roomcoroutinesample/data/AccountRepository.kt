package evan.chen.tutorial.arch.roomcoroutinesample.data

import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.time.format.DateTimeFormatter

interface IAccountRepository {
    fun insert(accountRecord: AccountRecord)
    fun getTodayRecord(date: LocalDate): Flow<List<DailyRecord>>
    fun getAccountStatistics(
        startDate: LocalDate,
        endDate: LocalDate
    ): Flow<List<AccountStatistics>>

    fun update(record: AccountRecord)
    fun delete(id: Int)
    fun getAccountRecord(id: Int): Flow<AccountRecordDetail>
}

class AccountRepository(private val recordDao: AccountRecordDao) :
    IAccountRepository {

    override fun insert(accountRecord: AccountRecord) {
        recordDao.insert(accountRecord)
    }

    override fun getTodayRecord(date: LocalDate): Flow<List<DailyRecord>> {
        val dateString = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        return recordDao.getRecordByDate(dateString)
    }

    override fun getAccountStatistics(
        startDate: LocalDate,
        endDate: LocalDate
    ): Flow<List<AccountStatistics>> {
        val startDateString = startDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        val endDateString = endDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))

        return recordDao.getStatisticsBetweenDate(startDateString, endDateString)
    }

    override fun update(record: AccountRecord) {
        recordDao.updateRecord(record.id, record.money, record.date, record.memo)
    }

    override fun delete(id: Int) {
        recordDao.delete(id)
    }

    override fun getAccountRecord(id: Int): Flow<AccountRecordDetail> {
        return recordDao.getRecordById(id)
    }

}
