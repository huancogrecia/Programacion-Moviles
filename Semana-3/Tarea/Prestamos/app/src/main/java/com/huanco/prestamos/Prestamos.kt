package com.huanco.prestamos

fun main() {

    println("+++INGRESO DE DATOS+++")
    println()

    println("Ingresa el titulo del Libro:")
    val titulo = readln()

    println()
    println("Selecciona el tipo de Usuario:")
    println("1. Alumno")
    println("2. Docente")
    val opcionUsuario = readln().toInt()

    val usuario = when (opcionUsuario) {
        1 -> "Alumno"
        2 -> "Docente"
        else -> "No valido"
    }

    val multaPorDia = when (opcionUsuario) {
        1 -> 1.50
        2 -> 3.00
        else -> 0.00
    }

    println("Ingrese la fecha de Prestamo:")
    val fechaPrestamo = readln()

    println("Ingrese la fecha de Entrega:")
    val fechaEntrega = readln()

    println("Ingrese fecha devolucion:")
    val fechaDevolucion = readln()

    val diaEntrega = fechaEntrega.substringBefore("/").toInt()
    val diaDevolucion = fechaDevolucion.substringBefore("/").toInt()

    var diasAtraso = diaDevolucion - diaEntrega

    if (diasAtraso < 0) {
        diasAtraso = 0
    }

    val multaTotal = diasAtraso * multaPorDia

    val estado = if (diasAtraso > 0) {
        "Devuelto con $diasAtraso dias de atraso"
    } else {
        "Devuelto a tiempo"
    }

    println("SISTEMA DE DEVOLUCION DE LIBROS")
    println("Titulo del Libro: $titulo")
    println("Tipo de Usuario:  $usuario")
    println("Fecha de Prestamo: $fechaPrestamo")
    println("Fecha de Entrega:  $fechaEntrega")
    println("Fecha de Devolucion: $fechaDevolucion")
    println("Estado: $estado")

    println()
    println("+++Detalle de Multa+++")
    println("Dias | Fecha       | Multa Diaria | Multa Acumulada")

    var multaAcumulada = 0.0

    val partesFecha = fechaEntrega.split("/")
    val mes = partesFecha[1]

    for (dia in 1..diasAtraso) {

        multaAcumulada += multaPorDia

        val fechaMulta = "${diaEntrega + dia}/$mes"

        println(
            "$dia    | $fechaMulta     | S/ %.2f       | S/ %.2f".format(
                multaPorDia,
                multaAcumulada
            )
        )
    }
    println()
    println("+++DEUDA TOTAL+++")
    println("Total a pagar: S/ %.2f soles".format(multaTotal))
}