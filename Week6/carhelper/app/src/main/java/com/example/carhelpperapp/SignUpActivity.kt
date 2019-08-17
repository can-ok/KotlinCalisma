package com.example.carhelpperapp

import android.content.Intent
import android.os.Bundle
import android.os.TokenWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)


        button.setOnClickListener {

            if(editText_SignConfirmPassword.text.toString()==editText_SignPassword.text.toString())
            {

                var url:String="http://192.168.1.35/Denemeler/Uber/signup.php?mobile="+editText_SignMobile.text.toString()+"&password="+editText_SignPassword.text.toString()+
                        "&name="+editText_SignName.text.toString()

                var rq=Volley.newRequestQueue(this)

                var sreq=StringRequest(Request.Method.GET,url,
                    Response.Listener{ response ->
                        if(response=="0")
                        {
                            Toast.makeText(this,"Mobile number already exists",Toast.LENGTH_LONG).show()
                        }
                        else{
                            UserInfo.mobile=editText_SignMobile.text.toString()
                            var i=Intent(this,HomeAct::class.java)
                            startActivity(i)

                        }

                    },Response.ErrorListener { error ->
                        Toast.makeText(this,error.message,Toast.LENGTH_LONG).show()
                    })

                rq.add(sreq)

            }
            else{

                Toast.makeText(this,"Password not match",Toast.LENGTH_LONG).show()
            }
        }

    }
}
