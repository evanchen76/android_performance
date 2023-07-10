package evan.chen.tutorial.arch.roomcoroutinesample.data

import androidx.room.*
import java.time.LocalDate

@Entity(
    tableName = "Account_Record", indices = [Index(
        value = ["date"]
    )]
)
data class AccountRecord(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo val type: String,
    @ColumnInfo val money: Int,
    @ColumnInfo val memo: String? = null,
    @ColumnInfo val categoryId: Int,
    @ColumnInfo val date: LocalDate
)

data class AccountRecordDetail(
    val id: Int,
    val type: String,
    val money: Int,
    val memo: String? = null,
    val categoryId: Int,
    val date: LocalDate,
    val categoryName: String? = null
)



