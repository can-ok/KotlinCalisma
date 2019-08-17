package com.example.artbook

import android.app.Activity
import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import kotlinx.android.synthetic.main.activity_info.*
import java.io.ByteArrayOutputStream
import java.lang.Exception
import java.util.jar.Manifest

class InfoActivity : AppCompatActivity() {

    var selectedImage:Bitmap?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)


        val intent=intent

        val info=intent.getStringExtra("info")

        if(info.equals("new"))
        {
            val background=BitmapFactory.decodeResource(applicationContext.resources,R.drawable.select)

            imageView.setImageBitmap(background)
            buttonSava.visibility=View.VISIBLE
            editText.setText(" ")
        }
        else{

            val name=intent.getStringExtra("name")
            editText.setText(name)

            val chosen=Globals.Chosen

            val bitmap=chosen.returnImage()

            imageView.setImageBitmap(bitmap)

            buttonSava.visibility=View.INVISIBLE

        }


    }

    fun selectimage(view:View)
    {
        if(checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),2)
        }
        else{
            //user media storedan çekmesi için
            val intent=Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent,1)
        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if(requestCode==2)
        {
            //if user allow permission
            if(grantResults.size>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
            {
                val intent=Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(intent,1)

            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode==1 && resultCode== Activity.RESULT_OK && data !=null)
        {
            val image=data.data

            try{
                selectedImage=MediaStore.Images.Media.getBitmap(this.contentResolver,image)
                imageView.setImageBitmap(selectedImage)

            }catch (ex:Exception)
            {
                ex.printStackTrace()
            }


        }
        super.onActivityResult(requestCode, resultCode, data)
    }
    fun saveArt(view:View)
    {
        val artName=editText.text.toString()

        val outputStream=ByteArrayOutputStream()
        selectedImage!!.compress(Bitmap.CompressFormat.PNG,50,outputStream)

        val bytearray=outputStream.toByteArray()


        try{
            val database=openOrCreateDatabase("Arts",Context.MODE_PRIVATE,null)

            database.execSQL("CREATE TABLE IF NOT EXISTS arts(name VARCHAR,image BLOB)")


            val insertsql="INSERT INTO arts(name,image) VALUES(?,?)"

            val statement=database.compileStatement(insertsql)

            statement.bindString(1,artName)
            statement.bindBlob(2,bytearray)

            statement.execute()

        }catch (e:Exception){

        }

        val intent=Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
}
