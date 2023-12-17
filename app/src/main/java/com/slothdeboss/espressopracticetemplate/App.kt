package com.slothdeboss.espressopracticetemplate

import android.app.Application
import com.slothdeboss.espressopracticetemplate.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(appModules)
        }
    }
}