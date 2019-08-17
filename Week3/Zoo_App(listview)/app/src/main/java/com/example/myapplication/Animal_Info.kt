package com.example.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_animal__info.*

class Animal_Info : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animal__info)

        val bundle:Bundle=intent.extras

        val name=bundle.getString("name")
        val desc=bundle.getString("des")
        val img=bundle.getInt("image")

        imageView.setImageResource(img)
        textViewName.text=name
        textViewDesc.text=desc
    }
}
