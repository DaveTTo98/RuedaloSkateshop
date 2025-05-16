package com.ddeveloper.ruedaloskateshop.data

import com.ddeveloper.ruedaloskateshop.model.Producto

object Carrito {

    private val productos = mutableMapOf<Producto, Int>()

    fun addProduct(producto: Producto) {
        productos[producto] = productos.getOrDefault(producto, 0) + 1
    }

    fun getProductos(): Map<Producto, Int> = productos

    fun limpiar() {
        productos.clear()
    }
}
