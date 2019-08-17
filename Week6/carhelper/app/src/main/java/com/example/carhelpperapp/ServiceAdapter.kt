package com.example.carhelpperapp

import android.content.Context
import android.content.Intent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.service_row.view.*

class ServiceAdapter(var context:Context,var list:ArrayList<CarServices>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        var V=LayoutInflater.from(context).inflate(R.layout.service_row,parent,false)

        return  ServiceHolder(V)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, p1: Int) {
        (holder as ServiceHolder).show(list[p1].name,list[p1].photo)

        holder.itemView.imageView_Service.setOnClickListener {
            var i=Intent(context,OrderAct::class.java)
            i.putExtra("info",list[p1].id.toString())//which service we chose (secilen servisi gönder)

            context.startActivity(i)
        }
    }

    class ServiceHolder(itemView:View):RecyclerView.ViewHolder(itemView)
    {
        fun show(n:String,photo:String)
        {
            itemView.textView_ServiceName.text=n
            Picasso.get().load("http://192.168.1.35/Denemeler/Uber/"+photo).into(itemView.imageView_Service)//use picasso lib
        }
    }
}