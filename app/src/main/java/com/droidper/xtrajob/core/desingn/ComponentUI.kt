package com.droidper.xtrajob.core.desingn

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.DoNotDisturbOn
import androidx.compose.material.icons.filled.PauseCircle
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.StopCircle
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.constraintlayout.compose.ConstraintLayout
import com.droidper.xtrajob.R
import com.droidper.xtrajob.ui.theme.AppTheme
import com.droidper.xtrajob.ui.theme.blueDE


@Preview(
    uiMode = UI_MODE_NIGHT_NO,
    name = "ComponentsUILight",
    device = Devices.PIXEL_3A,
    showBackground = true
)
@Preview(
    uiMode = UI_MODE_NIGHT_YES,
    name = "ComponentsUINight",
    device = Devices.PIXEL_3A,
    showBackground = true
)
@Composable
fun HeaderHomePreview() {
    AppTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "HeaderHome")
            HeaderHome(
                name = "Alejandro",
                onclickMinus = {},
                onclickMore = {},
                onclickRegister = {}
            )
            Text(text = "HeaderListDaysRecorded")
            HeaderListDaysRecorded()
            Text(text = "RowHourMinute")
            RowHourMinute(
                modifier = Modifier,
                hour = "08",
                minute = "00"
            )
            Text(text = "PointWhite")
            PointWhite()
            Text(text = "BoxHour")
            BoxHour(number = "08")
            Text(text = "BoxHourMedium")
            BoxHourMedium(number = "08")
            Text(text = "ButtonM")
            ButtonM(text = "Registrar") {

            }

        }

    }

}
@Composable
fun HeaderHome(
    name: String,
    onclickMore: () -> Unit,
    onclickMinus: () -> Unit,
    onclickRegister: () -> Unit
) {
    Surface(
        color = MaterialTheme.colorScheme.onBackground,
        contentColor = MaterialTheme.colorScheme.background
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(360.dp)
                .height(244.dp)
                .clip(RoundedCornerShape(4.dp))
        ) {
            val (logo, welcome, hour, buttons, buttonRegister) = createRefs()

            Image(
                modifier = Modifier
                    .constrainAs(logo){
                        top.linkTo(parent.top, 20.dp)
                        start.linkTo(parent.start, margin = 20.dp)
                    },
                painter = painterResource(id = R.mipmap.ic_launcher),
                contentDescription = "Logo",

                )
            Text(
                modifier = Modifier
                    .constrainAs(welcome){
                        top.linkTo(logo.top)
                        bottom.linkTo(logo.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)},
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontSize = 12.sp, fontWeight = FontWeight.SemiBold)){
                        append("${stringResource(id = R.string.welcome)}\n")
                    }
                    withStyle(style = SpanStyle(fontSize = 16.sp,fontWeight = FontWeight.Bold)){
                        append(name)
                    }
                },
                textAlign = TextAlign.Center
            )
            RowHourMinute(modifier = Modifier
                .constrainAs(hour) {
                    top.linkTo(welcome.bottom, margin = 30.dp)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                },
                hour = "08",
                minute = "00")
            ButtonM(
                modifier = Modifier
                    .constrainAs(buttonRegister){
                        top.linkTo(hour.bottom, margin = 20.dp)
                        end.linkTo(hour.end)
                        start.linkTo(hour.start)},
                text = "Registrar") { onclickRegister() }

            Column(
                modifier = Modifier
                    .constrainAs(buttons) {
                        top.linkTo(hour.top)
                        end.linkTo(parent.end, margin = 22.dp)
                        bottom.linkTo(hour.bottom)
                        start.linkTo(hour.end, margin = 20.dp)
                    }
                    .width(40.dp)
            ) {
                IconButton(
                    onClick = onclickMore,
                    colors = IconButtonDefaults.iconButtonColors(contentColor = MaterialTheme.colorScheme.primary)
                ) {
                    Icon(
                        modifier = Modifier
                            .size(40.dp),
                        imageVector = Icons.Filled.AddCircle,
                        contentDescription = "Icon to Add hours",
                    )

                }
                IconButton(
                    onClick = onclickMinus,
                    colors = IconButtonDefaults.iconButtonColors(contentColor = MaterialTheme.colorScheme.primary)
                ) {
                    Icon(
                        modifier = Modifier
                            .size(40.dp),
                        imageVector = Icons.Filled.DoNotDisturbOn,
                        contentDescription = "Icon to Add hours",
                    )

                }
            }
        }
    }

}
@Composable
fun RowHourMinute(
    modifier: Modifier,
    hour: String,
    minute: String
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center

    ) {
        BoxHour(number = hour)
        Spacer(
            modifier = Modifier
                .width(30.dp)
        )
        Column {
            PointWhite()
            Spacer(
                modifier = Modifier
                    .height(20.dp))
            PointWhite()
        }
        Spacer(
            modifier = Modifier
                .width(30.dp)
        )
        BoxHour(number = minute)
    }
}
@Composable
fun PointWhite() {
    val color = MaterialTheme.colorScheme.background
    Canvas(
        modifier = Modifier
            .padding(8.dp),
        onDraw ={
            drawCircle(color = color, radius = 8.dp.toPx())
        })
}
@Composable
fun BoxHour(number: String) {
    val color = MaterialTheme.colorScheme.background
    Surface(
        modifier = Modifier
            .width(62.dp)
            .height(68.dp),
        shape = RoundedCornerShape(4.dp),
        color = Color.Black.copy(0f),
        border = BorderStroke(2.dp, color)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.Center),
                text = number,
                maxLines = 1,
                overflow = TextOverflow.Clip,
                style = MaterialTheme.typography.headlineLarge,
            )
        }

    }
}

