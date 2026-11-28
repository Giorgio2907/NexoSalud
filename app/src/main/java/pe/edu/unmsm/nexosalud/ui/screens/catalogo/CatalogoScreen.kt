package pe.edu.unmsm.nexosalud.ui.screens.catalogo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pe.edu.unmsm.nexosalud.model.DiagnosticoViewModel

@Composable
fun CatalogoScreen(viewModel: DiagnosticoViewModel) {
    val diagnosticos by viewModel.diagnosticos.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Catálogo CIE-10 · ${diagnosticos.size} registros",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(16.dp)
        )
        LazyColumn {
            items(diagnosticos, key = { it.id }) { diagnostico ->
                ListItem(
                    headlineContent = { Text(diagnostico.codigo) },
                    supportingContent = { Text(diagnostico.descripcion) },
                    trailingContent = {
                        if (diagnostico.noPertinente) {
                            Text(
                                text = "No pertinente",
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                )
                HorizontalDivider()
            }
        }
    }
}