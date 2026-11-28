package pe.edu.unmsm.nexosalud.ui.navigation

sealed class Routes(val ruta: String, val titulo: String) {
    object Pacientes : Routes("pacientes", "Pacientes")
    object Catalogo : Routes("catalogo", "Catálogo CIE-10")
}