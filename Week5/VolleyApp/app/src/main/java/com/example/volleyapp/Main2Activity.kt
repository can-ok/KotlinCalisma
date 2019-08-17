package com.example.volleyapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        button_find.setOnClickListener {

            var url:String="http://192.168.1.22/Denemeler/find.php?id="+editText_id.text.toString()

            var rq=Volley.newRequestQueue(this)

            var jsonobReq=JsonObjectRequest(Request.Method.GET,url,null,
                Response.Listener { response ->
                    textView_name.text=response.getString("name")
                    //go to json object and retrive name

                    textView_mobile.text=response.getString("mobile")

                },
                Response.ErrorListener {  })


            rq.add(jsonobReq)

        }

        button_getAll.setOnClickListener {


            var result:String=" "
            var url:String="http://192.168.1.22/Denemeler/all_drivers.php"

            var rq=Volley.newRequestQueue(this)

            var jsonArrayReq=JsonArrayRequest(Request.Method.GET,url,null,
                Response.Listener {  response ->
                    for(x in 0..response.length()-1)
                    {
                        result+=response.getJSONObject(x).getString("name")+" \n"


                    }

                    Toast.makeText(this,result,Toast.LENGTH_LONG).show()
                }
            ,Response.ErrorListener { error ->

                    Toast.makeText(this,error.message,Toast.LENGTH_LONG).show()
                })


            rq.add(jsonArrayReq)

        }

    }
}
