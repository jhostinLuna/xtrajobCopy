package com.droidper.xtrajob.core.navigation

import androidx.annotation.StringRes
import com.droidper.xtrajob.R


sealed class Screen(val route: String, @StringRes val resourceId: Int) {
    data object Home : Screen(route = "home", R.string.home_screen)
    data object NewDay : Screen(route = "new_day", R.string.new_day_screen)
    data object Calendar : Screen(route = "calendar", R.string.calendar_screen)
    data object RecordDays : Screen(route = "home", R.string.home_screen)
}