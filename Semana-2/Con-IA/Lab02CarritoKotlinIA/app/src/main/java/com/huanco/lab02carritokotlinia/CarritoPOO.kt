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

    fun calcularSubtotal(): Double {
        var subtotal = 0.0

        for (producto in productos) {
            subtotal += producto.precio
        }

        return subtotal
    }

    fun calcularIGV(subtotal: Double): Double {
        return subtotal * 0.18
    }

    fun calcularTotal(subtotal: Double, igv: Double): Double {
        return subtotal + igv
    }

    fun calcularDescuento(total: Double): Double {
        return when {
            total > 5000 -> total * 0.10
            total > 3000 -> total * 0.05
            else -> 0.0
        }
    }

    fun mostrarProductos() {
        println("----- PRODUCTOS DEL CARRITO -----")

        for (producto in productos) {
            println(
                "${producto.nombre} - S/ ${producto.precio} - ${producto.mostrarTipo()}"
            )
        }
    }
}

fun main() {

    println("===== CARRITO DE COMPRAS CON IA =====")

    print("Ingrese nombre del cliente: ")
    val nombreCliente = readLine() ?: ""

    println("Cliente: $nombreCliente")
    println()

    val carrito = Carrito()

    var continuar = "s"

    while (continuar.lowercase() == "s") {

        print("Ingrese nombre del producto: ")
        val nombre = readLine() ?: ""

        print("Ingrese precio: ")
        val precio = readLine()?.toDoubleOrNull() ?: 0.0

        print("Ingrese tipo (1-Fisico / 2-Digital): ")
        val tipo = readLine() ?: ""

        if (tipo == "1") {
            carrito.agregarProducto(
                ProductoFisico(nombre, precio)
            )
        } else {
            carrito.agregarProducto(
                ProductoDigital(nombre, precio)
            )
        }

        print("¿Desea agregar otro producto? (s/n): ")
        continuar = readLine() ?: "n"

        println()
    }

    carrito.mostrarProductos()

    println()

    val subtotal = carrito.calcularSubtotal()
    val igv = carrito.calcularIGV(subtotal)
    val total = carrito.calcularTotal(subtotal, igv)
    val descuento = carrito.calcularDescuento(total)
    val totalFinal = total - descuento

    println("----------- RESUMEN -----------")
    println("Cliente: $nombreCliente")
    println(String.format("Subtotal:    S/ %.2f", subtotal))
    println(String.format("IGV (18%%):   S/ %.2f", igv))
    println(String.format("Total:       S/ %.2f", total))
    println(String.format("Descuento:   S/ %.2f", descuento))
    println(String.format("Total final: S/ %.2f", totalFinal))
}