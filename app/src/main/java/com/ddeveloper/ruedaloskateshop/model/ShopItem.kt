package com.ddeveloper.ruedaloskateshop.model

sealed class ShopItem {
    data class TituloCategoria(val titulo: String) : ShopItem()
    data class ProductoItem(val producto: Producto) : ShopItem()
}
