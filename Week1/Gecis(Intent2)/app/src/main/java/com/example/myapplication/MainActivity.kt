package com.example.myapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun chagenActivity(view:View)
    {
        var intent= Intent(applicationContext,Main2Activity::class.java)

        var extrabilgi=editText.text.toString()

        intent.putExtra("Data","Can Okan")
        intent.putExtra("ExtraData",extrabilgi)

        startActivity(intent)
    }
}
