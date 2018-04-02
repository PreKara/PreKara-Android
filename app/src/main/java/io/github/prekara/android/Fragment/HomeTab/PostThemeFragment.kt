package io.github.prekara.android.Fragment.HomeTab


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import io.github.prekara.android.R


/**
 * A simple [Fragment] subclass.
 */
class PostThemeFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_post_theme, container, false)
    }

}// Required empty public constructor
