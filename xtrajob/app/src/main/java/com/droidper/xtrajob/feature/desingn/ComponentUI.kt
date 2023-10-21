package com.droidper.xtrajob.feature.desingn

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.droidper.xtrajob.R
import com.droidper.xtrajob.ui.theme.Black90


@Preview(
    showBackground = true
)
@Composable
fun HeaderHomePreview() {
    HeaderHome()
}
@Composable
fun HeaderHome() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(360.dp)
            .height(244.dp)
            .background(Black90, RoundedCornerShape(4))
    ) {
        val (logo, welcome, hourWithButtons) = createRefs()

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
                withStyle(style = SpanStyle(color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)){
                    append("${stringResource(id = R.string.welcome)}\n")
                }
                withStyle(style = SpanStyle(color = Color.White, fontSize = 16.sp,fontWeight = FontWeight.Bold)){
                    append("Jhostin")
                }
            },
            textAlign = TextAlign.Center,
            color = Color.White
        )
        Row(
            modifier = Modifier
                .constrainAs(hourWithButtons) {
                    top.linkTo(welcome.bottom, margin = 30.dp)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                }
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                BoxHour(hour = "08")
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
                BoxHour(hour = "00")

            }
        }
    }
}
@Composable
fun PointWhite() {
    Canvas(
        modifier = Modifier
            .padding(8.dp),
        onDraw ={
            drawCircle(Color.White, radius = 8.dp.toPx())
        })
}
@Composable
fun BoxHour(hour: String) {
    Surface(
        modifier = Modifier
            .width(62.dp)
            .height(68.dp),
        shape = RoundedCornerShape(4.dp),
        color = Color.Black.copy(0f),
        border = BorderStroke(2.dp, Color.White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.Center),
                text = hour,
                maxLines = 1,
                overflow = TextOverflow.Clip,
                style = MaterialTheme.typography.headlineLarge,
                color = Color.White,
            )
        }

    }
}