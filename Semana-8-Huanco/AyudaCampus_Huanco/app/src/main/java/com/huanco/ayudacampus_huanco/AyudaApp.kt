package com.huanco.ayudacampus_huanco
import android.app.Application
import com.huanco.ayudacampus_huanco.data.local.AyudaDatabase
import com.huanco.ayudacampus_huanco.data.local.DatosIniciales
import com.huanco.ayudacampus_huanco.data.repository.AyudaRepository
import com.huanco.ayudacampus_huanco.data.repository.AyudaRepositoryRoom
import kotlinx.coroutines.*
class AyudaApp : Application() {
    val database by lazy { AyudaDatabase.obtener(this) }
    val repository: AyudaRepository by lazy { AyudaRepositoryRoom(database) }
    private val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    override fun onCreate() {
        super.onCreate()
        applicationScope.launch { DatosIniciales.sembrar(database) }
    }
}
