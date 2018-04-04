package io.github.prekara.android.AsyncTask

import io.github.prekara.android.Model.Server

/**
 * Created by developer on 4/4/18.
 */
interface AsyncListener {
    fun preExecute()
    fun postExecute()
    fun doInBackground(server: Server): String
}