@Composable
fun BoxHourMedium(
    number: String,
    onclick: () -> Unit = {}
){
    Surface(
        modifier = Modifier
            .width(41.dp)
            .height(45.dp)
            .clickable { onclick() },
        shape = RoundedCornerShape(4.dp),
        color = Color.Black.copy(0f),
        border = BorderStroke(2.dp, blueDE)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.Center),
                text = number,
                maxLines = 1,
                overflow = TextOverflow.Clip,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = blueDE
            )
        }

    }
}

@Composable
fun HeaderListDaysRecorded(
    modifier: Modifier = Modifier
){
    Surface(
        modifier = modifier
            .defaultMinSize(minWidth = 266.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                modifier = Modifier
                    .weight(15f),
                imageVector = Icons.Filled.CalendarMonth,
                contentDescription = "Columna del dia del mes"
            )
            Text(
                modifier = Modifier
                    .weight(15f),
                text = "Dia",
                maxLines = 1,
                overflow = TextOverflow.Clip,
                textAlign = TextAlign.Center
            )
            Icon(
                modifier = Modifier
                    .weight(33.3f),
                imageVector = Icons.Filled.PlayCircle,
                contentDescription = "Columna hora de inicio de Jornada"
            )
            Icon(
                modifier = Modifier
                    .weight(33.3f),
                imageVector = Icons.Filled.PauseCircle,
                contentDescription = "Columna hora de inicio de Jornada"
            )
            Icon(
                modifier = Modifier
                    .weight(33.3f),
                imageVector = Icons.Filled.StopCircle,
                contentDescription = "Columna hora de inicio de Jornada"
            )
        }
    }

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogTimePicker(
    show: Boolean = false,
    onDismiss: () -> Unit,
    onclickAccept: (minute:Int,hour: Int) -> Unit,
) {
    val timepickerState: TimePickerState = rememberTimePickerState(
        initialHour = 0,
        initialMinute = 0,
        is24Hour = true
    )
    if (show) {
        AlertDialog(
            title = { Text(text = stringResource(id = R.string.select_hour))},
            text = {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 12.dp),
                ){
                    TimePicker(
                        state = timepickerState ,
                    )
                }
            },
            properties = DialogProperties(dismissOnBackPress = true,dismissOnClickOutside = true),
            onDismissRequest = onDismiss,
            confirmButton = { 
                Button(onClick = {
                    onclickAccept(timepickerState.hour, timepickerState.minute)
                },
                    content = {
                    Text(text = stringResource(id = R.string.acept))
                    }
                )
            },
        )

    }

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDialog(
    title: String = "",
    show: Boolean = false,
    onDismiss: () -> Unit,
    onclickAccept: (dateMillisTimestamp: Long) -> Unit,
) {
    val datePickerState: DatePickerState = rememberDatePickerState()
    val dateFormatter = remember{ DatePickerDefaults.dateFormatter()}
    if (show) {
        AlertDialog(
            onDismissRequest = {
                onDismiss()
            },
            confirmButton = {
                Button(onClick = {
                    onclickAccept(datePickerState.selectedDateMillis?:0)
                }) {
                    Text(text = stringResource(id = R.string.acept))
                }
            },
            dismissButton = {
                onDismiss()
            },
            title = { Text(text = title) },
            text = {
                DatePicker(
                    state = datePickerState,
                    title = { Text(text = "Select date") },
                    dateFormatter = dateFormatter
                )
            }
        )
    }

}
@Preview(
    device = Devices.PIXEL_3A
)
@Composable
fun DialogTimePickerPreview () {
    DialogTimePicker(
        show = true,
        onclickAccept = { _, _ ->},
        onDismiss = {}
    )
}

