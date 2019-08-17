package com.example.myapplication

import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    var score:Int=0

    var imagearray=ArrayList<ImageView>()

    var handler:Handler=Handler()

    var runnable:Runnable= Runnable {  }

    lateinit var btn:Button
    lateinit var scoretxt:TextView
    lateinit var timertxt:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn=findViewById(R.id.button)

        btn.visibility=View.INVISIBLE

        score=0

        imagearray= arrayListOf(imageView0,imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8)

        hiddeImage()

        scoretxt=findViewById(R.id.textViewScore)

        timertxt=findViewById(R.id.textViewTimer)


        //timer
        object:CountDownTimer(10000,1000)
        {
            override fun onFinish() {
                handler.removeCallbacks(runnable)
                for(i in imagearray)
                {
                    i.visibility=View.INVISIBLE
                }

                timertxt.text="Time: 0"
                btn.visibility=View.VISIBLE
            }

            override fun onTick(millisUntilFinished: Long) {
                timertxt.text="Time: "+(millisUntilFinished/1000).toString()
            }

        }.start()

    }

    fun increaseScore(view: View)
    {

        score++

        scoretxt.text="Score:"+score.toString()

    }
    fun hiddeImage()
    {
        runnable=object:Runnable{
            override fun run() {
                for(image in imagearray)
                {
                    image.visibility=View.INVISIBLE

                }

                val random= Random()
                val index=random.nextInt(8-0)
                imagearray[index].visibility=View.VISIBLE

                handler.postDelayed(runnable,500)


            }

        }

        handler.post(runnable)

    }
    fun retryclick(view:View)
    {
        Toast.makeText(applicationContext,"Game Done",Toast.LENGTH_LONG).show()
        val alert=AlertDialog.Builder(this)

        alert.setTitle("End")

        alert.setMessage("Would you like to play again")

        alert.setPositiveButton("Are you sure  you want a play again"){dialog:DialogInterface?,which:Int->

            finish();
            startActivity(getIntent());
        }

        alert.setNegativeButton("Done"){ dialogInterface: DialogInterface, i: Int ->
            Toast.makeText(applicationContext,"Game Done",Toast.LENGTH_LONG).show()
        }
        alert.show()
    }
}
