package com.droidper.xtrajob.ui.view

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.droidper.xtrajob.core.navigation.Screen
import com.droidper.xtrajob.ui.view.home.HomeScreen
import com.droidper.xtrajob.ui.view.newworkday.NewDayScreen
import com.droidper.xtrajob.ui.view.recorddays.RecordDaysScreen
import com.droidper.xtrajob.ui.view.workcalendar.WorkCalendarScreen
import com.droidper.xtrajob.ui.theme.AppTheme
import com.droidper.xtrajob.ui.view.newworkday.NewDayScreenViewModel

@Preview(
    device = Devices.PIXEL_4_XL,
    showBackground = true,
    uiMode = UI_MODE_NIGHT_NO,
    name = "HomeScreenPreviewLight",
)
@Composable
fun HomeScreenPreview(){
    AppTheme {
        XtraJobComposeApp()
    }
}

@Composable
fun XtraJobComposeApp (
    navHostController: NavHostController = rememberNavController(),
    newDayViewModel: NewDayScreenViewModel = hiltViewModel()
) {
    val workDayUiState by newDayViewModel.workDayUiState.collectAsState()
    NavHost(
        navController = navHostController,
        startDestination = Screen.Home.route,
    ){

        composable(route = Screen.RecordDays.route) {
            RecordDaysScreen(
                backPressed = {},
                navToNewDay = {}
            )
        }
        composable(route = Screen.Calendar.route) {
            WorkCalendarScreen(
                navToBack = {}
            )
        }
        composable(route = Screen.Home.route) {
            HomeScreen(
                navigateToCalendarScreen = { navHostController.navigate(Screen.Calendar.route) },
                navigateToRecordDayScreen = { navHostController.navigate(Screen.RecordDays.route) },
                navToNewDay = { navHostController.navigate(Screen.NewDay.route) }
            )
        }
        composable(
            route = Screen.NewDay.route
        ) {
            NewDayScreen(
                workDayUiState = workDayUiState,
                changeBreakWorkState = { newDayViewModel.changeBreakWorkState() },
                updateDateStartWorkDay = { newDayViewModel.updateDateStartWorkDay(it) },
                updateDateEndWorkDay = { newDayViewModel.updateDateEndWorkDay(it) },
                updateTimeWorkStart = { hour, minute -> newDayViewModel.updateTimeWorkStart(hour, minute) },
                updateTimeWorkEnd = { hour, minute -> newDayViewModel.updateTimeWorkEnd(hour, minute) },
                setBreakWork = { startHour, startMinute, endHour, endMinute ->
                    newDayViewModel.setBreakWork(startHour, startMinute, endHour, endMinute)
                },
                saveNewWorkDay = { newDayViewModel.saveWorkDay() },
                onPressBack = { navHostController.popBackStack() }
            )
        }
    }
}