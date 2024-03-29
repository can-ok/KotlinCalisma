package com.example.myapplication

import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun givAlert(view: View)
    {

        val alert=AlertDialog.Builder(this)

        alert.setTitle("Save")

        alert.setMessage("Are you sure you want a save this")
        alert.setPositiveButton("Yes"){ dialog: DialogInterface?, which: Int ->
            Toast.makeText(applicationContext," SAVED",Toast.LENGTH_LONG).show()



        }


        alert.setNegativeButton("NO"){dialog: DialogInterface?, which: Int ->
            Toast.makeText(applicationContext,"Not saved",Toast.LENGTH_LONG).show()
        }

        alert.show()

    }
}
