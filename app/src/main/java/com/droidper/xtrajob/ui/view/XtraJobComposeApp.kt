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
    navHostController: NavHostController = rememberNavController()
) {
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
            val viewModel = hiltViewModel<NewDayScreenViewModel>()

            val startWorkTimeUiState by viewModel.startWorkTimeUiState.collectAsState()
            val endWorkTimeUiState by viewModel.endWorkTimeUiState.collectAsState()
            val startBreakTimeUiState by viewModel.startBreakTimeUiState.collectAsState()
            val endBreakTimeUiState by viewModel.endBreakTimeUiState.collectAsState()
            val timeBreakWorkUiState by viewModel.timeBreakWorkUiState.collectAsState()
            val isBreakWork by viewModel.isBreakWorkUiState.collectAsState()
            val observationUiState by viewModel.observationUiState.collectAsState()
            val saveWorkDayUiState by viewModel.saveWorkDayUiState.collectAsState()

            NewDayScreen(
                startDateTimeUiState = startWorkTimeUiState,
                endDateTimeUiState = endWorkTimeUiState,
                timeBreakWorkUiState = timeBreakWorkUiState,
                updateTimeBreakWorkUiState = { time, isMinutes ->
                    viewModel.updateTimeBreakWorkUiState(time, isMinutes)
                                             },
                startBreakDateTimeUiState = startBreakTimeUiState,
                endBreakDateTimeUiState = endBreakTimeUiState,
                switchBreakWork = isBreakWork,
                observationUiState = observationUiState,
                saveWorkDayUiState = saveWorkDayUiState,
                changeBreakWorkState = { viewModel.changeBreakWorkState() },
                updateDateStartWorkDay = { viewModel.updateDateStartWorkDay(it) },
                updateDateEndWorkDay = { viewModel.updateDateEndWorkDay(it) },
                updateTimeWorkStart = { hour, minute -> viewModel.updateStartWorkTime(hour, minute) },
                updateTimeWorkEnd = { hour, minute -> viewModel.updateEndWorkTime(hour, minute) },
                setBreakWork = { startHour, startMinute ->
                    viewModel.updateStartBreakTimeUiState(startHour, startMinute)
                },
                updateObservationUiState = {viewModel.updateObservationUiState(it)},
                saveNewWorkDay = { viewModel.saveWorkDay() },
                onPressBack = { navHostController.popBackStack() }
            )
        }
    }
}