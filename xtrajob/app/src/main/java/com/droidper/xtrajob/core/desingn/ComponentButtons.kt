package com.droidper.xtrajob.core.desingn

import android.content.res.Configuration
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.droidper.xtrajob.R
import com.droidper.xtrajob.ui.theme.AppTheme

@Preview(
    device = Devices.PIXEL_3A,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "ButtonsPreviewNight"
)
@Preview(
    device = Devices.PIXEL_3A,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "ButtonsPreviewLight"
)
@Composable
fun ButtonsPreview(){
    AppTheme {
        ButtonM(text = stringResource(id = R.string.register)) {

        }
    }

}
@Composable
fun ButtonM(
    modifier: Modifier = Modifier,
    text: String,
    onclick: () -> Unit
){
    Button(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 22.dp, vertical = 6.dp),
        onClick = onclick
    ) {
        Text(
            text = text,
            maxLines = 1,
        )
    }
}