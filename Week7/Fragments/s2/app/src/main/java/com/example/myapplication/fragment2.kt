package com.example.myapplication

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast


class fragment2 : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Toast.makeText(context, "GEç", Toast.LENGTH_LONG).show()
        return inflater.inflate(R.layout.fragment_fragment2,container,false)

    }
}
