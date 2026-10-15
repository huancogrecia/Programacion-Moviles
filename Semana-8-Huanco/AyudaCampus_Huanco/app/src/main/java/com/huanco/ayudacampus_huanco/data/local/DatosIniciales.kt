package com.huanco.ayudacampus_huanco.data.local

import androidx.room.withTransaction
import com.huanco.ayudacampus_huanco.data.local.entity.Categoria
import com.huanco.ayudacampus_huanco.data.local.entity.Comentario
import com.huanco.ayudacampus_huanco.data.local.entity.Ticket
import com.huanco.ayudacampus_huanco.data.local.entity.Usuario
import com.huanco.ayudacampus_huanco.model.EstadoTicket
import com.huanco.ayudacampus_huanco.model.Prioridad
import com.huanco.ayudacampus_huanco.model.Rol

object DatosIniciales {
    suspend fun sembrar(db: AyudaDatabase) {
        db.withTransaction {
            if (db.usuarioDao().contar() == 0) {
                db.categoriaDao().insertarTodas(listOf(
                    Categoria(id = 1, nombre = "Hardware", icono = "pc"),
                    Categoria(id = 2, nombre = "Software", icono = "code"),
                    Categoria(id = 3, nombre = "Red/Wi-Fi", icono = "wifi"),
                    Categoria(id = 4, nombre = "Cuenta", icono = "key"),
                    Categoria(id = 5, nombre = "Impresión", icono = "printer")
                ))
                val cliente = Usuario(id = 1, nombre = "Grecia Huanco Ticona", correo = "huanco@campus.test", clave = "1234", rol = Rol.CLIENTE.name)
                val soporte = Usuario(id = 2, nombre = "Soporte TI", correo = "soporte@campus.test", clave = "admin", rol = Rol.ADMIN.name)
                db.usuarioDao().insertarTodos(listOf(cliente, soporte))
                val ahora = System.currentTimeMillis()
                val hora = 3600 * 1000L
                val tickets = listOf(
                    Ticket(clienteId = cliente.id, categoriaId = 3, titulo = "Sin conexión Wi-Fi", descripcion = "No puedo conectarme a la red del campus.", ubicacion = "Aula 201", prioridad = Prioridad.ALTA.name, estado = EstadoTicket.ABIERTO.name, fecha = ahora - 6 * hora),
                    Ticket(clienteId = cliente.id, categoriaId = 2, titulo = "Programa no inicia", descripcion = "El programa se cierra cuando intento abrirlo.", ubicacion = "Laboratorio 1", prioridad = Prioridad.MEDIA.name, estado = EstadoTicket.ABIERTO.name, fecha = ahora - hora),
                    Ticket(clienteId = cliente.id, categoriaId = 1, titulo = "Computadora no enciende", descripcion = "La computadora no responde al botón de encendido.", ubicacion = "Laboratorio 2", prioridad = Prioridad.ALTA.name, estado = EstadoTicket.EN_PROCESO.name, fecha = ahora - 8 * hora),
                    Ticket(clienteId = cliente.id, categoriaId = 5, titulo = "Impresora sin respuesta", descripcion = "Los documentos enviados no llegan a imprimirse.", ubicacion = "Biblioteca", prioridad = Prioridad.BAJA.name, estado = EstadoTicket.EN_PROCESO.name, fecha = ahora - 12 * hora),
                    Ticket(clienteId = cliente.id, categoriaId = 4, titulo = "Acceso a cuenta", descripcion = "No puedo ingresar a mi cuenta institucional.", ubicacion = "Aula 202", prioridad = Prioridad.MEDIA.name, estado = EstadoTicket.RESUELTO.name, fecha = ahora - 18 * hora),
                    Ticket(clienteId = cliente.id, categoriaId = 3, titulo = "Conexión inestable", descripcion = "La conexión se interrumpe durante las clases.", ubicacion = "Aula 203", prioridad = Prioridad.BAJA.name, estado = EstadoTicket.CERRADO.name, fecha = ahora - 24 * hora, fechaCierre = ahora - 2 * hora, calificacion = 4)
                )
                val ids = tickets.map { db.ticketDao().insertarTicket(it).toInt() }
                db.ticketDao().insertarComentario(Comentario(ticketId = ids[0], autorId = cliente.id, autorNombre = cliente.nombre, autorRol = cliente.rol, texto = "La red sigue sin conectarse.", fecha = ahora - 5 * hora))
                db.ticketDao().insertarComentario(Comentario(ticketId = ids[2], autorId = soporte.id, autorNombre = soporte.nombre, autorRol = soporte.rol, texto = "Estamos revisando la fuente de alimentación.", fecha = ahora - 7 * hora))
                db.ticketDao().insertarComentario(Comentario(ticketId = ids[4], autorId = soporte.id, autorNombre = soporte.nombre, autorRol = soporte.rol, texto = "Se restableció el acceso a la cuenta.", fecha = ahora - 4 * hora))
                db.ticketDao().insertarComentario(Comentario(ticketId = ids[5], autorId = cliente.id, autorNombre = cliente.nombre, autorRol = cliente.rol, texto = "La conexión ya funciona correctamente.", fecha = ahora - 2 * hora))
            }
        }
    }
}
