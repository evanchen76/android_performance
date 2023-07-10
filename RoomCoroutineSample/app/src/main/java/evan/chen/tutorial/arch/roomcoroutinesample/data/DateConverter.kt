package evan.chen.tutorial.arch.roomcoroutinesample.data

import androidx.room.TypeConverter
import evan.chen.tutorial.arch.roomcoroutinesample.utils.DateUtils
import java.time.LocalDate

class DateConverter  {

    @TypeConverter
    fun fromDateString(value: String?): LocalDate? {
        return DateUtils().stringToDate(value!!)
    }

    @TypeConverter
    fun dateToString(date: LocalDate?): String? {
        return DateUtils().dateToString(date!!)
    }
}