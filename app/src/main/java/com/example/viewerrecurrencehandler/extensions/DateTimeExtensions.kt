package com.example.viewerrecurrencehandler.extensions

import java.util.concurrent.TimeUnit

fun Long.timeMillisToHours(): Int {
    return TimeUnit.MILLISECONDS.toHours(this).toInt()
}

fun Long.timeMillisToMinutes(): Int {
    return TimeUnit.MILLISECONDS.toMinutes(this).toInt()
}