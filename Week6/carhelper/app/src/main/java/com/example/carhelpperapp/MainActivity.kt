package com.example.carhelpperapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_sign_up.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        login_button.setOnClickListener {

                    //UserInfo.url+"logim.php
            var url="http://192.168.1.35/Denemeler/Uber/login.php?mobile="+editText_loginMobile.text.toString()+
                    "&password="+editText_loginPassword.text.toString()


            var rq=Volley.newRequestQueue(this)
            var sr=StringRequest(Request.Method.GET,url,
                Response.Listener { response ->

                    if(response=="0")
                    {
                        Toast.makeText(this,"Login Fail",Toast.LENGTH_LONG).show()
                    }
                    else{
                        Toast.makeText(this,"Logged IN",Toast.LENGTH_LONG).show()
                        UserInfo.mobile=editText_loginMobile.text.toString()

                        var intent= Intent(this,HomeAct::class.java)

                        startActivity(intent)

                    }
                },
                Response.ErrorListener {  })


            rq.add(sr)
        }

        button_singup.setOnClickListener {
            var i=Intent(this,SignUpActivity::class.java)
            startActivity(i)
        }

    }
}
