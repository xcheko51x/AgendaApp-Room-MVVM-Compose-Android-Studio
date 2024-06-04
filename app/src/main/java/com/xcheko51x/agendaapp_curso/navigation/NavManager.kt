package com.xcheko51x.agendaapp_curso.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.xcheko51x.agendaapp_curso.viewmodels.AgendaViewModel
import com.xcheko51x.agendaapp_curso.views.AgregarCitaView
import com.xcheko51x.agendaapp_curso.views.EditarCitaView
import com.xcheko51x.agendaapp_curso.views.InicioView

@Composable
fun NavManager(
    viewModel: AgendaViewModel
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "inicio"
    ) {

        composable("inicio") { InicioView(navController, viewModel) }

        composable("agregar") { AgregarCitaView(navController, viewModel) }

        composable(
            "editar/{idCita}/{nomPaciente}/{telPaciente}/{asunto}/{diaCita}/{horaCita}",
            arguments = listOf(
                navArgument("idCita") { type = NavType.StringType },
                navArgument("nomPaciente") { type = NavType.StringType },
                navArgument("telPaciente") { type = NavType.StringType },
                navArgument("asunto") { type = NavType.StringType },
                navArgument("diaCita") { type = NavType.StringType },
                navArgument("horaCita") { type = NavType.StringType }
            )
        ) {
            EditarCitaView(
                navController,
                viewModel,
                it.arguments?.getString("idCita")!!,
                it.arguments?.getString("nomPaciente")!!,
                it.arguments?.getString("telPaciente")!!,
                it.arguments?.getString("asunto")!!,
                it.arguments?.getString("diaCita")!!,
                it.arguments?.getString("horaCita")!!,
            )
        }
    }
}