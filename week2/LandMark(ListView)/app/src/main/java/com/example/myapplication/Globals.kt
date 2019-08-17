package com.example.myapplication

import android.graphics.Bitmap

class Globals {

    companion object Chose{
        var chosenImage:Bitmap?=null

        fun returnImage():Bitmap
        {
            return chosenImage!!
        }
    }
}