package com.example.artbook

import android.graphics.Bitmap

class Globals {
    companion object Chosen{

        var chosenImag:Bitmap?=null

        fun returnImage():Bitmap
        {
            return  chosenImag!!
        }

    }
}