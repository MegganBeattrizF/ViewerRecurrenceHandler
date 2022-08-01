package com.example.viewerrecurrencehandler.extensions

import android.app.Activity
import android.content.Context
import androidx.core.content.edit

class RecurrencePreferences(activity: Activity) {
    private val preferences by lazy {
        activity.getSharedPreferences(
            RECURRENCE_BANNER,
            Context.MODE_PRIVATE
        )
    }

    fun saveCurrentHour() = preferences?.edit(true) {
        putInt(RECURRENCE_BANNER, (getSavedCurrentHour()))
    }

    fun getSavedCurrentHour() = preferences.getInt(RECURRENCE_BANNER, 0)

    companion object {
        const val RECURRENCE_BANNER = "RECURRENCE_BANNER"
    }
}