package evan.chen.tutorial.arch.roomcoroutinesample.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Dao
interface AccountRecordDao {

    //新增記帳記錄
    @Insert
    fun insert(accountRecord: AccountRecord): Long

    //查詢單筆記錄資料
    @Query("SELECT A.*, B.name as categoryName FROM Account_Record A JOIN Account_Category B ON A.categoryId = B.id WHERE A.id = :id")
    fun getRecordById(id: Int): Flow<AccountRecordDetail>

    //更新記帳記錄
    @Update
    fun update(accountRecord: AccountRecord)

    //更新金額、日期、備註
    @Query("UPDATE Account_Record SET money = :money, date= :date, memo = :memo WHERE id= :Id")
    fun updateRecord(Id: Int, money: Int, date: LocalDate, memo: String?)

    //依指定日期查詢記帳記錄
    @Query("SELECT A.id, A.type, A.money, A.memo, A.categoryId, B.name as categoryName, A.date FROM Account_Record A JOIN Account_Category B ON A.categoryId = B.id WHERE A.date = :date")
    fun getRecordByDate(date: String): Flow<List<DailyRecord>>

    //依起始、結束日區間，查詢分類統計
    @Query("SELECT A.type, SUM(A.money) AS money, A.categoryId, B.name as categoryName FROM Account_Record A JOIN Account_Category B ON A.categoryId = B.id WHERE A.date BETWEEN :startDate AND :endDate GROUP BY A.type, A.categoryId, B.name")
    fun getStatisticsBetweenDate(
        startDate: String,
        endDate: String
    ): Flow<List<AccountStatistics>>

    //依id 刪除記帳記錄
    @Query("DELETE FROM Account_Record WHERE id = :id")
    fun delete(id: Int)

}