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

    println()
    println("Ingrese la fecha de Prestamo (dia/mes/año):")
    val fechaPrestamo = readln()

    println("Ingrese la fecha de Entrega (dia/mes/año):")
    val fechaEntrega = readln()

    println("Ingrese fecha devolucion (dia/mes/año):")
    val fechaDevolucion = readln()

    val partesEntrega = fechaEntrega.split("/")

    var diaActual = partesEntrega[0].toInt()
    var mesActual = partesEntrega[1].toInt()
    var anioActual = partesEntrega[2].toInt()

    val partesDevolucion = fechaDevolucion.split("/")

    val diaDevolucion = partesDevolucion[0].toInt()
    val mesDevolucion = partesDevolucion[1].toInt()
    val anioDevolucion = partesDevolucion[2].toInt()

    var diasAtraso = 0

    while (
        anioActual < anioDevolucion ||
        (anioActual == anioDevolucion && mesActual < mesDevolucion) ||
        (anioActual == anioDevolucion &&
                mesActual == mesDevolucion &&
                diaActual < diaDevolucion)
    ) {

        diaActual++

        val diasMes = when (mesActual) {
            1, 3, 5, 7, 8, 10, 12 -> 31
            4, 6, 9, 11 -> 30
            2 -> 28
            else -> 0
        }

        if (diaActual > diasMes) {
            diaActual = 1
            mesActual++

            if (mesActual > 12) {
                mesActual = 1
                anioActual++
            }
        }

        diasAtraso++
    }

    val multaTotal = diasAtraso * multaPorDia

    val estado = if (diasAtraso > 0) {
        "Devuelto con $diasAtraso dias de atraso"
    } else {
        "Devuelto a tiempo"
    }

    println()
    println("+++SISTEMA DE DEVOLUCION DE LIBROS+++")
    println("Titulo del Libro: $titulo")
    println("Tipo de Usuario: $usuario")
    println("Fecha de Prestamo: $fechaPrestamo")
    println("Fecha de Entrega: $fechaEntrega")
    println("Fecha de Devolucion: $fechaDevolucion")
    println("Estado: $estado")

    println()
    println("+++Detalle de Multa+++")
    println("Dias | Fecha       | Multa Diaria | Multa Acumulada")

    var multaAcumulada = 0.0

    diaActual = partesEntrega[0].toInt()
    mesActual = partesEntrega[1].toInt()
    anioActual = partesEntrega[2].toInt()

    for (dia in 1..diasAtraso) {

        diaActual++

        val diasMes = when (mesActual) {
            1, 3, 5, 7, 8, 10, 12 -> 31
            4, 6, 9, 11 -> 30
            2 -> 28
            else -> 0
        }

        if (diaActual > diasMes) {
            diaActual = 1
            mesActual++

            if (mesActual > 12) {
                mesActual = 1
                anioActual++
            }
        }

        multaAcumulada += multaPorDia

        val fechaMulta = "$diaActual/$mesActual/$anioActual"

        println(
            "$dia    | $fechaMulta | S/ %.2f       | S/ %.2f".format(
                multaPorDia,
                multaAcumulada
            )
        )
    }

    println()
    println("+++DEUDA TOTAL+++")
    println("Total a pagar: S/ %.2f soles".format(multaTotal))
}