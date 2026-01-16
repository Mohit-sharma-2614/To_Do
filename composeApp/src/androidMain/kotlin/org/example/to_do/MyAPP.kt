package org.example.to_do

import android.app.Application
import org.example.to_do.di.commonModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext

class MyAPP : Application() {
    override fun onCreate() {
        super.onCreate()
        GlobalContext.startKoin {
            androidContext(this@MyAPP)
            modules(commonModule)
        }
    }
}