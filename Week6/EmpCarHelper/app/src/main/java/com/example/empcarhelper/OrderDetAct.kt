package com.example.empcarhelper

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class OrderDetAct : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_det)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap



        var ordernofromintent:Int=intent.getStringExtra("orderno").toInt()

        var rq=Volley.newRequestQueue(this)
        Toast.makeText(this,"ax:"+ordernofromintent,Toast.LENGTH_LONG).show()
        var url="http://192.168.1.41/Denemeler/Uber/order_details.php?orderno="+ordernofromintent

        var jor=JsonObjectRequest(Request.Method.GET,url,null,
            Response.Listener { response ->
                Toast.makeText(this,"lat:"+response.getDouble("lat"),Toast.LENGTH_LONG).show()

                val customer = LatLng(response.getDouble("lat"),response.getDouble("lon"))
                mMap.addMarker(MarkerOptions().position(customer).title("Marker in Sydney"))
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(customer,16f)) },
            Response.ErrorListener {  })

        rq.add(jor)
        // Add a marker in Sydney and move the camera

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.status_menu,menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var status:String=item.title.toString()
        var ordernofromintent:Int=intent.getStringExtra("orderno").toInt()

        var url="http://192.168.1.41/Denemeler/Uber/edit_status.php?order_status="+status+"&orderno="+ordernofromintent
        var rq=Volley.newRequestQueue(this)

        var sr=StringRequest(Request.Method.GET,url,
            Response.Listener { response ->
                Toast.makeText(this,"The order status has been changed",Toast.LENGTH_LONG).show()
            },
            Response.ErrorListener {  })

        rq.add(sr)
        return super.onOptionsItemSelected(item)
    }


}
