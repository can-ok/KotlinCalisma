package com.example.empcarhelper

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_login.setOnClickListener {
            var url:String="http://192.168.1.41/Denemeler/Uber/login.php?mobile="+editText_phone.text+
                    "&password="+editText_password.text.toString()

            var rq=Volley.newRequestQueue(this)

            var sr=StringRequest(Request.Method.GET,url,
                Response.Listener {
                    response ->
                    if(response=="0")
                    {
                        Toast.makeText(applicationContext,"Wrong password or Phone",Toast.LENGTH_LONG).show()
                    }
                    else{
                        EmpInfo.mobile=editText_phone.text.toString()

                        var intent=Intent(this,OrdersListAct::class.java)

                        startActivity(intent)
                    }
                },
                Response.ErrorListener {
                    err->
                    Toast.makeText(applicationContext,"Error"+err.message,Toast.LENGTH_LONG).show()

                })

            rq.add(sr)

        }
    }
}
