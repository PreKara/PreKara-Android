package io.github.prekara.android.AsyncTask

import android.app.Activity
import android.os.AsyncTask

/**
 * Created by developer on 4/4/18.
 */
class PostImage(val activity: Activity): AsyncTask<Void, Void, String>() {

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
    }

    override fun doInBackground(vararg params: Void?): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onPreExecute() {
        super.onPreExecute()
    }
}
