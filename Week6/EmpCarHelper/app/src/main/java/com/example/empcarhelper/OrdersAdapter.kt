package com.example.empcarhelper

import android.content.Context
import android.content.Intent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.order_row.view.*

class OrdersAdapter(var context:Context,var list:ArrayList<Orders>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RecyclerView.ViewHolder {

        var v=LayoutInflater.from(context).inflate(R.layout.order_row,parent,false)

        //return viewHolder
        return OrderHolderx(v)

    }

    override fun getItemCount(): Int {

        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //take value from the list
        (holder as OrderHolderx).show(list[position].name,list[position].orderno,list[position].order_date,
            list[position].order_status)

        //item tıklandığında
        holder.itemView.textView_ordernumber.setOnClickListener {
            var i=Intent(context,OrderDetAct::class.java)
            // EmpInfo.orderno=list[position].orderno

            i.putExtra("orderno",list[position].orderno.toString())

            context.startActivity(i)


        }

    }

    class OrderHolderx(itemview:View):RecyclerView.ViewHolder(itemview)
    {

        fun show(name:String,orderNo:Int,orderDate:String,orderStatus:String)
        {
            //which will come from the modul
            itemView.textView_serviceName.text=name

            itemView.textView_ordernumber.text=orderNo.toString()

            itemView.textView_orderDate.text=orderDate

            itemView.textView_orderStatus.text=orderStatus
        }

    }

}
