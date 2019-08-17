package com.example.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var counter=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        btn_change.setOnClickListener {


            if(counter%2==0)
            {
                showFragmnetTwo()
            }
            else
            {
                showFragmnetOne()
            }

        }
    }

    fun showFragmnetOne()
    {
        counter+=1
        val transcation=supportFragmentManager.beginTransaction()

        val fragment=Fragment1()

        transcation.replace(R.id.fragment_holder,fragment)

        transcation.addToBackStack(null)
        transcation.commit()
    }

    fun showFragmnetTwo()
    {
        counter+=1
        val transcation=supportFragmentManager.beginTransaction()

        val fragment= fragment2()

        transcation.replace(R.id.fragment_holder,fragment)

        transcation.addToBackStack(null)
        transcation.commit()
    }

}
