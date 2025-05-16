package com.ddeveloper.ruedaloskateshop.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ddeveloper.ruedaloskateshop.R

class PlacesFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_places, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    view.findViewById<Button>(R.id.TercerButton).setOnClickListener {
        goToMap("Tercer Milenio",4.5960157029090345, -74.08150433127962 )
    }

        view.findViewById<Button>(R.id.FontanarButton).setOnClickListener {
            goToMap("Fontanar",4.7568937869241, -74.11120904673233 )
        }

        view.findViewById<Button>(R.id.JaponButton).setOnClickListener{
            goToMap("Japón DIY",4.62043596273316, -74.15479887929386)
        }

    }

    private fun goToMap(skatepark: String, latitude: Double, longitude: Double) {
        val action = PlacesFragmentDirections
            .actionPlacesFragmentToMapFragment(skatepark, latitude.toFloat(), longitude.toFloat())

        findNavController().navigate(action)

    }
















}
