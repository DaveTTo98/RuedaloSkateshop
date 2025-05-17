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

class ClothesFragment : Fragment() {
    private lateinit var recyclerRopa: RecyclerView
    private lateinit var adapter: ProductAdapter

    private val productosLista = listOf(
        Producto(1, "Camiseta DC Shoes", 249000.0, R.drawable.dc_shirt, "Ropa"),
        Producto(2, "Buso Nike Sb", 249000.0, R.drawable.buso_nike, "Ropa"),
        Producto(3, "Tennis Stefan Janoski", 349000.0, R.drawable.nike_janoski, "Ropa")
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_clothes, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerRopa = view.findViewById(R.id.recyclerRopa)
        recyclerRopa.layoutManager = LinearLayoutManager(requireContext())

        // Aquí convertimos los productos en ShopItems
        val shopItems = mutableListOf<ShopItem>()
        shopItems.add(ShopItem.TituloCategoria("Ropa"))
        productosLista.forEach { producto ->
            shopItems.add(ShopItem.ProductoItem(producto))
        }

        adapter = ProductAdapter(shopItems) { producto ->
            Carrito.addProduct(producto)
        }

        recyclerRopa.adapter = adapter
    }
}
