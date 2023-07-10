package evan.chen.tutorial.arch.roomcoroutinesample.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountCategoryDao {
    @Query("SELECT * FROM Account_Category where type = :type")
    fun getCategory(type: String): Flow<List<AccountCategory>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertCategory(category: AccountCategory):Long
}