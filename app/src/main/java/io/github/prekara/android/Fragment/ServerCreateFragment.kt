package io.github.prekara.android.Fragment

import android.app.Fragment
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.result.Result
import io.github.prekara.android.AsyncTask.ConnectServer
import io.github.prekara.android.HomeActivity
import io.github.prekara.android.Model.Server
import io.github.prekara.android.R
import kotlinx.android.synthetic.main.fragment_server_info.*
import java.io.IOException

/**
 * Created by developer on 4/3/18.
 */
class ServerCreateFragment : Fragment() {

    companion object {
        fun getInstance(): ServerConnectionFragment {
            Log.d("Fragment", "ServerCreateFragment")
            return ServerConnectionFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_server_info, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        bt_connect.setOnClickListener {
            val serverName = et_server_name.text.toString()
            val password = et_server_password.text.toString()
            ConnectServer( activity,Server(serverName, password).toPairList()).execute("")
//            if (!serverName.isEmpty() && !serverName.isEmpty()) {
//
//                Fuel.post(
//                        "/server",
//                        listOf("server_name" to serverName, "password" to password)
//                ).responseJson() { request, response, result ->
//                    when (result) {
//                        is Result.Success -> {
//                            val json = result.value.obj()
//
//
//                            Log.d("Success", "$result")
//                            Log.d("Response", "$json")
//                            startActivity(Intent(activity.applicationContext, HomeActivity::class.java))
//                        }
//                        is Result.Failure -> {
//                            et_server_name.setText("")
//                            et_server_password.setText("")
//                            Toast.makeText(activity.applicationContext, "Error", Toast.LENGTH_SHORT).show()
//                        }
//                    }
//                }
//            }
        }
        super.onViewCreated(view, savedInstanceState)
    }
}// Required empty public constructor
