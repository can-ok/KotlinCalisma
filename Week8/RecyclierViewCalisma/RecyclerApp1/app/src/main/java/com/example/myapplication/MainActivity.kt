package com.example.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var listem=ArrayList<Product>()

        listem.add(Product("COCO COLA",2, R.drawable.dog))

        listem.add(Product("pepesi",2,R.drawable.sx))



        var adp=ProductAdapter(this,listem)

        recyclerView.layoutManager=LinearLayoutManager(this)

        recyclerView.adapter=adp
    }
}
