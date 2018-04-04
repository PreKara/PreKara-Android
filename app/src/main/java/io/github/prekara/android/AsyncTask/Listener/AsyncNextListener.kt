package io.github.prekara.android.AsyncTask.Listener

import io.github.prekara.android.Model.Server

/**
 * Created by developer on 4/4/18.
 */

interface AsyncNextListener {
    fun preExecute()
    fun postExecute()
    fun doInBackground(): String
}