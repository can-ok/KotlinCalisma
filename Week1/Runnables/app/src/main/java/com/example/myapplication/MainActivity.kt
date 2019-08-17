package com.example.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.INotificationSideChannel
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var number:Int=0;
    var handler:Handler= Handler()
    var runnable:Runnable= Runnable {  }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun start(view:View)
    {
        number=0

        runnable=object :Runnable{
            override fun run() {
                TextView.text="Time "+number
                number++

                handler.postDelayed(this,1000)//In there this referce Runnable
            }
        }

        handler.post(runnable)
    }
    fun Restart(view:View)
    {
        handler.removeCallbacks(runnable)//kill the runnable
        number=0

        TextView.text="Time "+number

    }
}
