package io.github.prekara.android

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.result.Result
import io.github.prekara.android.Adapter.HomeAdapter
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                pager.currentItem = 0
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                pager.currentItem = 1
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                pager.currentItem = 2
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        Fuel.get("/api").response { request, response, result ->
            when (result) {
                is Result.Success -> {
                    Log.d("Success", "$result")
                    Log.d("Response", "${response.data}")
                }
                is Result.Failure -> {
                    Log.d("Error", result.getException().toString())
                }
            }
        }

        val adapter = HomeAdapter(supportFragmentManager)
        pager.adapter = adapter

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}
