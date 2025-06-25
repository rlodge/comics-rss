package com.electronicmuse.comicsrss

import java.time.DayOfWeek
import java.time.OffsetDateTime

enum class Schedule(vararg days: DayOfWeek) {
    SEVEN_DAYS(DayOfWeek.SUNDAY, DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY),
    SUNDAY(DayOfWeek.SUNDAY);

    val daySet = days.toSet()

    fun acceptDate(date: OffsetDateTime): Boolean {
        return daySet.contains(date.dayOfWeek)
    }
}
