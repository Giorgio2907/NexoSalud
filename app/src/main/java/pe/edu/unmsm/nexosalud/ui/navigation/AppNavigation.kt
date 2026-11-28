package pe.edu.unmsm.nexosalud.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.People
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import pe.edu.unmsm.nexosalud.model.PacienteViewModel
import pe.edu.unmsm.nexosalud.ui.screens.catalogo.CatalogoScreen
import pe.edu.unmsm.nexosalud.ui.screens.pacientes.PacientesScreen
import pe.edu.unmsm.nexosalud.model.DiagnosticoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation(
    pacienteViewModel: PacienteViewModel,
    diagnosticoViewModel: DiagnosticoViewModel
) {
    val navController = rememberNavController()
    val entradaActual by navController.currentBackStackEntryAsState()
    val rutaActual = entradaActual?.destination?.route

    val titulo = when (rutaActual) {
        Routes.Catalogo.ruta -> Routes.Catalogo.titulo
        else -> Routes.Pacientes.titulo
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("NEXO SALUD · $titulo") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        bottomBar = { BottomBar(navController, rutaActual) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Routes.Pacientes.ruta,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Routes.Pacientes.ruta) { PacientesScreen(pacienteViewModel) }
            composable(Routes.Catalogo.ruta) { CatalogoScreen(diagnosticoViewModel) }
        }
    }
}

private data class ItemBarra(val ruta: Routes, val icono: ImageVector)

@Composable
private fun BottomBar(navController: NavController, rutaActual: String?) {
    val items = listOf(
        ItemBarra(Routes.Pacientes, Icons.Default.People),
        ItemBarra(Routes.Catalogo, Icons.Default.Description)
    )

    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                selected = rutaActual == item.ruta.ruta,
                onClick = {
                    navController.navigate(item.ruta.ruta) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = { Icon(item.icono, contentDescription = item.ruta.titulo) },
                label = { Text(item.ruta.titulo) }
            )
        }
    }
}