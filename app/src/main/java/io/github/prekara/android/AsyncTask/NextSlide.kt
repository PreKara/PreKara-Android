package io.github.prekara.android.AsyncTask

import android.os.AsyncTask
import io.github.prekara.android.AsyncTask.Listener.AsyncNextListener
import io.github.prekara.android.AsyncTask.Listener.AsyncServerListener

/**
 * Created by developer on 4/4/18.
 */

class NextSlide(): AsyncTask<Void, Void, String>() {


    lateinit var mListener: AsyncNextListener

    fun setListener(listener: AsyncNextListener) {
        mListener = listener
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        mListener.postExecute()
    }

    override fun doInBackground(vararg params: Void?): String {
        return mListener.doInBackground()

    }


    override fun onPreExecute() {
        super.onPreExecute()
        mListener.preExecute()
    }
}
