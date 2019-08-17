package com.example.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var txt:TextView=findViewById(R.id.editText)

        var btn:Button=findViewById(R.id.button)

        btn.setOnClickListener(object : View.OnClickListener {

            override fun onClick(v: View?) {
                var age=txt.text.toString()

                Toast.makeText(applicationContext,"Age: "+age,Toast.LENGTH_LONG).show()
            }
            
        })

    }
}
