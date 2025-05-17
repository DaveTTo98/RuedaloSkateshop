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
import com.ddeveloper.ruedaloskateshop.model.ShopItem

class ProductAdapter(
    private val items: List<ShopItem>,
    private val onAddClick: (Producto) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_TITLE = 0
        private const val VIEW_TYPE_PRODUCT = 1
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is ShopItem.TituloCategoria -> VIEW_TYPE_TITLE
            is ShopItem.ProductoItem -> VIEW_TYPE_PRODUCT
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_TITLE) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_categoria_header, parent, false)
            TituloViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_producto, parent, false)
            ProductoViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = items[position]) {
            is ShopItem.TituloCategoria -> (holder as TituloViewHolder).bind(item.titulo)
            is ShopItem.ProductoItem -> (holder as ProductoViewHolder).bind(item.producto)
        }
    }

    override fun getItemCount(): Int = items.size

    inner class ProductoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val productname: TextView = itemView.findViewById(R.id.ItemName)
        private val price: TextView = itemView.findViewById(R.id.ItemPrice)
        private val image: ImageView = itemView.findViewById(R.id.imgProducto)
        private val addButton: Button = itemView.findViewById(R.id.btnAgregarCarrito)

        fun bind(producto: Producto) {
            productname.text = producto.name
            price.text = "$${producto.price}"
            image.setImageResource(producto.imageId)
            addButton.setOnClickListener { onAddClick(producto) }
        }
    }

    inner class TituloViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tituloText: TextView = itemView.findViewById(R.id.txtCategoriaTitulo)
        private val iconCategoria: ImageView = itemView.findViewById(R.id.iconCategoria)

        fun bind(categoria: String) {
            tituloText.text = categoria
            val icono = when (categoria) {
                "Ropa" -> R.drawable.dc_shirt
                "Skates" -> R.drawable.skate
                "Accesorios" -> R.drawable.nike_janoski
                else -> R.drawable.main_logo
            }
            iconCategoria.setImageResource(icono)
        }
    }
}
