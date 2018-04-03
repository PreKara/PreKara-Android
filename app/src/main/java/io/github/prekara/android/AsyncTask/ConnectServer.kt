package io.github.prekara.android.AsyncTask

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.util.Log
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.core.Response
import io.github.prekara.android.Fragment.Dialog.ProgressDialog
import io.github.prekara.android.HomeActivity
import io.github.prekara.android.Model.Server

/**
 * Created by developer on 4/3/18.
 */

class ConnectServer(
        val activity: Activity,
        val body: List<Pair<String, Any>>
                    ): AsyncTask<String, String, String>() {

    lateinit var mDialog: ProgressDialog

    override fun onPreExecute() {
        super.onPreExecute()
        mDialog = ProgressDialog.getInstance()
        mDialog.show(activity.fragmentManager, "DialogFragment")
    }

    override fun doInBackground(vararg params: String?): String {
        val (request, response, result) = Fuel.post("/server", body).responseJson()
        Log.d("Create", result.get().toString())
        return result.get().toString()
    }

    override fun onProgressUpdate(vararg values: String?) {
        super.onProgressUpdate(*values)
        mDialog.dismiss()
        activity.startActivity( Intent( activity.applicationContext, HomeActivity::class.java ) )
    }
}