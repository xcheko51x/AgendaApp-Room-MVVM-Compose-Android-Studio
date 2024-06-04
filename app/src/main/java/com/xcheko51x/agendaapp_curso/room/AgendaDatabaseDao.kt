package com.xcheko51x.agendaapp_curso.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.xcheko51x.agendaapp_curso.models.Cita
import kotlinx.coroutines.flow.Flow

@Dao
interface AgendaDatabaseDao {
    @Query("SELECT * FROM citas")
    fun obtenerCitas(): Flow<List<Cita>>

    @Insert
    suspend fun agregarCita(cita: Cita)

    @Update
    suspend fun actualizarCita(cita: Cita)

    @Delete
    suspend fun borrarCita(cita: Cita)
}