package com.huanco.lab02carritokotlin

data class Producto(
    val nombre: String,
    val precio: Double,
    var cantidad: Int
)

fun main() {
    println("=========================================")
    println(" CARRITO DE COMPRAS - TIENDA TECSUP ")
    println("=========================================")

    val nombreCliente = "Grecia Huanco"
    val carrito = mutableListOf<Producto>()

    println("Cliente: $nombreCliente")
    println()

    carrito.add(Producto("Laptop HP", 2500.0, 1))
    carrito.add(Producto("Mouse Logitech", 45.5, 2))
    carrito.add(Producto("Teclado Mecanico", 180.0, 1))
    carrito.add(Producto("Monitor Samsung", 850.0, 1))

    for (producto in carrito) {
        println("Producto agregado: ${producto.nombre}")
    }

    var subtotal = 0.0

    for (producto in carrito) {
        subtotal += producto.precio * producto.cantidad
    }

    val igv = subtotal * 0.18
}
