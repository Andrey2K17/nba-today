package com.example.nba_today.common

import com.example.nba_today.adapters.GamesHomePagerAdapter
import java.text.SimpleDateFormat
import java.util.*

/**
 * Returns the date of a given page of the adapter. E.g. if the position is the center page
 * (NUM_PAGES / 2) then the date returned is today, if the position is the center page + 1 then
 * the date returned is tomorrow.
 */
fun getDateForPosition(position: Int): Long {
    val todayPage = GamesHomePagerAdapter.NUM_PAGES / 2
    val date = Calendar.getInstance()
    date.add(Calendar.DAY_OF_YEAR, -1)

    return if (position == todayPage) {
        date.timeInMillis
    } else {
        date.add(Calendar.DAY_OF_YEAR, -1 * (todayPage - position))
        date.timeInMillis
    }
}

/**
 * Returns the position in the adapter that a given page corresponds to. E.g. if the date is
 * today then the position should be the page in the center (NUM_PAGES / 2), if the date is
 * yesterday then the position is the center most page - 1.
 */
fun getPositionForDate(date: Long): Int {
    val selectedDate = Calendar.getInstance()
    selectedDate.timeInMillis = date
    val today = Calendar.getInstance()

    val dayDiff = getDayDifferenceBetweenDates(today, selectedDate)

    return GamesHomePagerAdapter.NUM_PAGES / 2 - dayDiff
}

private fun getDayDifferenceBetweenDates(today: Calendar, date: Calendar): Int {
    today.set(Calendar.HOUR_OF_DAY, 0)
    today.set(Calendar.MINUTE, 0)
    today.set(Calendar.SECOND, 0)

    date.set(Calendar.HOUR_OF_DAY, 0)
    date.set(Calendar.MINUTE, 0)
    date.set(Calendar.SECOND, 0)

    val millisDiff = today.timeInMillis - date.timeInMillis
    return (millisDiff / (1000 * 60 * 60 * 24)).toInt()
}

fun areDatesEqual(calendar1: Calendar, calendar2: Calendar): Boolean {
    return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR) && calendar1.get(Calendar.DAY_OF_YEAR) == calendar2.get(
        Calendar.DAY_OF_YEAR
    )
}

fun isDateToday(date: Date): Boolean {
    val calNow = Calendar.getInstance()
    val calDate = Calendar.getInstance()
    calNow.add(Calendar.DAY_OF_YEAR, -1)
    calDate.time = date
    return areDatesEqual(calNow, calDate)
}

fun isDateYesterday(date: Date): Boolean {
    val calYesterday = Calendar.getInstance()
    calYesterday.add(Calendar.DAY_OF_YEAR, -2)
    val calDate = Calendar.getInstance()
    calDate.time = date
    return areDatesEqual(calYesterday, calDate)
}

fun isDateTomorrow(date: Date): Boolean {
    val calTomorrow = Calendar.getInstance()
    //calTomorrow.add(Calendar.DAY_OF_YEAR, 1)
    val calDate = Calendar.getInstance()
    calDate.time = date
    return areDatesEqual(calTomorrow, calDate)
}

fun formatNavigatorDate(date: Date): String = if (isDateToday(date)) {
    "Сегодня"
} else if (isDateYesterday(date)) {
    "Вчера"
} else if (isDateTomorrow(date)) {
    "Завтра"
} else {
    val format = SimpleDateFormat("EEEE, d MMMM, yyyy", Locale.getDefault())
    format.format(date)
}