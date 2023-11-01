package com.droidper.xtrajob.feature.newworkday

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
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
import com.droidper.xtrajob.core.desingn.RowHourMinute
import com.droidper.xtrajob.core.desingn.TopAppBarBasic
import com.droidper.xtrajob.feature.home.RowTitleWithContent
import com.droidper.xtrajob.ui.theme.AppTheme

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
            onPressBack = {}
        )
    }

}
@Composable
fun NewDayScreen(
    onPressBack: () -> Unit
){
    Scaffold(
        topBar = {
            TopAppBarBasic(
                title = stringResource(id = R.string.new_day_screen),
                onclickArrowBack = onPressBack
            )
        },
        floatingActionButton = {
            IconButton(onClick = { /*TODO*/ }) {
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
            Surface(
                modifier = Modifier
                    .width(262.dp)
                    .height(134.dp),
                shape = RoundedCornerShape(4.dp),
                color = MaterialTheme.colorScheme.inversePrimary,
                shadowElevation = 4.dp
            ) {
                RowHourMinute(
                    modifier = Modifier
                        .fillMaxSize(),
                    hour = "08",
                    minute = "00"
                )
            }
            Spacer(modifier = Modifier.height(50.dp))
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
                RowHourMinute(
                    modifier = Modifier
                        .fillMaxSize(),
                    hour = "08",
                    minute = "00"
                )

            }
            Spacer(modifier = Modifier.height(50.dp))
            var switchBreakWorkState by remember {
                mutableStateOf(false)
            }
            RowTitleWithContent(title = stringResource(id = R.string.newday_title3), topSpacer = 10.dp, bottomSpacer = 10.dp) {
                Switch(checked = switchBreakWorkState, onCheckedChange = {switchBreakWorkState = !switchBreakWorkState})
            }
            var observationState by remember {
                mutableStateOf("")
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