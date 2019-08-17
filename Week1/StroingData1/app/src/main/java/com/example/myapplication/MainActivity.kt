package com.example.myapplication

import android.content.Context
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var Edittxt:EditText
    lateinit var txtView:TextView
    lateinit var sharedPreference:SharedPreferences;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        txtView=findViewById(R.id.textView)

        val sharedpre=getSharedPreferences("com.example.myapplication\"",Context.MODE_PRIVATE)

        var a

    }

}
