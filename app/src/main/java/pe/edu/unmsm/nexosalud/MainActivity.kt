package pe.edu.unmsm.nexosalud

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import pe.edu.unmsm.nexosalud.data.local.AppDatabase
import pe.edu.unmsm.nexosalud.data.repository.DiagnosticoRepository
import pe.edu.unmsm.nexosalud.data.repository.PacienteRepository
import pe.edu.unmsm.nexosalud.data.seed.Sembrador
import pe.edu.unmsm.nexosalud.model.DiagnosticoViewModel
import pe.edu.unmsm.nexosalud.model.DiagnosticoViewModelFactory
import pe.edu.unmsm.nexosalud.model.PacienteViewModel
import pe.edu.unmsm.nexosalud.model.PacienteViewModelFactory
import pe.edu.unmsm.nexosalud.ui.navigation.AppNavigation
import pe.edu.unmsm.nexosalud.ui.theme.NexoSaludTheme

class MainActivity : ComponentActivity() {

    private val db by lazy { AppDatabase.getDatabase(this) }

    private val pacienteViewModel: PacienteViewModel by viewModels {
        PacienteViewModelFactory(PacienteRepository(db.pacienteDao()))
    }

    private val diagnosticoViewModel: DiagnosticoViewModel by viewModels {
        DiagnosticoViewModelFactory(DiagnosticoRepository(db.diagnosticoCie10Dao()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch { Sembrador(db).sembrarSiHaceFalta() }

        enableEdgeToEdge()
        setContent {
            NexoSaludTheme {
                AppNavigation(pacienteViewModel, diagnosticoViewModel)
            }
        }
    }
}