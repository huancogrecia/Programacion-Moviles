package com.huanco.ayudacampus_huanco.data.local
import androidx.room.withTransaction
import com.huanco.ayudacampus_huanco.data.local.entity.Categoria
object DatosIniciales {
    suspend fun sembrar(db: AyudaDatabase) {
        db.withTransaction {
            if (db.categoriaDao().contar() == 0) {
                db.categoriaDao().insertarTodas(listOf(
                    Categoria(nombre = "Hardware", icono = "pc"),
                    Categoria(nombre = "Software", icono = "code"),
                    Categoria(nombre = "Red/Wi-Fi", icono = "wifi"),
                    Categoria(nombre = "Cuenta", icono = "key"),
                    Categoria(nombre = "Impresión", icono = "printer")
                ))
            }
        }
    }
}
