package pe.edu.unmsm.nexosalud.ui.screens.pacientes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pe.edu.unmsm.nexosalud.model.PacienteViewModel

@Composable
fun PacientesScreen(viewModel: PacienteViewModel) {
    val pacientes by viewModel.pacientes.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Pacientes",
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            text = "Prototipo: aquí se registrarán y consultarán los pacientes con su código anonimizado.",
            modifier = Modifier.padding(top = 8.dp)
        )
        Text(
            text = "Registrados: ${pacientes.size}",
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}