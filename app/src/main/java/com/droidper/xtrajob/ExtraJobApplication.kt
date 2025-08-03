package com.droidper.xtrajob

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ExtraJobApplication: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}