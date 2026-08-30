package com.huanco.prestamos

fun main() {

    println("***CONTROL DE PRESTAMO***")


    println("Ingrese tItulo del libro:")
    val titulo = readln()

    println("Ingrese fecha de prestamo:")
    val fechaPrestamo = readln()

    println("Ingrese fecha de entrega:")
    val fechaEntrega = readln()

    println("Ingrese fecha de devolucion:")
    val fechaDevolucion = readln()


    println()
    println("***DATOS REGISTRADOS***")
    println("Titulo: $titulo")
    println("Fecha prestamo: $fechaPrestamo")
    println("Fecha entrega: $fechaEntrega")
    println("Fecha devolucion: $fechaDevolucion")
}