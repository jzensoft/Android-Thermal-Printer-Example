package com.weha.printerexternal

import android.app.Application
import com.weha.epsonthermal.EpsonThermal

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        EpsonThermal.instance.initPrinter(this)
    }
}