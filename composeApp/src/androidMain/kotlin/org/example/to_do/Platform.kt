package org.example.to_do

import android.app.Application
import android.os.Build
import org.example.to_do.di.commonModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext

class AndroidPlatform {
    val name: String = "Android ${Build.VERSION.SDK_INT}"
}

fun getPlatform() = AndroidPlatform()
