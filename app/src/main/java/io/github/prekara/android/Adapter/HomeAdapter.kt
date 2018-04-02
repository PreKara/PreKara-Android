package io.github.prekara.android.Adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import io.github.prekara.android.Fragment.HomeTab.PostImageFragment
import io.github.prekara.android.Fragment.HomeTab.PostThemeFragment
import io.github.prekara.android.Fragment.HomeTab.PresenterFragment

/**
 * Created by developer on 4/2/18.
 */
class HomeAdapter(val manager: FragmentManager): FragmentPagerAdapter(manager) {

    val NUM_OF_PAGES: Int = 3

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> PresenterFragment()
            1 -> PostImageFragment()
            2 -> PostThemeFragment()
            else -> PresenterFragment()
        }
    }

    override fun getCount(): Int = NUM_OF_PAGES

}