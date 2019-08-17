package com.example.empcarhelper

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_orders_list.*

class OrdersListAct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orders_list)


        var url="http://192.168.1.41/Denemeler/Uber/show_orders.php?mobile="+EmpInfo.mobile
        var list=ArrayList<Orders>()

        var rq=Volley.newRequestQueue(this)

        var jar=JsonArrayRequest(Request.Method.GET,url,null,
            Response.Listener {

                response ->

                for(x in 0..response.length()-1)
                {
                    list.add(Orders(response.getJSONObject(x).getString("name"),
                        response.getJSONObject(x).getInt("orderno"),
                        response.getJSONObject(x).getString("order_date"),
                        response.getJSONObject(x).getString("order_status")))

                    Toast.makeText(applicationContext,response.getJSONObject(x).getString("orderno"),Toast.LENGTH_LONG).show()
                }

                    var adpter=OrdersAdapter(this,list)
                    recyclerView.adapter=adpter

               //Toast.makeText(applicationContext,adpter.itemCount.toString(),Toast.LENGTH_LONG).show()

                    //for show linear way or Veritcal way
                    recyclerView.layoutManager= LinearLayoutManager(this)


            },
            Response.ErrorListener {  })


        rq.add(jar)
    }
}
