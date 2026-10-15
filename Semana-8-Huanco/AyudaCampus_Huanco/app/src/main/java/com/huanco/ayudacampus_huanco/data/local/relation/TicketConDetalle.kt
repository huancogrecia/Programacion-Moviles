package com.huanco.ayudacampus_huanco.data.local.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.huanco.ayudacampus_huanco.data.local.entity.Categoria
import com.huanco.ayudacampus_huanco.data.local.entity.Comentario
import com.huanco.ayudacampus_huanco.data.local.entity.Ticket
import com.huanco.ayudacampus_huanco.data.local.entity.Usuario

data class TicketConDetalle(
    @Embedded val ticket: Ticket,
    @Relation(parentColumn = "id", entityColumn = "ticketId")
    val comentarios: List<Comentario>,
    @Relation(parentColumn = "categoriaId", entityColumn = "id")
    val categoria: Categoria,
    @Relation(parentColumn = "clienteId", entityColumn = "id")
    val cliente: Usuario
)
