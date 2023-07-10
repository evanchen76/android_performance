package evan.chen.tutorial.arch.roomcoroutinesample.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [AccountRecord::class, AccountCategory::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class AccountDatabase : RoomDatabase() {

    abstract fun accountRecordDao(): AccountRecordDao
    abstract fun categoryDao(): AccountCategoryDao

    companion object {
        @Volatile
        private var INSTANCE: AccountDatabase? = null

        fun getDatabase(context: Context): AccountDatabase {
            return INSTANCE
                ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        AccountDatabase::class.java,
                        "account_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                    instance
                }
        }

    }

}