package com.xcheko51x.agendaapp_curso.viewmodels

import android.app.Person
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xcheko51x.agendaapp_curso.models.Cita
import com.xcheko51x.agendaapp_curso.room.AgendaDatabaseDao
import com.xcheko51x.agendaapp_curso.states.AgendaState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.time.Period

class AgendaViewModel(
    private val dao: AgendaDatabaseDao
): ViewModel() {

    var state by mutableStateOf(AgendaState())
        private set

    init {
        viewModelScope.launch {
            dao.obtenerCitas().collectLatest {
                state = state.copy(listaCitas = it)
            }
        }
    }

    fun agregarCita(cita: Cita) = viewModelScope.launch {
        dao.agregarCita(cita = cita)
    }

    fun actualizarCita(cita: Cita) = viewModelScope.launch {
        dao.actualizarCita(cita = cita)
    }

    fun borrarCita(cita: Cita) = viewModelScope.launch {
        dao.borrarCita(cita = cita)
    }

}