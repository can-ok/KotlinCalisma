package com.example.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        chng_btn.setOnClickListener {
            val transcation=supportFragmentManager.beginTransaction()

            fragment_holder.visibility=View.VISIBLE

            val fragment=fragment1()

            val bundle=Bundle()

            bundle.putString("str","I am coming from Fragment_01")

            fragment.arguments=bundle

            transcation.replace(R.id.fragment_holder,fragment)

            transcation.addToBackStack(null)
            transcation.commit()
        }
        chng_color.setOnClickListener {

            fragment_holder.visibility=View.INVISIBLE
        }
    }
}
