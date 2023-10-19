package com.droidper.xtrajob.feature.desingn

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PauseCircle
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.StopCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.droidper.xtrajob.ui.theme.GreenActive
import com.droidper.xtrajob.ui.theme.OrangeRegister

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
                columnHourWithIcon(hours = listOf("6:00")){
                    Icon(imageVector = Icons.Filled.PlayCircle, contentDescription = "Icon of play or start")
                }
                columnHourWithIcon(hours = listOf("11:00","12:00")){
                    Icon(imageVector = Icons.Filled.PauseCircle, contentDescription = "Icon of play or start")
                }
                columnHourWithIcon(hours = listOf("16:00")) {
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
fun columnHourWithIcon(
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
    widthDp = 300
)
@Composable
fun CardWorkingDayPreview() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CardWorkingDay()
    }
}