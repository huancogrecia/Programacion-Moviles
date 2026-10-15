package com.huanco.ayudacampus_huanco.data.prefs

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore("ajustes")

class PreferenciasStore(context: Context) {
    private val dataStore = context.applicationContext.dataStore
    private val clavePerfilActivo = stringPreferencesKey("perfilActivo")
    private val claveUsuarioId = intPreferencesKey("usuarioId")
    private val claveModoOscuro = booleanPreferencesKey("modoOscuro")
    private val claveNotificaciones = booleanPreferencesKey("notificaciones")
    private val claveRecibirTickets = booleanPreferencesKey("recibirTickets")

    val perfilActivo: Flow<String> = dataStore.data.map { it[clavePerfilActivo] ?: "" }
    val usuarioId: Flow<Int> = dataStore.data.map { it[claveUsuarioId] ?: 0 }
    val modoOscuro: Flow<Boolean> = dataStore.data.map { it[claveModoOscuro] ?: false }
    val notificaciones: Flow<Boolean> = dataStore.data.map { it[claveNotificaciones] ?: true }
    val recibirTickets: Flow<Boolean> = dataStore.data.map { it[claveRecibirTickets] ?: true }

    suspend fun guardarSesion(rol: String, usuarioId: Int) {
        dataStore.edit {
            it[clavePerfilActivo] = rol
            it[claveUsuarioId] = usuarioId
        }
    }

    suspend fun guardarModoOscuro(b: Boolean) {
        dataStore.edit { it[claveModoOscuro] = b }
    }

    suspend fun guardarNotificaciones(b: Boolean) {
        dataStore.edit { it[claveNotificaciones] = b }
    }

    suspend fun guardarRecibirTickets(b: Boolean) {
        dataStore.edit { it[claveRecibirTickets] = b }
    }

    suspend fun cerrarSesion() {
        dataStore.edit {
            it.remove(clavePerfilActivo)
            it.remove(claveUsuarioId)
        }
    }
}
