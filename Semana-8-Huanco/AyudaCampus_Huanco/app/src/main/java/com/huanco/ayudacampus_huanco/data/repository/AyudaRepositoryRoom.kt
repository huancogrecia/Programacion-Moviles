package com.huanco.ayudacampus_huanco.data.repository
import kotlinx.coroutines.flow.Flow
import com.huanco.ayudacampus_huanco.data.local.AyudaDatabase
import com.huanco.ayudacampus_huanco.data.local.entity.*
import com.huanco.ayudacampus_huanco.data.local.relation.TicketConDetalle
import com.huanco.ayudacampus_huanco.model.*
class AyudaRepositoryRoom(private val db: AyudaDatabase) : AyudaRepository {
    override suspend fun login(correo: String, clave: String): Usuario? = TODO("Paso 4: implementar esta operación")
    override fun observarCategorias(): Flow<List<Categoria>> = db.categoriaDao().observarTodas()
    override fun observarCategoriasActivas(): Flow<List<Categoria>> = db.categoriaDao().observarActivas()
    override suspend fun guardarCategoria(c: Categoria) { if (c.id == 0) db.categoriaDao().insertar(c) else db.categoriaDao().actualizar(c) }
    override suspend fun eliminarCategoria(c: Categoria) { db.categoriaDao().eliminar(c) }
    override suspend fun cambiarActiva(id: Int, activa: Boolean) { db.categoriaDao().cambiarActiva(id, activa) }
    override suspend fun crearTicket(clienteId: Int, categoriaId: Int, titulo: String, descripcion: String, ubicacion: String, prioridad: Prioridad): Long = TODO("Paso 4: implementar esta operación")
    override fun observarTicketsCliente(clienteId: Int): Flow<List<TicketConDetalle>> = TODO("Paso 4: implementar esta operación")
    override fun observarTodosLosTickets(): Flow<List<TicketConDetalle>> = TODO("Paso 4: implementar esta operación")
    override fun observarTicket(id: Int): Flow<TicketConDetalle?> = TODO("Paso 4: implementar esta operación")
    override suspend fun cambiarEstado(ticketId: Int, estado: EstadoTicket) = TODO("Paso 4: implementar esta operación")
    override suspend fun cambiarPrioridad(ticketId: Int, prioridad: Prioridad) = TODO("Paso 4: implementar esta operación")
    override suspend fun cerrarConCalificacion(ticketId: Int, calificacion: Int) = TODO("Paso 4: implementar esta operación")
    override suspend fun reabrir(ticketId: Int) = TODO("Paso 4: implementar esta operación")
    override suspend fun agregarComentario(ticketId: Int, autor: Usuario, texto: String) = TODO("Paso 4: implementar esta operación")
    override fun observarCantidadPorEstado(estado: EstadoTicket): Flow<Int> = TODO("Paso 4: implementar esta operación")
    override fun observarCantidadPorCategoria(): Flow<List<ConteoCategoria>> = TODO("Paso 4: implementar esta operación")
    override fun observarAltasSinAtender(limite: Long): Flow<Int> = TODO("Paso 4: implementar esta operación")
}
