package io.github.prekara.android.Fragment.HomeTab


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import io.github.prekara.android.R
import kotlinx.android.synthetic.main.fragment_presenter.*


/**
 * A simple [Fragment] subclass.
 */
class PresenterFragment : Fragment() {

    private var isTimeUp = false
    private var isSlideFinished = true

    private lateinit var cookie: SharedPreferences

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        cookie = activity.getSharedPreferences("cookie", Context.MODE_PRIVATE)
        return inflater!!.inflate(R.layout.fragment_presenter, container, false)
    }

    var new = true;

    var cookieStr = ""
    override fun onStart() {
        super.onStart()

        cookieStr = cookie.getString("cookie", "").replace("[", "").replace("]", "")
//        if (!isSlideFinished) bt_next.text =_ "次のプレゼンターへ"

        bt_next.setOnClickListener {
            if(new) {
                "/control/new".httpGet().header("Content-Type" to "application/json").header("cookie" to cookieStr).responseJson { request, response, result ->
                    when (result) {
                        is Result.Success -> {
                            Log.d("Success", result.value.content)
                            Toast.makeText(activity, "success", Toast.LENGTH_SHORT).show()
                            bt_next.text = "次のスライドへ"
                            new = false;
                        }
                        is Result.Failure -> {
                            Log.d("Header", request.headers.toString())
                            result.getException().printStackTrace()
                        }
                    }
                }
            }else {
                "/control/next".httpGet().header("Content-Type" to "application/json").header("cookie" to cookieStr).responseJson { request, response, result ->
                    when (result) {
                        is Result.Success -> {
                            Log.d("Success", result.value.content)
                            Toast.makeText(activity, "success", Toast.LENGTH_SHORT).show()
                        }
                        is Result.Failure -> {
                            if (result.error.response.statusCode == 405) {

                                Toast.makeText(activity, "finished", Toast.LENGTH_SHORT).show()
                                bt_next.text = "次のプレゼンターへ"
                                new = true;
                            }

                            Log.d("Header", request.headers.toString())
                            result.getException().printStackTrace()
                        }
                    }
                }
            }
        }
    }// Required empty public constructor

//    val new = View.OnClickListener{
}
