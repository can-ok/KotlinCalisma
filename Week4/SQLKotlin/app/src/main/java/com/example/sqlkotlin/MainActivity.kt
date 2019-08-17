package com.example.sqlkotlin

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try{

            val myDatabase=this.openOrCreateDatabase("Musicians",Context.MODE_PRIVATE,null)

            /*
            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS musicians(name VARCHAR,age INT(2))")

            myDatabase.execSQL("INSERT INTO musicians(name,age) VALUES('James','50')")
            */
            //myDatabase.execSQL("INSERT INTO musicians(name,age) VALUES('Kirk','60')")

            //myDatabase.execSQL("INSERT INTO musicians(name,age) VALUES('Hasan','70')")

            myDatabase.execSQL("UPDATE musicians SET age=55 WHERE name = 'Hasan'")
            //updating Hasan's age 55


            myDatabase.execSQL("DELETE FROM musicians WHERE name='Hasan'")
            //deleting hasan

            //Query 
            //val cursor=myDatabase.rawQuery("SELECT * FROM musicians WHERE age<60",null)

            val cursor=myDatabase.rawQuery("SELECT * FROM musicians",null)



            val nameIndex=cursor.getColumnIndex("name")
            val ageIndex=cursor.getColumnIndex("age")

            cursor.moveToFirst()

            while(cursor!=null)
            {
                textView.text="Name: "+cursor.getString(nameIndex)+" Age:"+cursor.getString(ageIndex)

                cursor.moveToNext()
            }
            if(cursor!=null)
            {
                cursor!!.close()
            }


        }catch (ex:Exception)
        {
            ex.printStackTrace()
        }
    }
}
