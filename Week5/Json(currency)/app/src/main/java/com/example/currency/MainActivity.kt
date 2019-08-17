package com.example.currency

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.JsPromptResult
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URI
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



    }
    fun get(view:View)
    {

        val url="http://data.fixer.io/api/latest?access_key=21420251f61e55538794dede8fab94f8"

        val downloadData=Download()

        downloadData.execute(url)

        editText.setText("EUR")


        /*


                val url="http://data.fixer.io/api/latest?access_key=21420251f61e55538794dede8fab94f8&base?"
                val chosen=edit.text.toString()

                        downloadData.execute(url+chosen)

         */
    }



    inner class Download: AsyncTask<String, Void, String>(){

        override fun doInBackground(vararg params: String?): String {

            var result=" "

            var url:URL
            val httpURLConnection:HttpURLConnection

            try{
                url=URL(params[0])

                httpURLConnection=url.openConnection() as HttpURLConnection
                val inputStream=httpURLConnection.inputStream

                val inputStreamReader=InputStreamReader(inputStream)

                var data=inputStreamReader.read()

                while (data>0)
                {
                    val character=data.toChar()
                    result+=character

                    data=inputStreamReader.read()
                }
                return result

            }catch (e:Exception)
            {
                e.printStackTrace()
                return " "
            }


        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)

            try{

                val JSONObject=JSONObject(result)

                println(JSONObject)

                val base=JSONObject.getString("base")
                Toast.makeText(applicationContext,"Base :"+base,Toast.LENGTH_LONG).show()

                val rates=JSONObject.getString("rates")
                println(rates)


                val newJsonobject=JSONObject(rates)

                val chf=newJsonobject.getString("CHF: ")

                textView.text="CHF"+chf

                println(chf)

                val tl=newJsonobject.getString("TRY")

                Toast.makeText(applicationContext,"TR Kuru:"+tl,Toast.LENGTH_LONG).show()

                textView2.text="TRY: "+tl

            }catch (e:Exception)
            {

            }

        }

    }
}
