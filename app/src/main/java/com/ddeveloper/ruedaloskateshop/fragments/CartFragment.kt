package com.ddeveloper.ruedaloskateshop.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ddeveloper.ruedaloskateshop.R
import com.ddeveloper.ruedaloskateshop.adapter.CartAdapter
import com.ddeveloper.ruedaloskateshop.data.Carrito

class CartFragment : Fragment() {

    private lateinit var recyclerCart: RecyclerView
    private lateinit var totalTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerCart = view.findViewById(R.id.recyclerCart)
        totalTextView = view.findViewById(R.id.txtTotalCarrito)

        val productosEnCarrito = Carrito.getProductos().toList()


        recyclerCart.layoutManager = LinearLayoutManager(requireContext())
        recyclerCart.adapter = CartAdapter(productosEnCarrito)


        val total = productosEnCarrito.sumOf { it.first.price * it.second }
        totalTextView.text = "Total a pagar: $${String.format("%.2f", total)}"
    }
}
