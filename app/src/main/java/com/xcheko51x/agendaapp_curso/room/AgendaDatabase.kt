package com.xcheko51x.agendaapp_curso.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.xcheko51x.agendaapp_curso.models.Cita

@Database(
    entities = [Cita::class],
    version = 1,
    exportSchema = false
)
abstract class AgendaDatabase: RoomDatabase() {
    abstract fun citasDao(): AgendaDatabaseDao
}