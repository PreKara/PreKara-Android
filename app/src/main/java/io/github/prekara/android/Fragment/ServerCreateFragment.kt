package io.github.prekara.android.Fragment

import android.app.Fragment
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.kittinunf.fuel.Fuel
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import io.github.prekara.android.AsyncTask.Listener.AsyncServerListener
import io.github.prekara.android.AsyncTask.CreateServer
import io.github.prekara.android.HomeActivity
import io.github.prekara.android.Model.Server
import io.github.prekara.android.R
import kotlinx.android.synthetic.main.fragment_server_info.*

/**
 * Created by developer on 4/3/18.
 */
class ServerCreateFragment : Fragment() {


    companion object {
        fun getInstance(): ServerCreateFragment {
            Log.d("Fragment", "ServerCreateFragment")
            return ServerCreateFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        Log.d("onCreateView", "start")
        Log.d("onCreateView", "finish")
        return inflater!!.inflate(R.layout.fragment_server_info, container, false)
    }

    override fun onStart() {
        super.onStart()

        Log.d("onStart", "start")
        bt_submit.text = "create"

        bt_submit.setOnClickListener {
            val createServer = CreateServer(
                    Server(
                            et_server_name.text.toString(),
                            et_server_password.text.toString()
                    )
            )

            createServer.setListener( object : AsyncServerListener {
                override fun doInBackground(server: Server): String {
                    val moshi = Moshi.Builder()
                            .add(KotlinJsonAdapterFactory())
                            .build()

                    val adapter: JsonAdapter<Server> = moshi.adapter(Server::class.java)
                    val (_, response, result) = Fuel.post("/server").body(adapter.toJson(server)).responseString()

                    val cookies: SharedPreferences = activity.getSharedPreferences("cookie", Context.MODE_PRIVATE)
                    val editor : SharedPreferences.Editor = cookies.edit()

                    var cookieStr: String = response.headers["set-cookie"]!![0]
                    //                            .replace("[", "").replace("]", "")

                    Log.d("Cookie", cookieStr)
//                    cookieStr =
//                            cookieStr.split(", ")[0].split(Regex(";"))[0] + ";" +
//                            cookieStr.split(", ")[2].split(Regex(";"))[0]

                    editor.putString("cookie", cookieStr)
                    editor.apply()

                    val (date, error) = result
                    if (date != null && error == null) {
                        Log.d("CreateServer", date)
                    } else {
                        Log.e("Error", error.toString())
                    }
                    return ""
                }

                override fun postExecute() {
                    startActivity( Intent( activity.applicationContext, HomeActivity::class.java ) )
                }

                override  fun preExecute() {
                    val viewList = mutableListOf<View>(bt_submit, et_server_name, et_server_password)
                    viewList.forEach {
                        it.setBackgroundColor( Color.parseColor("#9E9E9E") )
                    }

                    bt_submit.isClickable = false
                }
            })

            createServer.execute()
        }

        Log.d("onStart", "finish")

    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("onViewCreated", "start")
        Log.d("onViewCreated", "start")
    }
}// Required empty public constructor
