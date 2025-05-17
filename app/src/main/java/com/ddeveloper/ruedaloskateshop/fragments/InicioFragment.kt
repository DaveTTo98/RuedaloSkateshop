package com.ddeveloper.ruedaloskateshop.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ddeveloper.ruedaloskateshop.R
import com.ddeveloper.ruedaloskateshop.adapter.CarruselAdapter
import com.ddeveloper.ruedaloskateshop.adapter.HomeAdapter
import com.ddeveloper.ruedaloskateshop.model.ImagenCarrusel
import com.ddeveloper.ruedaloskateshop.model.Producto

class InicioFragment : Fragment() {

    private lateinit var recyclerInicio: RecyclerView
    private lateinit var recyclerCarrusel: RecyclerView
    private lateinit var homeAdapter: HomeAdapter
    private lateinit var carruselAdapter: CarruselAdapter
    private val handler = Handler(Looper.getMainLooper())
    private var carruselPosition = 0

    private val productosDestacados = listOf(
        Producto(1, "Camiseta DC Shoes", 249.00, R.drawable.dc_shirt, categoria = "Ropa"),
        Producto(2, "Gorra Vans", 349.00, R.drawable.vans_cap, categoria = "Accesorios"),
        Producto(3, "Board Nyjah Monster edition", 420000.0, R.drawable.nyjah_board, categoria = "Accesorios")
    )

    private val imagenesCarrusel = listOf(
        ImagenCarrusel(R.drawable.fontanar, "FONTANAR SKATEPARK"),
        ImagenCarrusel(R.drawable.tercer_milenio, "TERCER MILENIO SKATEPARK"),
        ImagenCarrusel(R.drawable.japon_kenedy, "JAPÓN DIY SKATEPARK"),
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_inicio, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Carrusel
        recyclerCarrusel = view.findViewById(R.id.recyclerCarrusel)
        recyclerCarrusel.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        carruselAdapter = CarruselAdapter(imagenesCarrusel)
        recyclerCarrusel.adapter = carruselAdapter


        recyclerInicio = view.findViewById(R.id.recyclerInicio)
        recyclerInicio.layoutManager = LinearLayoutManager(requireContext())
        homeAdapter = HomeAdapter(productosDestacados)
        recyclerInicio.adapter = homeAdapter


        val btnVerRopa: Button = view.findViewById(R.id.btnRopa)
        btnVerRopa.setOnClickListener {
            findNavController().navigate(R.id.ropaFragment)
        }

        startAutoScroll()
    }

    private fun startAutoScroll() {
        handler.postDelayed(object : Runnable {
            override fun run() {
                if (carruselAdapter.itemCount == 0) return

                recyclerCarrusel.smoothScrollToPosition(carruselPosition)
                carruselPosition = (carruselPosition + 1) % carruselAdapter.itemCount
                handler.postDelayed(this, 2500)
            }
        }, 2500)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        handler.removeCallbacksAndMessages(null)
    }
}
