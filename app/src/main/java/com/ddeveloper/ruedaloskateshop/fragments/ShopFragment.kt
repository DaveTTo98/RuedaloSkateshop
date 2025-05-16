package com.ddeveloper.ruedaloskateshop.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ddeveloper.ruedaloskateshop.R
import com.ddeveloper.ruedaloskateshop.adapter.ProductAdapter
import com.ddeveloper.ruedaloskateshop.model.Producto
import com.ddeveloper.ruedaloskateshop.data.Carrito



class ShopFragment: Fragment() {

    private lateinit var recyclerShop: RecyclerView
    private lateinit var adapter : ProductAdapter

    //PRODUCT LIST

    private val products = listOf(
        Producto(1, "Camiseta Dc Shoes ", 249.000, R.drawable.dc_shirt),
        Producto(2, "Buso Nike Sb ", 349.900, R.drawable.buso_nike),
        Producto(3, "Tennis Nike Sb Janoski ", 320.000, R.drawable.nike_janoski)
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_shop, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerShop = view.findViewById(R.id.recyclerShop)
        recyclerShop.layoutManager = LinearLayoutManager(requireContext())
        adapter = ProductAdapter(products) { producto ->
            Carrito.addProduct(producto)

        }
        recyclerShop.adapter = adapter

    }



}