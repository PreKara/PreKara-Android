package io.github.prekara.android.Fragment.HomeTab


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import io.github.prekara.android.AsyncTask.Listener.AsyncNextListener
import io.github.prekara.android.AsyncTask.NextSlide

import io.github.prekara.android.R
import kotlinx.android.synthetic.main.fragment_presenter.*
import java.net.URL
import kotlin.concurrent.thread


/**
 * A simple [Fragment] subclass.
 */
class PresenterFragment : Fragment() {

    private var isTimeUp        = false
    private var isSlideFinished = true

    private lateinit var cookie: SharedPreferences

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        cookie = activity.getSharedPreferences("cookie", Context.MODE_PRIVATE)
        return inflater!!.inflate(R.layout.fragment_presenter, container, false)
    }

    override fun onStart() {
        super.onStart()

        if (!isSlideFinished) bt_next.text = "次のプレゼンターへ"

        bt_next.setOnClickListener {

            val nextSlide = NextSlide()
            nextSlide.setListener(object : AsyncNextListener {
                override fun preExecute() {
                    // Nothing
                }

                override fun postExecute() {

                }

                override fun doInBackground(): String {
                    if (isTimeUp) {
                        "/control/new".httpGet()
                                .header("Content-Type" to "application/json")
                                .header("Cookie" to cookie.getString("cookie", ""))
                                .responseJson { request, response, result ->
                                    when (result) {
                                        is Result.Success -> {
                                            // Success
                                            Log.d("NextPresenter", "${result.value}")
                                        }
                                        is Result.Failure -> {
                                            // Error
                                            Log.e("Error at Presenter", result.getException().toString())
                                        }
                                    }
                                }
                    } else {
                        if (isSlideFinished) {
                            "/control/new".httpGet()
                                    .header("Content-Type" to "application/json")
                                    .header("Cookie" to cookie.getString("cookie", ""))
                                    .response()
                            isSlideFinished = false
                            return ""
                        }

                        var triple = "/control/next".httpGet()
                                .header("Content-Type" to "application/json")
                                .header("Cookie" to cookie.getString("cookie", ""))
                                .responseJson()
                        when (triple.third) {
                            is Result.Success -> {
                                Log.d("NextSlide", triple.third.toString())
                            }


                        }
                    }
//                                .responseJson { request, response, result ->
//                                    when (result) {
//                                        is Result.Success -> {
//                                            // Success
//                                            Log.d("NextSlide", "${result.value}")
//                                        }
//                                        is Result.Failure -> {
//                                            // Error
//                                            result.getException().printStackTrace()
////                                            Log.e("Error", result.getException().toString())
//                                        }
//                                    }


//                }
                    return ""
            }

        })
            nextSlide.execute()
        }

    }
}// Required empty public constructor
