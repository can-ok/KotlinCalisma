package com.example.carhelpperapp


import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ListAdapter

import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_home.*

class HomeAct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        var url:String="http://172.20.10.3/Denemeler/Uber/services.php"

        var list=ArrayList<CarServices>()

        var rq=Volley.newRequestQueue(this)

        var jar=JsonArrayRequest(Request.Method.GET,url,null,
            Response.Listener { response ->
                for(x in 0.. response.length()-1)
                {
                    list.add(CarServices(response.getJSONObject(x).getInt("id"),
                        response.getJSONObject(x).getString("name")
                    ,response.getJSONObject(x).getString("photo")))
                }
                var adp=ServiceAdapter(this,list)
                recyclerView_Services.adapter=adp
                recyclerView_Services.layoutManager= GridLayoutManager(this,2)//display 2 services in each row
            },
            Response.ErrorListener {  error ->
                Toast.makeText(this,error.message,Toast.LENGTH_LONG).show()
            })

        rq.add(jar)


    }
}
