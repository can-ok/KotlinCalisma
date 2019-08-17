package com.example.myapplication

import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_fragment1.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

class fragment1 : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val v=inflater!!.inflate(R.layout.fragment_fragment1,container,false)

        val txt=v.findViewById<TextView>(R.id.degistextview)

            if(arguments?.getString("str")!=null)
        {
            val str=arguments?.getString("str")

            txt.setText(str)
            txt.setTextColor(Color.BLUE)
           //Toast.makeText(context,str,Toast.LENGTH_LONG).show()

        }


        return v
    }
}
