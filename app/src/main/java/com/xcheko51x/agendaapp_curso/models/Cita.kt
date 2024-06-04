package com.xcheko51x.agendaapp_curso.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "citas")
data class Cita(
    @PrimaryKey(autoGenerate = false)
    val idCita: String,
    @ColumnInfo("nomPaciente")
    var nomPaciente: String,
    @ColumnInfo("telPaciente")
    val telPaciente: String,
    @ColumnInfo("asunto")
    val asunto: String,
    @ColumnInfo("diaCita")
    val diaCita: String,
    @ColumnInfo("horaCita")
    val horaCita: String
)