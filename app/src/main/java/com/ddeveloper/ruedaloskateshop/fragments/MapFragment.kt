package com.ddeveloper.ruedaloskateshop.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ddeveloper.ruedaloskateshop.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapFragment: Fragment(), OnMapReadyCallback {

    private lateinit var googleMap: GoogleMap
    private var latitude: Double = 0.0
    private var longitude: Double = 0.0
    private  var place = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_map, container, false)
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = MapFragmentArgs.fromBundle(requireArguments())
        latitude = args.latitude.toDouble()
        longitude = args.longitude.toDouble()
        place = args.skatepark

        val mapFragment = childFragmentManager.findFragmentById(R.id.maps) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        val placeLatLng = LatLng(latitude, longitude)
        googleMap.addMarker(MarkerOptions().position(placeLatLng).title(place))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(placeLatLng, 15f))


    }

}