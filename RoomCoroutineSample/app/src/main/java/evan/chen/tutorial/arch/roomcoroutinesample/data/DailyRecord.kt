package evan.chen.tutorial.arch.roomcoroutinesample.data

import java.time.LocalDate

data class DailyRecord(
    val id: Int,
    val type: String,
    val money: Int,
    val memo: String? = null,
    val categoryId: Int,
    val categoryName: String,
    val date: LocalDate
)