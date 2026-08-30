package com.huanco.prestamos

fun main() {

    println("***CONTROL DE PRESTAMO***")


    println("Ingrese titulo del libro:")
    val titulo = readln()

    println()
    println("Seleccione usuario:")
    println("1. Alumno")
    println("2. Docente")

    val opcionUsuario = readln().toInt()

    val usuario = when (opcionUsuario) {
        1 -> "Alumno"
        2 -> "Docente"
        else -> "No valido"
    }


    println("Ingrese fecha de prestamo:")
    val fechaPrestamo = readln()

    println("Ingrese fecha de entrega:")
    val fechaEntrega = readln()

    println("Ingrese fecha de devolucion:")
    val fechaDevolucion = readln()

    val diaEntrega = fechaEntrega.substringBefore("/").toInt()
    val diaDevolucion = fechaDevolucion.substringBefore("/").toInt()

    var diasAtraso = diaDevolucion - diaEntrega

    if (diasAtraso < 0) {
        diasAtraso = 0
    }

    val multaPorDia = 1.50
    val multaTotal = diasAtraso * multaPorDia


    println()
    println("***DATOS REGISTRADOS***")
    println("Titulo: $titulo")
    println("Fecha prestamo: $fechaPrestamo")
    println("Fecha entrega: $fechaEntrega")
    println("Fecha devolucion: $fechaDevolucion")
    println("Usuario: $usuario")
    println("Dias de atraso: $diasAtraso")
    println("Multa por dia: S/ $multaPorDia")
    println("Multa total: S/ $multaTotal")
}