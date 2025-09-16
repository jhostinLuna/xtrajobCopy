package com.droidper.xtrajob

import android.os.Bundle
import android.os.StrictMode
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.droidper.xtrajob.ui.view.XtraJobComposeApp
import com.droidper.xtrajob.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.  onCreate(savedInstanceState)
        StrictMode.setVmPolicy(
            StrictMode.VmPolicy.Builder()
                .detectActivityLeaks()
                .penaltyLog()
                .build()
        )
        setContent {
            AppTheme {
                XtraJobComposeApp()
            }
        }
    }
}
