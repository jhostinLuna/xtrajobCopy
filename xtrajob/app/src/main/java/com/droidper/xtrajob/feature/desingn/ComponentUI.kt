package com.droidper.xtrajob.feature.desingn

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.droidper.xtrajob.R
import com.droidper.xtrajob.ui.theme.Black90

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

        Icon(
            modifier = Modifier
                .constrainAs(logo){
                    top.linkTo(parent.top, 20.dp)
                    start.linkTo(parent.start, margin = 20.dp)
                },
            painter = painterResource(id = R.mipmap.ic_launcher),
            contentDescription = "Logo",
            tint = Color.White

        )
    }
}
@Preview(
    heightDp = 360,
    widthDp = 300,
    showBackground = true
)
@Composable
fun HeaderHomePreview() {
    HeaderHome()
}