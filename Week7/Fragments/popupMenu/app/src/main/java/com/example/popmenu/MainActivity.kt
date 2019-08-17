package com.example.popmenu

import android.app.Activity
import android.app.Dialog
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.dialog_activity.*
import java.util.jar.Manifest
import javax.net.ssl.ManagerFactoryParameters

class MainActivity : AppCompatActivity() {

    internal  lateinit var btn:Button
    internal  lateinit var myDialog:Dialog

    var img_uri:Uri?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btn=findViewById<View>(R.id.clickbutton) as Button


        btn.setOnClickListener {

            showDialog()
        }



    }

    lateinit var txtbtn:TextView
    lateinit var latView:TextView
    private val code=100

    private val IMAGE_CAPTURE_CODE:Int=1001
    fun showDialog()
    {
        myDialog= Dialog(this)

        myDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)

        myDialog.setContentView(R.layout.dialog_activity)


        myDialog.setTitle("My fist Dialog box")

        txtbtn = myDialog.findViewById<View>(R.id.button_ok) as TextView

        latView=myDialog.findViewById<View>(R.id.lat_textView) as TextView

        latView.setText("15")

        myDialog.cekfoto_btn.setOnClickListener {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            {
                if(checkSelfPermission(android.Manifest.permission.CAMERA)==PackageManager.PERMISSION_DENIED || checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED)
                {

                    val permission= arrayOf(android.Manifest.permission.CAMERA ,android.Manifest.permission.WRITE_EXTERNAL_STORAGE)

                    requestPermissions(permission,code)
                }
                else{
                        //already granteed

                        check()

                }
            }
            else{
                //system os< masrhmallwo
                check()
            }
        }

        txtbtn.setOnClickListener {

            Toast.makeText(applicationContext,"Thanks for Participating and Lat: "+latView.text,Toast.LENGTH_LONG).show()
            myDialog.cancel()
        }

        myDialog.show()
    }

    fun check()
    {

        val values= ContentValues()

        values.put(MediaStore.Images.Media.TITLE,"New picture")
        values.put(MediaStore.Images.Media.DESCRIPTION,"From the camera")



        val cameraIntent=Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,IMAGE_CAPTURE_CODE)

        startActivityForResult(cameraIntent,IMAGE_CAPTURE_CODE)
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {

        when(requestCode){

            code->{
                if(grantResults.size> 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    showDialog()
                }
                else{

                    Toast.makeText(this,"Permisson denied",Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode==Activity.RESULT_OK)
        {
            Toast.makeText(this,"ekele",Toast.LENGTH_LONG).show()
            var bmp=data!!.extras.get("data") as Bitmap
            myDialog.foto_imageView.setImageBitmap(bmp)
        }
    }
}
