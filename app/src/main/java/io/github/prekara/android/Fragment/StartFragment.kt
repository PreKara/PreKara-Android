package io.github.prekara.android.Fragment


import android.os.Bundle
import android.app.Fragment
import android.app.FragmentTransaction
import android.transition.Slide
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import io.github.prekara.android.R

import kotlinx.android.synthetic.main.fragment_start.*


/**
 * A simple [Fragment] subclass.
 */
class StartFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val manager  = fragmentManager
        val fragment = ServerInfoFragment()

        bt_create.setOnClickListener {
            // Transition To SeverCreateFragment
            manager.beginTransaction()
                    .replace(R.id.layout_space_for_fragments_main, fragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            manager.executePendingTransactions()
        }

        bt_connect.setOnClickListener {
            // Transition To SeverConnectFragment
            manager.beginTransaction()
                    .replace(R.id.layout_space_for_fragments_main, fragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            manager.executePendingTransactions()
        }

    }
}// Required empty public constructor
