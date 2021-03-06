package com.uamar.apigmaps

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import com.google.android.gms.maps.*

import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-8.2239099, 110.9452151)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in pantai klayar"))
                //.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN ))
                .setIcon(BitmapDescriptorFactory.fromResource(R.drawable.index))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

       val zoom : CameraUpdate = CameraUpdateFactory.newLatLngZoom(sydney,21f)
        mMap.animateCamera(zoom)
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val permission: Array<String> = arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION)
            ActivityCompat.requestPermissions(this, permission, 99)
            if (ActivityCompat.checkSelfPermission(this, permission[0]) == PackageManager.PERMISSION_GRANTED)
                mMap.isMyLocationEnabled = true
        }
    }

}
