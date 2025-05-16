package com.ddeveloper.ruedaloskateshop.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ddeveloper.ruedaloskateshop.R
import com.ddeveloper.ruedaloskateshop.model.Producto

class ProductAdapter (
    private val productos: List<Producto>,
    private val onAddClick: (Producto) -> Unit
): RecyclerView.Adapter<ProductAdapter.ProductoViewHolder>(){

    inner class ProductoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val productname: TextView = itemView.findViewById(R.id.ItemName)
        val price: TextView = itemView.findViewById(R.id.ItemPrice)
        val image: ImageView = itemView.findViewById(R.id.imgProducto)
        val addButton: Button = itemView.findViewById(R.id.btnAgregarCarrito)

    }

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_producto, parent, false)
        return ProductoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int ) {
        val producto = productos[position]
        holder.productname.text = producto.name
        holder.price.text = "$${producto.price}"
        holder.image.setImageResource(producto.imageId)
        holder.addButton.setOnClickListener {
            onAddClick(producto)
        }

    }

    override fun getItemCount(): Int = productos.size









}