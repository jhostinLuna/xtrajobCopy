package com.droidper.xtrajob.feature.desingn

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Construction
import androidx.compose.material.icons.filled.Euro
import androidx.compose.material.icons.filled.PauseCircle
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.StopCircle
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.droidper.xtrajob.ui.theme.GreenActive
import com.droidper.xtrajob.ui.theme.Green_80
import com.droidper.xtrajob.ui.theme.OrangeRegister
import java.time.LocalDate

val brush = Brush.verticalGradient(listOf(GreenActive,Color.White))
@Composable
fun CardWorkingDay(){
    Card(
        modifier = Modifier
            .width(236.dp)
            .height(124.dp),
        shape = RoundedCornerShape(4.dp)
    ) {
        Box (
            modifier = Modifier
                .fillMaxSize()
                .background(brush)
                ) {
            Row(
                modifier = Modifier
                    .padding(top = 15.dp, end = 15.dp, start = 15.dp)
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                ColumnHourWithIcon(hours = listOf("6:00")){
                    Icon(imageVector = Icons.Filled.PlayCircle, contentDescription = "Icon of play or start")
                }
                ColumnHourWithIcon(hours = listOf("11:00","12:00")){
                    Icon(imageVector = Icons.Filled.PauseCircle, contentDescription = "Icon of play or start")
                }
                ColumnHourWithIcon(hours = listOf("16:00")) {
                    Icon(imageVector = Icons.Filled.StopCircle, contentDescription = "Icon of play or start")
                }
            }
            Text(
                modifier = Modifier
                    .padding(start = 15.dp, bottom = 12.dp)
                    .align(Alignment.BottomStart),
                text = "LUNES",
                style = MaterialTheme.typography.bodyMedium,
                color = OrangeRegister
            )
        }
    }
}
@Composable
fun ColumnHourWithIcon(
    hours: List<String>,
    icon: @Composable () -> Unit
){
    Column(
        modifier = Modifier
            .height(89.dp)
            .width(37.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Surface(
            content = icon,
            color = Color.White.copy(0f)
        )
        WorkBreak(hours = hours)

    }
}
@Composable
fun WorkBreak(hours:List<String>){
    Column(
        modifier = Modifier
            .height(55.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (hours.size > 1) {
            Text(
                text = hours[0],
                style = MaterialTheme.typography.bodyMedium
            )
            Divider(
                modifier = Modifier
                    .height(14.dp)
                    .width(2.dp),
                color = Color.Black
            )
            Text(
                text = hours[1],
                style = MaterialTheme.typography.bodyMedium
                )
        } else {
            Text(
                text = hours[0],
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
@Preview(
    heightDp = 300,
    widthDp = 300,
    showBackground = true
)
@Composable
fun CardWorkingDayPreview() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CardWorkingDay()
    }
}
/***************************Recorded Card Day **********************************/
@Preview
@Composable
fun CardRecordedDayPreview() {
    CardRecordedDay(LocalDate.now())
}
@Composable
fun CardRecordedDay(date: LocalDate) {
    Card(
        modifier = Modifier
            .width(100.dp)
            .height(85.dp),
        shape = RoundedCornerShape(4.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconDay(day = "D")
                Text(
                    text = "10/09/23",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodySmall,
                )
            }
            Spacer(modifier = Modifier.height(5.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ColumnIconWithHour(
                    icon = {
                        Icon(imageVector = Icons.Filled.Construction, contentDescription = "Icon Work")
                    },
                    quantity = "8h"
                )
                ColumnIconWithHour(
                    icon = {
                        Icon(imageVector = Icons.Filled.Euro, contentDescription = "Icon Work")
                    },
                    quantity = "35€"
                )
            }

        }

    }
}
@Composable
fun IconDay(day: String) {
    Surface(
        modifier = Modifier
            .width(21.dp)
            .height(21.dp),
        shape = RoundedCornerShape(4.dp),
        color = OrangeRegister
    ) {
        Text(
            modifier = Modifier,
            text = day,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center

        )
    }
}
@Composable
fun ColumnIconWithHour(
    icon: @Composable () -> Unit,
    quantity: String
){
    Column(
        modifier = Modifier
            .width(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Surface(
            modifier = Modifier,
            content = icon,
            color = Color.White.copy(0f),
            contentColor = Color.DarkGray
        )
        Text(
            text = quantity,
            maxLines = 1,
            overflow = TextOverflow.Clip,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
/************************************* Card Month ****************************/
@Preview
@Composable
fun CardMonthPreview(){
    CardMonth(age = "2023", hours = "8h", money = "")
}
@Composable
fun CardMonth(
    age: String,
    hours: String,
    money: String
) {
    Column(
        modifier = Modifier
            .width(100.dp)
            .height(122.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "2023")
        Card(
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(4.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .height(31.dp)
                        .fillMaxWidth()
                        .background(color = Green_80),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = "Diciembre",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    ColumnIconWithHour(
                        icon = {
                            Icon(
                                imageVector = Icons.Filled.Construction,
                                contentDescription = "Icon of construction"
                            ) },
                        quantity = "8h"
                    )
                    ColumnIconWithHour(
                        icon = {
                            Icon(
                                imageVector = Icons.Filled.Euro,
                                contentDescription = "Icon of construction"
                            ) },
                        quantity = "35€"
                    )
                }

            }


        }
    }
}