package io.github.prekara.android.AsyncTask

import android.app.Activity
import android.content.res.Resources
import android.graphics.Color
import android.os.AsyncTask
import android.widget.Button
import android.widget.EditText
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import io.github.prekara.android.Model.Theme
import io.github.prekara.android.R
import kotlinx.android.synthetic.main.fragment_post_theme.view.*

/**
 * Created by developer on 4/4/18.
 */

class PostTheme(val activity: Activity): AsyncTask<Void, Void, String>() {

    lateinit var etTheme     : EditText
    lateinit var btPostTheme : Button

    lateinit var theme: String

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        btPostTheme = activity.findViewById(R.id.bt_post_theme)
        etTheme     = activity.findViewById(R.id.et_theme)
        theme = etTheme.text.toString()

        btPostTheme.isClickable = false
        btPostTheme.setBackgroundColor( Color.parseColor("#9E9E9E") )
        btPostTheme.setTextColor( Color.parseColor("#FFFFFF"))
    }

    override fun doInBackground(vararg params: Void?): String {
        val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()

        val adapter: JsonAdapter<Theme>? = moshi.adapter(Theme::class.java)
        val json   : String = "/theme".httpPost(listOf("theme" to theme)).responseJson().third.get().obj().toString()

        return adapter!!.fromJson(json).toString()
    }

    override fun onPreExecute() {
        super.onPreExecute()
        btPostTheme.isClickable = true
        btPostTheme.setBackgroundColor( Color.parseColor("#000000") )
        btPostTheme.setTextColor( Color.parseColor("#FFFFFF") )
    }
}