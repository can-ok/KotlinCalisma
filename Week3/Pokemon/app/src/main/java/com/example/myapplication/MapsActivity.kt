package com.example.myapplication


import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.lang.Exception

class  MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        checkPermison()
        loadPockemon()
    }

    var Accessloctaion=123

    fun checkPermison()
    {
        if(Build.VERSION.SDK_INT>=23)
        {
            //permission alınmamış ise almak için sor
            if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED)
            {
                requestPermissions(arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),Accessloctaion)
                return
            }

        }
        GetUserLocation()


    }
    fun GetUserLocation()
    {
        Toast.makeText(this,"User Locataion access on",Toast.LENGTH_LONG).show()
        //TODO:Will implement later

        var mylocation=MylocationListener()

        var locationManager=getSystemService(Context.LOCATION_SERVICE) as LocationManager

        //3 millisecond or 3 metre
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,3000,3f,mylocation)

        var mythread=myThread()

        mythread.start()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode)
        {
            Accessloctaion->{
                if(grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                    GetUserLocation()
                }
                else{
                    Toast.makeText(this,"We can't access Locataion",Toast.LENGTH_LONG).show()
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    var location:Location?=null

    inner class MylocationListener:LocationListener{


        constructor()
        {
            location= Location("Start")
            location!!.longitude=35.0
            location!!.latitude=40.0
        }
        override fun onLocationChanged(p0: Location?) {

            location=p0
        }

        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onProviderEnabled(provider: String?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onProviderDisabled(provider: String?) {
            //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }


    var oldLocation:Location?=null

    inner class myThread:Thread{
        constructor():super() {
            oldLocation= Location("Start")
            oldLocation!!.longitude=0.0
            oldLocation!!.latitude=0.0


        }
            override fun  run()
            {
                while(true)
                {
                    try{
                        /*
                        if(oldLocation!!.distanceTo(location)==0f)
                        {
                            continue
                        }


                        oldLocation=location
                           */

                        runOnUiThread {

                            Toast.makeText(applicationContext,"xx",Toast.LENGTH_LONG).show()

                            mMap.clear()

                            val loc = LatLng(location!!.latitude, location!!.longitude)
                            mMap.addMarker(
                                MarkerOptions()
                                    .position(loc).title("Me").snippet("Here is my locatation")
                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.mario))
                            )

                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, 10f))
                        }

                        //show Pokemon

                        for(i in 0..listofPockemno.size-1)
                        {
                            Toast.makeText(applicationContext,"poke ",Toast.LENGTH_LONG).show()
                            var newPockemon=listofPockemno[i]

                            if(newPockemon.IsCatch==false)
                            {
                                val pockemonloc = LatLng(newPockemon.lat!!,newPockemon.log!!)
                                mMap.addMarker(
                                    MarkerOptions()
                                        .position(pockemonloc).title(newPockemon.name).snippet("Here is Pockemon")
                                        .icon(BitmapDescriptorFactory.fromResource(newPockemon.image!!))
                                )

                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pockemonloc, 10f))
                            }
                        }


                        Thread.sleep(1000)

                    }catch (ex:Exception)
                    {

                    }
                }

        }
    }


    var listofPockemno=ArrayList<Pockemon>()

    fun loadPockemon()
    {
        listofPockemno.add(Pockemon(R.drawable.charmander,"Charmander","Dragon",55.0,37.333,40.00,false))

        listofPockemno.add(Pockemon(R.drawable.squirtle,"Squirtle","Water",55.0,38.333,36.00,false))


    }
}
