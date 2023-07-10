package evan.chen.tutorial.arch.roomcoroutinesample.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

class DateUtils {
    fun stringToDate(dateString: String): LocalDate? {
        return try {
            LocalDate.parse(dateString)
        }catch (exception: DateTimeParseException){
            null
        }
    }

    fun dateToString(date: LocalDate): String? {
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    }
}