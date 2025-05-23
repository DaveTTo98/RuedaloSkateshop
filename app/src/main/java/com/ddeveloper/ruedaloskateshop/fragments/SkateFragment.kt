package com.ddeveloper.ruedaloskateshop.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ddeveloper.ruedaloskateshop.R
import com.ddeveloper.ruedaloskateshop.adapter.ProductAdapter
import com.ddeveloper.ruedaloskateshop.data.Carrito
import com.ddeveloper.ruedaloskateshop.model.Producto
import com.ddeveloper.ruedaloskateshop.model.ShopItem

class SkateFragment : Fragment() {
    private lateinit var recyclerSkates: RecyclerView
    private lateinit var adapter: ProductAdapter

    private val productosLista = listOf(
        Producto(1, "Board Nyjah Monster edition", 420000.0, R.drawable.nyjah_board, "Skates"),
        Producto(2, "Board Felipe Gustavo", 400000.0, R.drawable.felipe_gustavo_board, "Skates"),
        Producto(3, "Board Santa Cruz Star wars Edition", 700000.0, R.drawable.storm_deck, "Skates")
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_skates, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerSkates = view.findViewById(R.id.recyclerSkates)
        recyclerSkates.layoutManager = LinearLayoutManager(requireContext())

        // Aquí convertimos los productos en ShopItems
        val shopItems = mutableListOf<ShopItem>()
        shopItems.add(ShopItem.TituloCategoria("Skates"))
        productosLista.forEach { producto ->
            shopItems.add(ShopItem.ProductoItem(producto))
        }

        adapter = ProductAdapter(shopItems) { producto ->
            Carrito.addProduct(producto)
        }

        recyclerSkates.adapter = adapter
    }
}
