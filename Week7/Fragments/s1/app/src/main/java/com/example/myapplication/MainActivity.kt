package com.example.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    fun loadPikachu(frag1:PikachuFragment)
    {
        val ft=supportFragmentManager.beginTransaction()

        ft.replace(R.id.mainFragment,frag1)
        ft.commit()
    }

    fun loadPidgey(frag2:PidgyFragment)
    {
        val ft=supportFragmentManager.beginTransaction()

        ft.replace(R.id.mainFragment,frag2)
        ft.commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pika_button.setOnClickListener {
            loadPikachu(PikachuFragment())

        }

        pidgey_button.setOnClickListener {
            loadPidgey(PidgyFragment())
        }





    }
}
