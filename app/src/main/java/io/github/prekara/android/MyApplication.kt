package io.github.prekara.android

import android.app.Application
import com.github.kittinunf.fuel.core.FuelManager

/**
 * Created by developer on 4/2/18.
 */

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        FuelManager.instance.apply {
            basePath = "https://prekara.mizucoffee.net"
            baseHeaders = mapOf( "Content-Type" to "application/json" )
        }
    }
}