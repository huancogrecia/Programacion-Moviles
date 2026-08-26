package com.huanco.lab02carritokotlinia

abstract class Producto(
    val nombre: String,
    val precio: Double
) {
    abstract fun mostrarTipo(): String
}


class ProductoFisico(
    nombre: String,
    precio: Double
) : Producto(nombre, precio) {

    override fun mostrarTipo(): String {
        return "Producto fisico"
    }
}

class ProductoDigital(
    nombre: String,
    precio: Double
) : Producto(nombre, precio) {

    override fun mostrarTipo(): String {
        return "Producto digital"
    }
}


fun main() {
    println("=====CARRITO DE COMPRAS CON IA - TECSUP=====")

    val nombreCliente = "Grecia Huanco"

    println("Cliente: $nombreCliente")
}