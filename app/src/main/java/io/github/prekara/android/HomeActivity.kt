package io.github.prekara.android

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.fuel.httpGet
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

        Log.d("HomeActivity", "Created")

        val cookie: SharedPreferences = getSharedPreferences("cookie", Context.MODE_PRIVATE)

        val adapter = HomeAdapter(supportFragmentManager)
        pager.adapter = adapter

        var cookieStr: String = cookie.getString("cookie", "")
                .replace("[", "").replace("]", "")
        cookieStr =
                cookieStr.split(", ")[0].split(Regex(";"))[0] + ";" +
                cookieStr.split(", ")[2].split(Regex(";"))[0]

        "/session".httpGet().header("Content-Type" to "application/json").header("Cookie" to cookieStr).responseJson { request, response, result ->
            when (result) {
                is Result.Success -> {
                    Log.d("Success", result.get().toString())
                }
                is Result.Failure -> {
                    Log.d("Header", request.headers.toString())
                    Log.e("Error", result.getException().toString())
                }
            }
        }

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}
