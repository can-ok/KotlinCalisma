package com.example.volleyapp

import android.content.Context
import android.support.v7.view.menu.ActionMenuItemView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.driver_row.view.*

class DriverAdapter(var context:Context,var list: ArrayList<Driver>):RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {

        var v=LayoutInflater.from(context).inflate(R.layout.driver_row,p0,false)

        return MyDriver(v)
    }

    override fun getItemCount(): Int {

        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MyDriver).show(list[position].name,list[position].mobile )


    }

    class MyDriver(itemView: View):RecyclerView.ViewHolder(itemView)
    {

        fun show(n:String,m:String)
        {
            itemView.textView_DriverName.text=n

            itemView.textView_DriverMobile.text=m


        }
    }
}