package com.droidper.xtrajob.ui.view.workcalendar

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.droidper.xtrajob.R
import com.droidper.xtrajob.core.desingn.CardWorkingDay
import com.droidper.xtrajob.core.desingn.TopAppBarBasic
import com.droidper.xtrajob.ui.theme.AppTheme

@Preview(
    device = Devices.PIXEL_4_XL,
    showBackground = true,
    uiMode = UI_MODE_NIGHT_NO,
    name = "WorkCalendarScreenPreviewLight"
)
@Preview(
    device = Devices.PIXEL_4_XL,
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES,
    name = "WorkCalendarScreenPreviewDark"
)
@Composable
fun WorkCalendarScreenPreview(){
    AppTheme {
        WorkCalendarScreen(
            navToBack = {},
            navToNewDay = {}
        )
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkCalendarScreen(
    navToBack: () -> Unit,
    navToNewDay: () -> Unit
) {
    Scaffold(
        modifier = Modifier,
        topBar = { TopAppBarBasic(
            title = stringResource(id = R.string.work_calendar),
            onclickArrowBack = navToBack
        )},
        floatingActionButton = {
            IconButton(
                onClick = navToNewDay,
            ) {
                Icon(
                    modifier = Modifier
                        .fillMaxSize(),
                    imageVector = Icons.Filled.AddCircle,
                    contentDescription = "Button for add new recorded work"
                )
            }
        }
    ) {innerPaddingValue->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPaddingValue)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Pre-select a date for January 4, 2020
            val datePickerState = rememberDatePickerState(initialSelectedDateMillis = 1578096000000)
            DatePicker(state = datePickerState, modifier = Modifier.padding(16.dp))
            Spacer(
                modifier = Modifier
                    .height(30.dp)
            )
            CardWorkingDay(
                hoursDay = listOf("6:00","16:00"),
                hoursBrakingWork = listOf("12:00","13:00"),
                day = "LUNES",
            ) {
                
            }
        }
    }

}
