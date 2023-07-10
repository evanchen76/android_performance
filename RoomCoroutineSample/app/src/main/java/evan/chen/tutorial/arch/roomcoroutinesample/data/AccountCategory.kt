package evan.chen.tutorial.arch.roomcoroutinesample.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "Account_Category", indices = [Index(
        value = ["type", "name"],
        unique = true
    )]
)
data class AccountCategory(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo val type: String,
    @ColumnInfo val name: String
)