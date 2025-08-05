package com.droidper.xtrajob.core.navigation

sealed class Screen(val route: String) {
    data object Home : Screen(route = "home")
    data object NewDay : Screen(route = "new_day")
    data object Calendar : Screen(route = "calendar")
    data object RecordDays : Screen(route = "home")
}