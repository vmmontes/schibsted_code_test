package com.vmmontes.excurrency.presentation.utils

import java.text.SimpleDateFormat
import java.util.*

fun getDateTime(date : String, stringFormat : String) : Long {
    val parser = SimpleDateFormat(stringFormat, Locale.ENGLISH)
    return parser.parse(date).time
}

fun getTimeDateInStringFormat(time : Long, stringFormat : String) : String {
    val date = Date(time)
    val format = SimpleDateFormat(stringFormat, Locale.ENGLISH)
    return format.format(date)
}