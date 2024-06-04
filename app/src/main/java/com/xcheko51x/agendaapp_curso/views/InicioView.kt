package com.xcheko51x.agendaapp_curso.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.xcheko51x.agendaapp_curso.viewmodels.AgendaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InicioView(
    navController: NavController,
    viewModel: AgendaViewModel
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "AgendaApp",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("agregar")
                },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Agregar Cita"
                )
            }
        }
    ) {
        InicioContentView(it, navController, viewModel)
    }
}

@Composable
fun InicioContentView(
    it: PaddingValues,
    navController: NavController,
    viewModel: AgendaViewModel
) {
    val txtPaciente = remember { mutableStateOf("") }
    val state = viewModel.state

    Column(
        modifier = Modifier
            .padding(it)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                value = txtPaciente.value,
                placeholder = {
                    Text(text = "Nombre del paciente a buscar ...")
                },
                onValueChange = {
                    txtPaciente.value = it
                },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null
                    )
                },
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
        }

        LazyColumn {
            items(
                state.listaCitas.filter {
                    it.nomPaciente.toLowerCase().contains(txtPaciente.value.toLowerCase())
                }.sortedBy {
                    it.diaCita
                }.sortedByDescending {
                    it.horaCita
                }
            ) {

                var color: Color = Color.White

                when (it.diaCita) {
                    "Lunes" -> { color = Color.Red }
                    "Martes" -> { color = Color.Blue }
                    "Miercoles" -> { color = Color.Gray }
                    "Jueves" -> { color = Color.Cyan }
                    "Viernes" -> { color = Color.Green }
                    "Sabado" -> { color = Color.Magenta }
                    "Domingo" -> { color = Color.LightGray }
                }

                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = color,
                        contentColor = Color.White
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 6.dp
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(12.dp)
                    ) {
                        Text(
                            text = it.nomPaciente,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                        Text(
                            text = it.diaCita,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                        Text(
                            text = it.horaCita,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            IconButton(
                                onClick = {
                                    navController.navigate(
                                        "editar/${it.idCita}/${it.nomPaciente}/${it.telPaciente}/${it.asunto}/${it.diaCita}/${it.horaCita}")
                                }
                            ) {
                                Icon(imageVector = Icons.Default.Edit, contentDescription = "Editar")
                            }
                            IconButton(
                                onClick = { viewModel.borrarCita(it) }
                            ) {
                                Icon(imageVector = Icons.Default.Delete, contentDescription = "Borrar")
                            }
                        }
                    }
                }
            }
        }
    }
}