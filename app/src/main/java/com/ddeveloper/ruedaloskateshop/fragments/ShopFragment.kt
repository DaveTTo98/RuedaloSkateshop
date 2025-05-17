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
import com.ddeveloper.ruedaloskateshop.model.ShopItem
import com.ddeveloper.ruedaloskateshop.model.Producto

class ShopFragment : Fragment() {

    private lateinit var recyclerShop: RecyclerView
    private lateinit var adapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_shop, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerShop = view.findViewById(R.id.recyclerShop)
        recyclerShop.layoutManager = LinearLayoutManager(requireContext())

        val items = buildTiendaItems()
        adapter = ProductAdapter(items) { producto ->
            Carrito.addProduct(producto)
        }
        recyclerShop.adapter = adapter
    }

    private fun buildTiendaItems(): List<ShopItem> {
        val items = mutableListOf<ShopItem>()

        val ropa = listOf(
            Producto(
                1, "Camiseta DC Shoes", 249000.0, R.drawable.dc_shirt, categoria = "Ropa"),
            Producto(2, "Buso Nike SB", 349900.0, R.drawable.buso_nike,categoria = "Ropa"),
            Producto(3, "Camiseta Vans", 229000.0, R.drawable.dc_shirt,categoria = "Ropa")
        )

        val calzado = listOf(
            Producto(4, "Nike SB Janoski", 320000.0, R.drawable.nike_janoski,categoria = "Skates"),
            Producto(5, "Nike Blazer Low", 299000.0, R.drawable.nike_janoski,categoria = "Skates"),
            Producto(6, "DC Court Graffik", 289000.0, R.drawable.dc_shirt,categoria = "Skates")
        )

        val accesorios = listOf(
            Producto(7, "Gorra Vans", 99000.0, R.drawable.buso_nike,categoria = "Accesorios"),
            Producto(8, "Medias Skate", 29000.0, R.drawable.dc_shirt,categoria = "Accesorios"),
            Producto(9, "Mochila DC", 149000.0, R.drawable.buso_nike,categoria = "Accesorios")
        )

        // Agregar secciones
        items += ShopItem.TituloCategoria("Ropa")
        items += ropa.take(3).map { ShopItem.ProductoItem(it) }

        items += ShopItem.TituloCategoria("Skates")
        items += calzado.take(3).map { ShopItem.ProductoItem(it) }

        items += ShopItem.TituloCategoria("Accesorios")
        items += accesorios.take(3).map { ShopItem.ProductoItem(it) }

        return items
    }
}
