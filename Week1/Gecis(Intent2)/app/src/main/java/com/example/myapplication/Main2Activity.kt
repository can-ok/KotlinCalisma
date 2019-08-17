package com.example.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Main2Activity : AppCompatActivity() {

    lateinit var txt:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        txt=findViewById(R.id.textView2)

        var name=intent.getStringExtra("Data")

        var extra=intent.getStringExtra("ExtraData")
        txt.setText(name+ ":"+extra)


    }
}
