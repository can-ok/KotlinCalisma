package com.example.volleyapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_rv.*

class RVActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rv)

        var listem=ArrayList<Driver>()

        var rq=Volley.newRequestQueue(this)


        var url:String="http://172.20.10.3/Denemeler/all_drivers.php"


        var jsonArrReq=JsonArrayRequest(Request.Method.GET,url,null,
            Response.Listener { response ->
                for(x in 0..response.length()-1)
                {
                    Toast.makeText(this,"BAŞARILI",Toast.LENGTH_LONG).show()
                    listem.add(Driver(response.getJSONObject(x).getString("name"),response.getJSONObject(x).getString("mobile")))


                    var adp=DriverAdapter(this,listem)

                    recyclerView.layoutManager=LinearLayoutManager(this)

                    recyclerView.adapter=adp

                }
            },
            Response.ErrorListener { error ->
                Toast.makeText(this,error.message,Toast.LENGTH_LONG).show()

            })

        rq.add(jsonArrReq)
    }
}
