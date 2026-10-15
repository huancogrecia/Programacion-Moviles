package com.huanco.ayudacampus_huanco.data.local
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.huanco.ayudacampus_huanco.data.local.dao.CategoriaDao
import com.huanco.ayudacampus_huanco.data.local.dao.UsuarioDao
import com.huanco.ayudacampus_huanco.data.local.dao.TicketDao
import com.huanco.ayudacampus_huanco.data.local.entity.Categoria
import com.huanco.ayudacampus_huanco.data.local.entity.Usuario
import com.huanco.ayudacampus_huanco.data.local.entity.Ticket
import com.huanco.ayudacampus_huanco.data.local.entity.Comentario
@Database(entities = [Categoria::class, Usuario::class, Ticket::class, Comentario::class], version = 1, exportSchema = false)
abstract class AyudaDatabase : RoomDatabase() {
    abstract fun categoriaDao(): CategoriaDao
    abstract fun usuarioDao(): UsuarioDao
    abstract fun ticketDao(): TicketDao
    companion object {
        @Volatile private var INSTANCE: AyudaDatabase? = null
        fun obtener(context: Context): AyudaDatabase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: Room.databaseBuilder(context.applicationContext, AyudaDatabase::class.java, "ayudacampus.db")
                .build().also { INSTANCE = it }
        }
    }
}
