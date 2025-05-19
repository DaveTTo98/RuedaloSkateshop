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

class AccesoriosFragment : Fragment() {
    private lateinit var recyclerAcc: RecyclerView
    private lateinit var adapter: ProductAdapter

    private val productosLista = listOf(
        Producto(1, "Gorra Vans", 130000.0, R.drawable.vans_cap, "Accesorios"),
        Producto(2, "Medias Skate Stance Star wars Collection", 170000.0, R.drawable.chewbee_socks, "Accesorios"),
        Producto(3, "Mochila DC", 149000.0, R.drawable.dc_bag, "Accesorios")
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_accesorios, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerAcc= view.findViewById(R.id.recyclerAccesorios)
        recyclerAcc.layoutManager = LinearLayoutManager(requireContext())

        // Aquí convertimos los productos en ShopItems
        val shopItems = mutableListOf<ShopItem>()
        shopItems.add(ShopItem.TituloCategoria("Accesorios"))
        productosLista.forEach { producto ->
            shopItems.add(ShopItem.ProductoItem(producto))
        }

        adapter = ProductAdapter(shopItems) { producto ->
            Carrito.addProduct(producto)
        }

        recyclerAcc.adapter = adapter
    }
}
