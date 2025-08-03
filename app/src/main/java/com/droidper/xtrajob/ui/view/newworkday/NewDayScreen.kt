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
import androidx.compose.runtime.collectAsState
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.droidper.xtrajob.R
import com.droidper.xtrajob.core.common.PreviewViewModelFactory
import com.droidper.xtrajob.core.desingn.BoxHourMedium
import com.droidper.xtrajob.core.desingn.DialogTimePicker
import com.droidper.xtrajob.core.desingn.RowHourMinute
import com.droidper.xtrajob.core.desingn.TopAppBarBasic
import com.droidper.xtrajob.core.desingn.WorkBreak
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
        NewDayScreen(
            viewModelFactory = PreviewViewModelFactory(),
            onPressBack = {}
        )
    }

}
@Composable
fun NewDayScreen(
    viewModelFactory: ViewModelProvider.Factory? = null,
    onPressBack: () -> Unit
){
    val viewmodel = if (viewModelFactory != null) {
        viewModel<NewDayScreenViewModel>(factory = viewModelFactory)
    } else {
        // En producción, seguimos usando hiltViewModel
        hiltViewModel()
    }
    val initTimerPickerUiState by viewmodel.timeInitPickerUiState.collectAsState()
    val finTimerPickerUiState by viewmodel.timeFinPickerUiState.collectAsState()
    Scaffold(
        topBar = {
            TopAppBarBasic(
                title = stringResource(id = R.string.new_day_screen),
                onclickArrowBack = onPressBack
            )
        },
        floatingActionButton = {
            IconButton(onClick = {
            /*TODO*/
            }) {
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

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPaddingValue),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            RowTitleWithContent(title = stringResource(id = R.string.newday_title1), topSpacer = 10.dp, bottomSpacer = 10.dp) {

            }
            // Hora Inicio

            Surface(
                modifier = Modifier
                    .width(262.dp)
                    .height(134.dp),
                shape = RoundedCornerShape(4.dp),
                color = MaterialTheme.colorScheme.inversePrimary,
                shadowElevation = 4.dp
            ) {
                var showTimePicker by remember {
                    mutableStateOf(false)
                }
                RowHourMinute(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable { showTimePicker = true },
                    hour = initTimerPickerUiState.timerPickerModel.hour,
                    minute = initTimerPickerUiState.timerPickerModel.hour
                )
                DialogTimePicker(
                    show = showTimePicker,
                    onclickAccept = {hour, minute ->

                        viewmodel.updateTimeInit(hour = hour, minute = minute)
                        showTimePicker = false
                                    },
                    onDismiss = {showTimePicker = false}
                )
            }
            Spacer(modifier = Modifier.height(50.dp))

            //Hora Fin
            RowTitleWithContent(title = stringResource(id = R.string.newday_title2), topSpacer = 10.dp, bottomSpacer = 10.dp) {

            }
            Surface(
                modifier = Modifier
                    .width(262.dp)
                    .height(134.dp),
                shape = RoundedCornerShape(4.dp),
                color = MaterialTheme.colorScheme.tertiaryContainer,
                shadowElevation = 4.dp
            ) {
                var showTimePicker by remember {
                    mutableStateOf(false)
                }
                RowHourMinute(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable { showTimePicker = true },
                    hour = finTimerPickerUiState.timerPickerModel.hour,
                    minute = finTimerPickerUiState.timerPickerModel.minute
                )
                DialogTimePicker(
                    show = showTimePicker,
                    onclickAccept = {hour,minute ->
                        viewmodel.updateTimeFin(hour = hour, minute = minute)
                        showTimePicker = false},
                    onDismiss = {showTimePicker = false}
                )

            }
            Spacer(modifier = Modifier.height(50.dp))
            var switchBreakWorkState by remember {
                mutableStateOf(false)
            }
            var showDialogTime by remember {
                mutableStateOf(false)
            }
            RowTitleWithContent(title = stringResource(id = R.string.split_work), topSpacer = 10.dp, bottomSpacer = 10.dp) {
                Switch(checked = switchBreakWorkState, onCheckedChange = {switchBreakWorkState = !switchBreakWorkState})
            }

            if (switchBreakWorkState) {
                DialogTimePicker(
                    show = showDialogTime,
                    onclickAccept = { hour, minute ->
                        viewmodel.updateTimeBreak(hour = hour, minute = minute)
                        showDialogTime = false
                    },
                    onDismiss = {showDialogTime = false}
                )
            }

            var observationState by remember {
                mutableStateOf("")
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ){
                WorkBreak(
                    modifier = Modifier
                        .clickable { showDialogTime = true },
                    hours = listOf("12","18")
                )
                BoxHourMedium(number = "0h")
            }
            BasicTextField(
                modifier = Modifier
                    .padding(horizontal = 40.dp)
                    .fillMaxWidth()
                    .height(136.dp),
                value = observationState,
                onValueChange = { if (it.length < 256) observationState = it},
                maxLines = 5,
                decorationBox = {
                    Surface(
                        modifier = Modifier
                            .padding(8 .dp),
                        border = BorderStroke(2.dp, Color.Black),
                        shape = RoundedCornerShape(4.dp),
                        shadowElevation = 4.dp
                    ) {
                        if (observationState.isEmpty()) {
                            Text(
                                text = stringResource(id = R.string.observations),
                                color = Color.Gray
                            )
                        }else {
                            Text(text = observationState)
                        }
                        
                    }
                }
            )
        }
    }



}
