package com.diegocunha.marvelheroguide

import android.app.Application
import com.diegocunha.marvelheroguide.dependency.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MarvelHeroGuideApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MarvelHeroGuideApplication)
            modules(appModule)
        }
    }
}