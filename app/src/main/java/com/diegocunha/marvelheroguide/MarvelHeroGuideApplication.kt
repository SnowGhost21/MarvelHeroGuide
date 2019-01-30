package com.diegocunha.marvelheroguide

import android.app.Application
import com.diegocunha.marvelheroguide.dependency.appModule
import org.koin.android.ext.android.startKoin

class MarvelHeroGuideApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(appModule))
    }
}