package com.example.myapplication

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.my_row.view.*

class ProductAdapter(var c:Context,var list:ArrayList<Product>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {

        var myview=LayoutInflater.from(c).inflate(R.layout.my_row,p0,false)


        return MyProduct(myview)

    }

    override fun getItemCount(): Int {
        return list.size    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {

        (p0 as MyProduct).bind(list[p1].name,list[p1].price,list[p1].photo)

        p0.itemView.setOnClickListener {
            Toast.makeText(c,list[p1].name.toString(),Toast.LENGTH_LONG).show()
        }

    }


    class MyProduct(view: View):RecyclerView.ViewHolder(view)
    {
        var tv_name=view.findViewById<TextView>(R.id.textView_prodname)

        var tv_price=view.findViewById<TextView>(R.id.textview_price)

        var iv_photo=view.findViewById<ImageView>(R.id.imageView)



        fun bind(n:String,p:Int,ph:Int)
        {
            tv_name.text=n

            tv_price.text=p.toString()

            iv_photo.setImageResource(ph)


        }


    }


    }

