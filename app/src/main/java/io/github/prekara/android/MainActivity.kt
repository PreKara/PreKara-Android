package io.github.prekara.android

import android.app.Fragment
import android.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import io.github.prekara.android.Fragment.StartFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragment     = StartFragment()
        val transaction  = fragmentManager.beginTransaction()
        transaction.replace(R.id.layout_space_for_fragments_main,fragment)
        transaction.commit()

    }
}
