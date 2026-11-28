package pe.edu.unmsm.nexosalud

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import pe.edu.unmsm.nexosalud.data.local.AppDatabase
import pe.edu.unmsm.nexosalud.data.repository.PacienteRepository
import pe.edu.unmsm.nexosalud.model.PacienteViewModel
import pe.edu.unmsm.nexosalud.model.PacienteViewModelFactory
import pe.edu.unmsm.nexosalud.ui.navigation.AppNavigation
import pe.edu.unmsm.nexosalud.ui.theme.NexoSaludTheme

class MainActivity : ComponentActivity() {

    private val pacienteViewModel: PacienteViewModel by viewModels {
        PacienteViewModelFactory(
            PacienteRepository(AppDatabase.getDatabase(this).pacienteDao())
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NexoSaludTheme {
                AppNavigation(pacienteViewModel)
            }
        }
    }
}