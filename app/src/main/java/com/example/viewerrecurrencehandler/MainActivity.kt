package com.example.viewerrecurrencehandler

import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.viewerrecurrencehandler.databinding.ActivityMainBinding.inflate
import com.example.viewerrecurrencehandler.extensions.RecurrencePreferences
import com.example.viewerrecurrencehandler.extensions.timeMillisToMinutes
import com.example.viewerrecurrencehandler.extensions.visible

@RequiresApi(Build.VERSION_CODES.N)
class MainActivity : AppCompatActivity() {
    private val binding by lazy { inflate(layoutInflater).apply { setContentView(root) } }
    private val viewModel by lazy { ViewModel() }
    private val preferences by lazy { RecurrencePreferences(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.fetchBanner()
        showBannerByRecurrenceTimer()
    }

    private fun showBannerByRecurrenceTimer() =
        viewModel.liveData.observe(this) { recurrenceTimer ->
            verifyHasTimeSaved(recurrenceTimer)
        }

    private fun verifyHasTimeSaved(recurrenceTimer: Int) {
        val lastHourSaved = preferences.getSavedCurrentHour()
        if (lastHourSaved == 0) {
            showBanner()
            preferences.saveCurrentHour()
        } else {
            val passedHour = getSystemCurrentHour() - lastHourSaved
            if (passedHour > recurrenceTimer) {
                showBanner()
                preferences.saveCurrentHour()
            }
        }
    }

    private fun getSystemCurrentHour() = Calendar.getInstance().timeInMillis.timeMillisToMinutes()

    private fun showBanner() = binding.banner.visible()
}