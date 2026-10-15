package com.huanco.ayudacampus_huanco.data.repository

import com.huanco.ayudacampus_huanco.data.local.AyudaDatabase
import com.huanco.ayudacampus_huanco.data.local.entity.Categoria
import com.huanco.ayudacampus_huanco.data.local.entity.Comentario
import com.huanco.ayudacampus_huanco.data.local.entity.Ticket
import com.huanco.ayudacampus_huanco.data.local.entity.Usuario
import com.huanco.ayudacampus_huanco.data.local.relation.TicketConDetalle
import com.huanco.ayudacampus_huanco.model.ConteoCategoria
import com.huanco.ayudacampus_huanco.model.EstadoTicket
import com.huanco.ayudacampus_huanco.model.Prioridad
import kotlinx.coroutines.flow.Flow

class AyudaRepositoryRoom(private val db: AyudaDatabase) : AyudaRepository {
    override suspend fun login(correo: String, clave: String): Usuario? =
        db.usuarioDao().login(correo, clave)

    override fun observarCategorias(): Flow<List<Categoria>> = db.categoriaDao().observarTodas()

    override fun observarCategoriasActivas(): Flow<List<Categoria>> = db.categoriaDao().observarActivas()

    override suspend fun guardarCategoria(c: Categoria) {
        if (c.id == 0) db.categoriaDao().insertar(c) else db.categoriaDao().actualizar(c)
    }

    override suspend fun eliminarCategoria(c: Categoria) {
        db.categoriaDao().eliminar(c)
    }

    override suspend fun cambiarActiva(id: Int, activa: Boolean) {
        db.categoriaDao().cambiarActiva(id, activa)
    }

    override suspend fun crearTicket(
        clienteId: Int,
        categoriaId: Int,
        titulo: String,
        descripcion: String,
        ubicacion: String,
        prioridad: Prioridad
    ): Long {
        val ticket = Ticket(
            clienteId = clienteId,
            categoriaId = categoriaId,
            titulo = titulo,
            descripcion = descripcion,
            ubicacion = ubicacion,
            prioridad = prioridad.name,
            estado = EstadoTicket.ABIERTO.name,
            fecha = System.currentTimeMillis()
        )
        return db.ticketDao().insertarTicket(ticket)
    }

    override fun observarTicketsCliente(clienteId: Int): Flow<List<TicketConDetalle>> =
        db.ticketDao().observarPorCliente(clienteId)

    override fun observarTodosLosTickets(): Flow<List<TicketConDetalle>> =
        db.ticketDao().observarTodos()

    override fun observarTicket(id: Int): Flow<TicketConDetalle?> = db.ticketDao().observarUno(id)

    override suspend fun cambiarEstado(ticketId: Int, estado: EstadoTicket) {
        db.ticketDao().actualizarEstado(ticketId, estado.name)
    }

    override suspend fun cambiarPrioridad(ticketId: Int, prioridad: Prioridad) {
        db.ticketDao().actualizarPrioridad(ticketId, prioridad.name)
    }

    override suspend fun cerrarConCalificacion(ticketId: Int, calificacion: Int) {
        db.ticketDao().cerrar(ticketId, calificacion, System.currentTimeMillis())
    }

    override suspend fun reabrir(ticketId: Int) {
        db.ticketDao().reabrir(ticketId)
    }

    override suspend fun agregarComentario(ticketId: Int, autor: Usuario, texto: String) {
        val comentario = Comentario(
            ticketId = ticketId,
            autorId = autor.id,
            autorNombre = autor.nombre,
            autorRol = autor.rol,
            texto = texto,
            fecha = System.currentTimeMillis()
        )
        db.ticketDao().insertarComentario(comentario)
    }

    override fun observarCantidadPorEstado(estado: EstadoTicket): Flow<Int> =
        db.ticketDao().contarPorEstado(estado.name)

    override fun observarCantidadPorCategoria(): Flow<List<ConteoCategoria>> =
        db.ticketDao().contarPorCategoria()

    override fun observarAltasSinAtender(limite: Long): Flow<Int> =
        db.ticketDao().contarAltasSinAtender(limite)
}
