package com.example.myapplication

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.food_ticket.view.*

class MainActivity : AppCompatActivity() {

    var adapter:FoodAdapter?= null
    var foodlist=ArrayList<Food>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        foodlist.add(Food("Coffe","Description about coffee",R.drawable.coffee_pot))
        foodlist.add(Food("Espersso","Espresso's authentic",R.drawable.espresso))
        foodlist.add(Food("French Fires","Firees...",R.drawable.french_fries))
        foodlist.add(Food("Strawberry","berr",R.drawable.strawberry_ice_cream))
        adapter= FoodAdapter(this,foodlist)

        gvListFood.adapter=adapter

    }

    class FoodAdapter:BaseAdapter{

        var listOfFood=ArrayList<Food>()
        var context:Context?=null

        constructor(context:Context,ListofFood:ArrayList<Food>):super()
        {
            this.context=context
            this.listOfFood=ListofFood

        }
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

            val fooditem=listOfFood[position]
            var inflator=context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

            var foodView=inflator.inflate(R.layout.food_ticket,null)

            foodView.imageView.setImageResource(fooditem.image!!)
            foodView.textView.text=fooditem.name

            return  foodView
        }

        override fun getItem(position: Int): Any {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun getItemId(position: Int): Long {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun getCount(): Int {

            return  listOfFood.size
        }

    }
}
