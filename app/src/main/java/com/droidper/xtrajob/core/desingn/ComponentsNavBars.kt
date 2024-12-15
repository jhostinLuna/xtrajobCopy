package com.droidper.xtrajob.core.desingn

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.droidper.xtrajob.ui.theme.AppTheme

@Preview(
    uiMode = UI_MODE_NIGHT_NO,
    name = "ComponentsNavBarsLightPreview",
    device = Devices.PIXEL_3A,
    showBackground = true
)
@Preview(
    uiMode = UI_MODE_NIGHT_YES,
    name = "ComponentsNavBarsNightPreview",
    device = Devices.PIXEL_3A,
    showBackground = true
)
@Composable
fun ComponentsNavBars(){
    AppTheme {
        TopAppBarBasic("") {}
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarBasic(
    title: String,
    onclickArrowBack: () -> Unit
) {
    TopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        title = {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = title,
                    textAlign = TextAlign.Center
                )
                },
        navigationIcon = {
            IconButton(
                onClick = onclickArrowBack,
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Button to Back up"
                )
            }
        },
        actions = {
            IconButton(
                onClick = { /*TODO*/ }) {
                Surface(
                    modifier = Modifier
                        .size(24.dp),
                    color = Color.White.copy(0f)
                ) {

                }
            }

        }
    )
}