@Composable
fun ChooseTimeBreakWork(
    show: Boolean = false,
    onDismiss: () -> Unit = {},
    onclickAccept: (time: Int, isMinutes: Boolean) -> Unit = {_,_ ->}
) {
    var timeRest by remember { mutableIntStateOf(0) }
    var isMinutes by remember { mutableStateOf(false) }
    if (!show) return
    AlertDialog(
        onDismissRequest = {onDismiss()},
        confirmButton = {
            Button(
                onClick = {
                onclickAccept(timeRest, isMinutes)
            }) {
                Text(text = "Aceptar")
            }

        },
        title = { Text(text = stringResource(R.string.select_time_of_rest)) },
        text = {
            Row {
                TextField(
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.width(50.dp),
                    value = timeRest.toString(),
                    onValueChange = {
                        if (it.isBlank()) {
                            timeRest = 0
                            return@TextField
                        }
                        timeRest = it.toInt()
                    }
                )

                val radioOptions = listOf(stringResource(R.string.hours), stringResource(R.string.minutes))
                val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }
                // Note that Modifier.selectableGroup() is essential to ensure correct accessibility behavior
                Column(Modifier.selectableGroup()) {
                    radioOptions.forEach { text ->
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .height(56.dp)
                                .selectable(
                                    selected = (text == selectedOption),
                                    onClick = { onOptionSelected(text) },
                                    role = Role.RadioButton
                                )
                                .padding(horizontal = 16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            val minutes = stringResource(R.string.minutes)
                            isMinutes = selectedOption == minutes
                            RadioButton(
                                selected = (text == selectedOption),
                                onClick = {
                                    onOptionSelected(text)
                                }
                            )
                            Text(
                                text = text,
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.padding(start = 16.dp)
                            )
                        }
                    }
                }

            }
        }
    )
}

@Composable
fun LoadingOverlay() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            // Fondo semitransparente para simular una capa modal
            .background(Color.Black.copy(alpha = 0.4f))
            // Esto es CRUCIAL: Intercepta todos los clics y evita que pasen
            .clickable(enabled = false) { },
        contentAlignment = Alignment.Center
    ) {
        // El indicador de progreso de Material 3
        CircularProgressIndicator()
    }
}

@Preview(
    device = Devices.PIXEL_3A
)
@Composable
fun PreviewTimeBreakDialog() {
    ChooseTimeBreakWork(
        show = true,
        onDismiss = {},
        onclickAccept = {_, _ ->}
    )
}