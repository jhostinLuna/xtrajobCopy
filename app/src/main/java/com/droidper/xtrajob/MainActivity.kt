package com.droidper.xtrajob

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.droidper.xtrajob.ui.view.XtraJobComposeApp
import com.droidper.xtrajob.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.  onCreate(savedInstanceState)
        setContent {
            AppTheme {
                XtraJobComposeApp()
            }
        }
    }
}
