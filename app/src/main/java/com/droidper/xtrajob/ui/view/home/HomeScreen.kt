package com.droidper.xtrajob.ui.view.home

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.droidper.xtrajob.R
import com.droidper.xtrajob.core.desingn.CardDayHoursMoney
import com.droidper.xtrajob.core.desingn.CardMonth
import com.droidper.xtrajob.core.desingn.CardWorkingDay
import com.droidper.xtrajob.core.desingn.HeaderHome
import com.droidper.xtrajob.ui.theme.AppTheme
import java.time.LocalDate

@Preview(
    device = Devices.PIXEL_4_XL,
    showBackground = true,
    uiMode = UI_MODE_NIGHT_NO,
    name = "HomeScreenPreviewLight",
)
@Preview(
    device = Devices.PIXEL_4_XL,
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES,
    name = "HomeScreenPreviewDark",
)
@Composable
fun HomeScreenPreview() {
    AppTheme {
        HomeScreen(
            navigateToCalendarScreen = {},
            navigateToRecordDayScreen = {},
            navToNewDay = {}
        )
    }


}
@Composable
fun HomeScreen(
    navigateToCalendarScreen: () -> Unit,
    navigateToRecordDayScreen: () -> Unit,
    navToNewDay: () -> Unit
) {

    Scaffold(
        topBar = {
            HeaderHome(
                name = "Alejandro",
                onclickMore = { /*TODO*/ },
                onclickMinus = { /*TODO*/ }) {
                //Registrar
            }
        },

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
    ) {innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            RowTitleWithContent(
                title = stringResource(id = R.string.title_home_1),
                contentRight = {
                    TextButton(onClick = navigateToCalendarScreen) {
                        Text(
                            text = stringResource(id = R.string.ver_mas),
                            textDecoration = TextDecoration.Underline,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            )
            LazyRow(
                modifier = Modifier
                    .padding(horizontal = 40.dp)
            ) {
                items(items = listOf("")) {
                    CardWorkingDay(hoursDay = listOf("08:00","16:00"), hoursBrakingWork = listOf("13:00","14:00"), day = "Miercoles") {

                    }
                }
            }
            RowTitleWithContent(title = stringResource(id = R.string.title_home_2), topSpacer = 60.dp, bottomSpacer = 25.dp) {

            }
            LazyRow(
                modifier = Modifier
                    .padding(horizontal = 40.dp)
            ) {
                items(items = listOf("")) {
                    CardDayHoursMoney(date = LocalDate.now(), hoursDay = "9h", money = "70€")
                }
            }
            RowTitleWithContent(title = stringResource(id = R.string.title_home_3), topSpacer = 60.dp, bottomSpacer = 25.dp) {

            }
            LazyRow(
                modifier = Modifier
                    .padding(horizontal = 40.dp)
            ) {
                items(items = listOf("")) {
                    CardMonth(age = "2023", hours = "200", month = "Diciembre", money = "1500€") {
                        navigateToRecordDayScreen()
                    }
                }
            }

        }
    }
    /***********/

}
@Composable
fun RowTitleWithContent(
    modifier: Modifier = Modifier,
    title: String,
    topSpacer: Dp = 0.dp,
    bottomSpacer: Dp = 0.dp,
    contentRight: @Composable () -> Unit
){

    Row(
        modifier = modifier
            .padding(top = topSpacer,
                bottom = bottomSpacer,
                start = 40.dp,
                end = 40.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            modifier = Modifier,
            style = MaterialTheme.typography.titleMedium,
            text = title)
        Surface(
            modifier = Modifier,
            content = contentRight,
        )
    }

}
