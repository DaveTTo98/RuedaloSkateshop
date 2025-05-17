package com.ddeveloper.ruedaloskateshop.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ddeveloper.ruedaloskateshop.R
import com.ddeveloper.ruedaloskateshop.model.Producto

class HomeAdapter(private val productos: List<Producto>):
        RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    inner class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombre: TextView = itemView.findViewById(R.id.prod_name)
        val precio: TextView = itemView.findViewById(R.id.prod_price)
        val image: ImageView = itemView.findViewById(R.id.img_prod)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.producto_destacado, parent, false)
        return HomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val producto = productos[position]
        holder.nombre.text = producto.name
        holder.precio.text = "$${producto.price}"
        holder.image.setImageResource(producto.imageId)
    }
    override fun getItemCount(): Int = productos.size
}