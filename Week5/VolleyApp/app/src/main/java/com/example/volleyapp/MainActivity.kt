package com.example.volleyapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //IP4:192.168.1.20



        buttonSend.setOnClickListener {
            //passing the parameters
            var url:String="http://192.168.1.22/Denemeler/add.php"+
                    "?name="+editText_name.text.toString()+
                    "&mobile="+editText_phone.text.toString()

            //request and retrieve result
            var rq=Volley.newRequestQueue(this)

            var sr=StringRequest(Request.Method.GET,url,
                Response.Listener { response ->
                    Toast.makeText(applicationContext,"This: "+response,Toast.LENGTH_LONG).show()
                },Response.ErrorListener { error ->
                    Toast.makeText(this,error.message,Toast.LENGTH_LONG).show()
                })

            rq.add(sr)


        }
    }
}
