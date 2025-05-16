package com.ddeveloper.ruedaloskateshop.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ddeveloper.ruedaloskateshop.R
import com.ddeveloper.ruedaloskateshop.model.Producto

class CartAdapter(
    private val productosCarrito: List<Pair<Producto, Int>>
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    inner class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombre: TextView = itemView.findViewById(R.id.cartItemName)
        val cantidad: TextView = itemView.findViewById(R.id.cartItemQuantity)
        val precioUnitario: TextView = itemView.findViewById(R.id.cartItemPrice)
        val total: TextView = itemView.findViewById(R.id.cartItemTotal)
        val imagen: ImageView = itemView.findViewById(R.id.imgCarritoProducto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cart, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val (producto, cantidad) = productosCarrito[position]
        holder.nombre.text = producto.name
        holder.cantidad.text = "Cantidad: $cantidad"
        holder.precioUnitario.text = "Precio unitario: $${producto.price}"
        holder.total.text = "Total: $${String.format("%.2f", producto.price * cantidad)}"
        holder.imagen.setImageResource(producto.imageId)
    }

    override fun getItemCount(): Int = productosCarrito.size
}
