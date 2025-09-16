package com.droidper.xtrajob.ui.view.newworkday

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.droidper.xtrajob.R
import com.droidper.xtrajob.core.desingn.BoxHourMedium
import com.droidper.xtrajob.core.desingn.ChooseTimeBreakWork
import com.droidper.xtrajob.core.desingn.DatePickerDialog
import com.droidper.xtrajob.core.desingn.DialogTimePicker
import com.droidper.xtrajob.core.desingn.LoadingOverlay
import com.droidper.xtrajob.core.desingn.RowHourMinute
import com.droidper.xtrajob.core.desingn.TopAppBarBasic
import com.droidper.xtrajob.core.desingn.WorkBreak
import com.droidper.xtrajob.core.extensions.formattedSpanish
import com.droidper.xtrajob.core.extensions.withZero
import com.droidper.xtrajob.ui.model.DateTimeUiState
import com.droidper.xtrajob.ui.model.SaveStatusUiState
import com.droidper.xtrajob.ui.model.TimeBreakWorkUiState
import com.droidper.xtrajob.ui.view.home.RowTitleWithContent
import com.droidper.xtrajob.ui.theme.AppTheme

/**
 * @author Jhostin Luna
 */
@Preview(
    device = Devices.PIXEL_4_XL,
    showBackground = true,
    uiMode = UI_MODE_NIGHT_NO,
    name = "NewDayScreenLight",
)
@Preview(
    device = Devices.PIXEL_4_XL,
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES,
    name = "NewDayScreenDark",
)
@Composable
fun NewDayScreenPreview(){
    AppTheme {
        NewDayScreen()
    }

}
@Composable
fun NewDayScreen(
    startDateTimeUiState: DateTimeUiState = DateTimeUiState(),
    endDateTimeUiState: DateTimeUiState = DateTimeUiState(),
    startBreakDateTimeUiState: DateTimeUiState = DateTimeUiState(),
    timeBreakWorkUiState: TimeBreakWorkUiState = TimeBreakWorkUiState(),
    updateTimeBreakWorkUiState: (Int, Boolean) -> Unit = {_,_ ->},
    endBreakDateTimeUiState: DateTimeUiState = DateTimeUiState(),
    switchBreakWork: Boolean = false,
    observationUiState: String = "",
    saveWorkDayUiState: SaveStatusUiState = SaveStatusUiState(),
    changeBreakWorkState: () -> Unit = {},
    updateDateStartWorkDay: (Long) -> Unit = {},
    updateDateEndWorkDay: (Long) -> Unit = {},
    updateTimeWorkStart: (Int, Int) -> Unit = { _,_ -> },
    updateTimeWorkEnd: (Int, Int) -> Unit = {_,_ ->},
    setBreakWork: (Int, Int) -> Unit = {_,_ ->},
    updateObservationUiState: (String) -> Unit = {_ ->},
    saveNewWorkDay: () -> Unit = {},
    onPressBack: () -> Unit = {}
){

    Scaffold(
        topBar = {
            TopAppBarBasic(
                title = stringResource(id = R.string.new_day_screen),
                onclickArrowBack = onPressBack
            )
        },
        floatingActionButton = {
            IconButton(onClick = {
            saveNewWorkDay()
            },
                enabled = !saveWorkDayUiState.isLoading
            ) {
                Surface(
                    modifier = Modifier,
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.tertiaryContainer
                ) {
                    Icon(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(4.dp),
                        imageVector = Icons.Filled.Save,
                        contentDescription = "Guardar nueva jornada"
                    )
                }

            }
        }
    ) {innerPaddingValue->

        var showStartDatePicker by remember {
            mutableStateOf(false)
        }
        DatePickerDialog(
            title = stringResource(id = R.string.text_date_start_day),
            show = showStartDatePicker,
            onDismiss = {  },
        ) { timestamp->
            updateDateStartWorkDay(timestamp)
            showStartDatePicker = false
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPaddingValue),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            RowTitleWithContent(
                title = stringResource(id = R.string.newday_title1),
                topSpacer = 10.dp,
                bottomSpacer = 10.dp,
                contentRight = {
                    Text(
                        modifier = Modifier
                            .clickable {
                                showStartDatePicker = true
                            },
                        text = startDateTimeUiState.dateTime.toLocalDate().formattedSpanish()
                    )
                }
            )
            // Hora Inicio

            Surface(
                modifier = Modifier
                    .width(262.dp)
                    .height(134.dp),
                shape = RoundedCornerShape(4.dp),
                color = MaterialTheme.colorScheme.inversePrimary,
                shadowElevation = 4.dp
            ) {
                var showStartTimePicker by remember {
                    mutableStateOf(false)
                }
                RowHourMinute(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable { showStartTimePicker = true },
                    hour = startDateTimeUiState.dateTime.hour.withZero(),
                    minute = startDateTimeUiState.dateTime.minute.withZero()
                )
                DialogTimePicker(
                    show = showStartTimePicker,
                    onclickAccept = {hour, minute ->

                        updateTimeWorkStart(hour, minute)
                        showStartTimePicker = false
                                    },
                    onDismiss = {showStartTimePicker = false}
                )
            }
            Spacer(modifier = Modifier.height(50.dp))

            //Hora Fin
            var showEndDatePicker by remember {
                mutableStateOf(false)
            }
            DatePickerDialog(
                title = stringResource(id = R.string.text_date_end_day),
                show = showEndDatePicker,
                onDismiss = {  },
            ) {timestamp->
                updateDateEndWorkDay(timestamp)
                showEndDatePicker = false
            }
            RowTitleWithContent(
                title = stringResource(id = R.string.newday_title2),
                topSpacer = 10.dp,
                bottomSpacer = 10.dp
            ) {
                Text(
                    modifier = Modifier.clickable {
                        showEndDatePicker = true
                    },
                    text = endDateTimeUiState.dateTime.toLocalDate().formattedSpanish()
                )
            }

            Surface(
                modifier = Modifier
                    .width(262.dp)
                    .height(134.dp),
                shape = RoundedCornerShape(4.dp),
                color = MaterialTheme.colorScheme.tertiaryContainer,
                shadowElevation = 4.dp
            ) {
                var showEndTimePicker by remember {
                    mutableStateOf(false)
                }
                RowHourMinute(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable { showEndTimePicker = true },
                    hour = endDateTimeUiState.dateTime.hour.withZero(),
                    minute = endDateTimeUiState.dateTime.minute.withZero()
                )
                DialogTimePicker(
                    show = showEndTimePicker,
                    onclickAccept = {hour,minute ->
                        updateTimeWorkEnd(hour, minute)
                        showEndTimePicker = false},
                    onDismiss = {showEndTimePicker = false}
                )

            }
            Spacer(modifier = Modifier.height(50.dp))

            var showStartBreakTimePicker by remember {
                mutableStateOf(false)
            }

            RowTitleWithContent(
                title = stringResource(id = R.string.split_work),
                topSpacer = 10.dp,
                bottomSpacer = 10.dp
            ) {
                Switch(checked = switchBreakWork, onCheckedChange = {
                    changeBreakWorkState()
                    showStartBreakTimePicker = it
                })
            }

            DialogTimePicker(
                show = showStartBreakTimePicker,
                onDismiss = { },
                onclickAccept = { hour, minute ->
                    setBreakWork(hour, minute)
                    showStartBreakTimePicker = false
                },
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ){
                WorkBreak(

                    startBreakWorkTime = "${startBreakDateTimeUiState.dateTime.hour.withZero()}:${startBreakDateTimeUiState.dateTime.minute.withZero()}",
                    endBreakWorkTime = "${endBreakDateTimeUiState.dateTime.hour.withZero()}:${endBreakDateTimeUiState.dateTime.minute.withZero()}"
                )
                var showTimeBreakWork by remember {
                    mutableStateOf(false)
                }
                BoxHourMedium(
                    number = timeBreakWorkUiState.timeBreakWork.toString(),
                ) {
                    showTimeBreakWork = true
                }
                ChooseTimeBreakWork(
                    show = showTimeBreakWork,
                    onDismiss = { showTimeBreakWork = false },
                    onclickAccept = { time, isMinutes ->
                        updateTimeBreakWorkUiState(time,isMinutes)
                        showTimeBreakWork = false
                    }
                )
            }
            BasicTextField(
                modifier = Modifier
                    .padding(horizontal = 40.dp)
                    .fillMaxWidth()
                    .height(136.dp),
                value = observationUiState,
                onValueChange = { if (it.length < 256) updateObservationUiState(it) },
                maxLines = 5,
                decorationBox = {
                    Surface(
                        modifier = Modifier
                            .padding(8 .dp),
                        border = BorderStroke(2.dp, Color.Black),
                        shape = RoundedCornerShape(4.dp),
                        shadowElevation = 4.dp
                    ) {
                        if (observationUiState.isEmpty()) {
                            Text(
                                text = stringResource(id = R.string.observations),
                                color = Color.Gray
                            )
                        }else {
                            Text(text = observationUiState)
                        }
                        
                    }
                }
            )
        }

        if (saveWorkDayUiState.isLoading) {
            LoadingOverlay()
        }
    }



}
