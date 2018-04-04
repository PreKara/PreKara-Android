package io.github.prekara.android

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.github.kittinunf.fuel.core.FuelManager

/**
 * Created by developer on 4/2/18.
 */

class MyApplication: Application() {

    lateinit var cookie: SharedPreferences
    lateinit var editor: SharedPreferences.Editor

    override fun onCreate() {
        super.onCreate()
        FuelManager.instance.apply {
            basePath = "https://prekara.net/api/v1"
            baseHeaders = mapOf( "Content-Type" to "application/json" )
        }
    }
}