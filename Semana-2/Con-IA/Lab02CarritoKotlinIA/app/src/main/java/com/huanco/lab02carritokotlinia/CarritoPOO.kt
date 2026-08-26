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
class Carrito {
    private val productos = mutableListOf<Producto>()

    fun agregarProducto(producto: Producto) {
        productos.add(producto)
    }

    fun mostrarProductos() {
        println("-----PRODUCTOS DEL CARRITO----")

        for (producto in productos) {
            println(
                "${producto.nombre} - S/ ${producto.precio} - ${producto.mostrarTipo()}"
            )
        }
    }
}

fun main() {
    println("=====CARRITO DE COMPRAS CON IA - TECSUP=====")

    val nombreCliente = "Grecia Huanco"

    println("Cliente: $nombreCliente")

    println()

    val carrito = Carrito()

    val laptop = ProductoFisico("Laptop HP", 2500.0)
    val mouse = ProductoFisico("Mouse Logitech", 45.5)
    val curso = ProductoDigital("Curso de Kotlin", 150.0)

    carrito.agregarProducto(laptop)
    carrito.agregarProducto(mouse)
    carrito.agregarProducto(curso)

    carrito.mostrarProductos()
}
