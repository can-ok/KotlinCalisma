package com.example.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    lateinit var etxt:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun getAge(view: View)
    {

        etxt=findViewById(R.id.editText)

        var year=etxt.text.toString().toInt()

        var age=2019-year

        AgeView.text=age.toString()

    }
}
