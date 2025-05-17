package com.ddeveloper.ruedaloskateshop.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ddeveloper.ruedaloskateshop.R
import com.ddeveloper.ruedaloskateshop.model.ImagenCarrusel

class CarruselAdapter(
    private val imagenes: List<ImagenCarrusel>
) : RecyclerView.Adapter<CarruselAdapter.CarruselViewHolder>() {

    inner class CarruselViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imagen: ImageView = itemView.findViewById(R.id.imageCarrusel)
        val texto: TextView = itemView.findViewById(R.id.textoCarrusel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarruselViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_carrusel, parent, false)
        return CarruselViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarruselViewHolder, position: Int) {
        val item = imagenes[position]
        holder.imagen.setImageResource(item.imageResId)
        holder.texto.text = item.titulo
    }

    override fun getItemCount(): Int = imagenes.size
}
