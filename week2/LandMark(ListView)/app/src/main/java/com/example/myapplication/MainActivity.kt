package com.example.myapplication

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.Image
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    lateinit var lstview:ListView
    var imagearray=ArrayList<Bitmap>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val chosen=Globals.Chose

        var landmrk=ArrayList<String>()
        landmrk.add("Pisa")
        landmrk.add("Colosseum")
        landmrk.add("London Bridge")

       
        val pisa=BitmapFactory.decodeResource(applicationContext.resources,R.drawable.pisa)
        val collesum=BitmapFactory.decodeResource(applicationContext.resources,R.drawable.colessuem)


        imagearray.add(pisa)
        imagearray.add(collesum)


        val arrayAdapter=ArrayAdapter(this,android.R.layout.simple_list_item_1,landmrk)

        lstview=findViewById(R.id.listView)

        lstview.adapter=arrayAdapter
        
        lstview.onItemClickListener=AdapterView.OnItemClickListener { parent, view, position, id ->


            val intent=Intent(applicationContext,DetailActivity::class.java)

            intent.putExtra("name",landmrk[position])



            val choseimg:Bitmap=imagearray[position]

            chosen.chosenImage=choseimg



            startActivity(intent)

        }

    }
}
