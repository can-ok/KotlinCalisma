package com.example.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var url="http://192.168.1.21/Denemeler/App1/allemp.php"

        var list=ArrayList<String>()


        var rq= Volley.newRequestQueue(this)


        var jar=JsonArrayRequest(Request.Method.GET,url,null,
            Response.Listener {  
                
                response ->

                for(x in 0..response.length()-1)
                {
                    list.add("name:"+response.getJSONObject(x).getString("name")+
                            " Salary: "+response.getJSONObject(x).getString("salary"))
                }

                for(y in 0..list.size-1)
                {
                    textView.text=" "+list[y]
                }

            },
            Response.ErrorListener { error -> Toast.makeText(applicationContext," "+error.message,Toast.LENGTH_LONG).show() })

        rq.add(jar)
1
    }
}
