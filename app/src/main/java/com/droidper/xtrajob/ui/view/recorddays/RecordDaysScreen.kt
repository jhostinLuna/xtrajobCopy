package com.droidper.xtrajob.ui.view.recorddays

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.droidper.xtrajob.R
import com.droidper.xtrajob.core.desingn.CardDayRecorded
import com.droidper.xtrajob.core.desingn.HeaderListDaysRecorded
import com.droidper.xtrajob.core.desingn.TopAppBarBasic
import com.droidper.xtrajob.ui.theme.AppTheme
import java.time.LocalDate

@Preview(
    device = Devices.PIXEL_4_XL,
    showBackground = true,
    uiMode = UI_MODE_NIGHT_NO,
    name = "RecordDaysScreenPreviewLight"
)
@Preview(
    device = Devices.PIXEL_4_XL,
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES,
    name = "RecordDaysScreenPreviewDark"
)
@Composable
fun RecordDaysScreenPreview() {
    AppTheme {
        RecordDaysScreen(
            backPressed = {},
            navToNewDay = {}
        )
    }

}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RecordDaysScreen(
    navToNewDay: () -> Unit,
    backPressed: () -> Unit
) {
    Scaffold(
        modifier = Modifier,
        topBar = { TopAppBarBasic(title = stringResource(id = R.string.recorded_days), onclickArrowBack = backPressed)},
        floatingActionButton = {
            IconButton(
                onClick = navToNewDay,
            ) {
                Icon(
                    modifier = Modifier
                        .fillMaxSize(),
                    imageVector = Icons.Filled.AddCircle,
                    contentDescription = "Registrar nuevo dia trabajado o añadir nueva jornada laboral"
                )
            }

        }
    ) {innerPaddingValue->
        HorizontalPager(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPaddingValue),
            pageCount = 1) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 40.dp)
            ){
                Column(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .fillMaxWidth()
                        .height(80.dp),
                    verticalArrangement = Arrangement.SpaceAround,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier,
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBackIos,
                            contentDescription = "resultados de mes anterior"
                        )
                        Text(text = "Octubre 2023")
                        Icon(
                            imageVector = Icons.Filled.ArrowForwardIos,
                            contentDescription = "resultados de mes posterior"
                        )
                    }
                    HeaderListDaysRecorded(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(vertical = 80.dp)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    CardDayRecorded(
                        modifier = Modifier
                            .fillMaxWidth(),
                        date = LocalDate.now(), hoursDay = listOf("6:00","16:00"),
                        hoursBrakingWork = listOf("12:00","13:00"))
                    CardDayRecorded(
                        modifier = Modifier
                            .fillMaxWidth(),
                        date = LocalDate.now(),
                        hoursDay = listOf("6:00","16:00"),
                        hoursBrakingWork = listOf("12:00","13:00"))
                }
            }

        }

    }

}