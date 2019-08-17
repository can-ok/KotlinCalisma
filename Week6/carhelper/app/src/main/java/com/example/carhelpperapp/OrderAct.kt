package com.example.carhelpperapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.HeaderViewListAdapter
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_order.*
import java.util.jar.Manifest

class OrderAct : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    var locationManager:LocationManager?=null
    var locationlistener:LocationListener?=null
    var lon:Double=0.0
    var lat:Double=0.0


    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)




    }


    fun sendOrder()
    {
        var serviceid:String=intent.getStringExtra("info")

        var url="http://192.168.1.35/Denemeler/Uber/avl_emp.php?mobile="+UserInfo.mobile+"&serviceid="+serviceid+
                "&lat="+lat+"&lon="+lon

        var rq=Volley.newRequestQueue(this)
        var sr=JsonObjectRequest(Request.Method.GET,url,null,
            Response.Listener { response ->
                Toast.makeText(applicationContext,"Your order has been sent",Toast.LENGTH_LONG).show()

                var mgr=SmsManager.getDefault()
                mgr.sendTextMessage(response.getString("emp_mobile"), null,"You have a new task"+
                        response.getString("orderno"),null,null)




                Toast.makeText(applicationContext,"new order:"+response.getString("emp_mobile")+
                        "-"+response.getString("orderno"),Toast.LENGTH_LONG).show()


            },Response.ErrorListener {  error ->
                Toast.makeText(applicationContext,"Error:"+error.message,Toast.LENGTH_LONG).show()

            })


        rq.add(sr)
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap



        if(Build.VERSION.SDK_INT<23)
        {
            showLoc()
        }
        else{
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION),123)
        }


        if(lat==0.0 && lon==0.0)
        {

            Toast.makeText(applicationContext, "HEAS ADD.", Toast.LENGTH_LONG).show();

            mMap.clear()
            val HEAS = LatLng(40.902613, 29.317083)

            lat=HEAS.latitude
            lon=HEAS.longitude
            val lastLocation = HEAS//locationManager!!.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            if (lastLocation != null) {
                var lastUserLocation = LatLng(lastLocation.latitude, lastLocation.longitude)
                mMap.addMarker(
                    MarkerOptions().position(lastUserLocation).title("Repairman").snippet("here is my location")
                )

                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lastUserLocation, 17f))


            }

        }

        var serviceid:String=intent.getStringExtra("info")

        Toast.makeText(this,"USer: "+UserInfo.mobile+" service"+serviceid,Toast.LENGTH_LONG).show()
        order_button.setOnClickListener {
                //http://192.168.1.35/Denemeler/Uber/avl_emp.php?mobile=123&serviceid=1&lat=21&lon=22


            if(Build.VERSION.SDK_INT<23)
            {
                showLoc()
            }
            else{
                ActivityCompat.requestPermissions(this,  arrayOf(android.Manifest.permission.SEND_SMS),456)
            }

        }


    }

    @SuppressLint("MissingPermission")
    fun showLoc()
    {
        var locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager




        var locationlistener = object : LocationListener {
            override fun onLocationChanged(p0: Location) {

                lat=p0.latitude
                lon=p0.longitude
                val my_Loc = LatLng(p0.latitude, p0.longitude)
                Toast.makeText(applicationContext, my_Loc.latitude.toString(), Toast.LENGTH_LONG).show()
                mMap.addMarker(MarkerOptions().position(my_Loc).title("Marker in Sydney"))
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(my_Loc, 16f))


            }

            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onProviderEnabled(provider: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onProviderDisabled(provider: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        }


        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, locationlistener)

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode==123)
        {
            //if user accept first and second permission
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED && grantResults[1]==PackageManager.PERMISSION_GRANTED)
            {
                showLoc()
            }
        }
        if(requestCode==456)
        {
            //if user accept first and second permission
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED)
            {
                sendOrder()
            }
        }
    }





}

