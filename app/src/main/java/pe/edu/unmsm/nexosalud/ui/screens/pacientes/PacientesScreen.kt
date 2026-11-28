package pe.edu.unmsm.nexosalud.ui.screens.pacientes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import pe.edu.unmsm.nexosalud.model.PacienteViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun PacientesScreen(viewModel: PacienteViewModel) {
    val pacientes by viewModel.pacientes.collectAsState()
    val mensaje by viewModel.mensaje.collectAsState()

    var codigo by rememberSaveable { mutableStateOf("") }
    var busqueda by rememberSaveable { mutableStateOf("") }

    val filtrados = remember(pacientes, busqueda) {
        if (busqueda.isBlank()) pacientes
        else pacientes.filter {
            it.codigoAnonimo.contains(busqueda.trim(), ignoreCase = true)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Registrar paciente", style = MaterialTheme.typography.titleMedium)

        OutlinedTextField(
            value = codigo,
            onValueChange = {
                codigo = it.take(20).uppercase()
                viewModel.mensajeMostrado()
            },
            label = { Text("Código anonimizado") },
            supportingText = { Text("Solo un código. Nunca escriba nombres ni DNI.") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Characters),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        )

        Button(
            onClick = { viewModel.registrar(codigo) { codigo = "" } },
            enabled = codigo.isNotBlank(),
            modifier = Modifier.padding(top = 4.dp)
        ) {
            Text("Registrar")
        }

        mensaje?.let {
            Text(
                text = it.texto,
                color = if (it.esError) MaterialTheme.colorScheme.error
                else MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Spacer(Modifier.height(16.dp))
        HorizontalDivider()
        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = busqueda,
            onValueChange = { busqueda = it },
            label = { Text("Buscar por código") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = "Mostrando ${filtrados.size} de ${pacientes.size}",
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        if (filtrados.isEmpty()) {
            Text("Sin resultados.")
        }

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(filtrados, key = { it.id }) { paciente ->
                ListItem(
                    headlineContent = { Text(paciente.codigoAnonimo) },
                    supportingContent = {
                        Text("Registrado: ${fechaLegible(paciente.fechaRegistro)}")
                    }
                )
                HorizontalDivider()
            }
        }
    }
}

private fun fechaLegible(iso: String): String =
    try {
        LocalDateTime.parse(iso).format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
    } catch (e: Exception) {
        iso
    }