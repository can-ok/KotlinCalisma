 package com.example.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*

 class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        button_find.setOnClickListener {
            var url="http://192.168.1.21/Denemeler/App1/findemp.php?id="+editText_id.text.toString()

            var rq=Volley.newRequestQueue(this)


            var jor=JsonObjectRequest(Request.Method.GET,url,null,
                Response.Listener {
                response ->

                    textView_name.text=response.getString("name")

                    textView_salary.text=response.getString("salary")


            },Response.ErrorListener {

                    error ->
                    Toast.makeText(applicationContext,"err"+error.message, Toast.LENGTH_LONG).show()

            })

            rq.add(jor)

        }


    }
}
