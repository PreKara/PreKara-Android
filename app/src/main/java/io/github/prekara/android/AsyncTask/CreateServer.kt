package io.github.prekara.android.AsyncTask

import android.os.AsyncTask
import io.github.prekara.android.Model.Server


/**
 * Created by developer on 4/4/18.
 */

class CreateServer(val server: Server): AsyncTask<Void, Void, String>() {


    lateinit var mListener: AsyncListener

    fun setListener(listener: AsyncListener) {
        mListener = listener
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        mListener.postExecute()
    }

    override fun doInBackground(vararg params: Void?): String {
        return mListener.doInBackground(server)

    }


    override fun onPreExecute() {
        super.onPreExecute()
        mListener.preExecute()
    }
}