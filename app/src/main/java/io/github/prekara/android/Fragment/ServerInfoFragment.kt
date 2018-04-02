package io.github.prekara.android.Fragment


import android.os.Bundle
import android.app.Fragment
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.prekara.android.HomeActivity

import io.github.prekara.android.R
import kotlinx.android.synthetic.main.fragment_server_info.*


/**
 * A simple [Fragment] subclass.
 */
class ServerInfoFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_server_info, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        bt_connect.setOnClickListener {
            startActivity( Intent( activity.applicationContext, HomeActivity::class.java ) )
        }
        super.onViewCreated(view, savedInstanceState)
    }
}// Required empty public constructor
