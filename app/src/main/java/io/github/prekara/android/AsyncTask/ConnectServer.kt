package io.github.prekara.android.AsyncTask

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.util.Log
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.core.Response
import io.github.prekara.android.AsyncTask.Listener.AsyncServerListener
import io.github.prekara.android.Fragment.Dialog.ProgressDialog
import io.github.prekara.android.HomeActivity
import io.github.prekara.android.Model.Server

/**
 * Created by developer on 4/3/18.
 */

class ConnectServer(
        val server: Server
): AsyncTask<String, String, String>() {

    private lateinit var mListener: AsyncServerListener

    fun setListener(listener: AsyncServerListener) {
        mListener = listener
    }


    override fun onPreExecute() {
        super.onPreExecute()
        mListener.preExecute()
    }

    override fun doInBackground(vararg params: String?): String {
        return mListener.doInBackground(server)
    }

    override fun onProgressUpdate(vararg values: String?) {
        super.onProgressUpdate(*values)
        mListener.postExecute()
    }
}