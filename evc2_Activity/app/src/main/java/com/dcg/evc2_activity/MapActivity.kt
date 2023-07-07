package com.dcg.evc2_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions

class MapActivity : AppCompatActivity() , OnMapReadyCallback {
    private lateinit var map:GoogleMap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        val mapFragment=supportFragmentManager.findFragmentById(R.id.fragment_map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        setTitle("Maravillas del Peru")
    }

    override fun onMapReady(p0: GoogleMap) {
        map=p0
        val machuPichu= LatLng(-13.1631412,-72.5449629)
        val lineasDeNazca= LatLng(-14.83098,-74.93895)
        val VolcanMisty= LatLng(-16.2988,-71.4057)
        map.addMarker(MarkerOptions().position(machuPichu).title("MachuPichu,peru"))
        map.addMarker(MarkerOptions().position(lineasDeNazca).title("lineas De Nazca,peru"))
        map.addMarker(MarkerOptions().position(VolcanMisty).title("Volcan Misty,peru"))

        // map.moveCamera(CameraUpdateFactory.newLatLngZoom(lima,16f))
        val bounBuilder= LatLngBounds.builder().include(machuPichu).include(VolcanMisty).include(lineasDeNazca)

        map.animateCamera(CameraUpdateFactory.newLatLngBounds(bounBuilder.build(),50))

    }
}