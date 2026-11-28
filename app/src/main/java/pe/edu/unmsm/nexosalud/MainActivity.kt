package pe.edu.unmsm.nexosalud

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import pe.edu.unmsm.nexosalud.data.seed.Sembrador
import pe.edu.unmsm.nexosalud.model.DiagnosticoViewModel
import pe.edu.unmsm.nexosalud.model.PacienteViewModel
import pe.edu.unmsm.nexosalud.ui.navigation.AppNavigation
import pe.edu.unmsm.nexosalud.ui.theme.NexoSaludTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var sembrador: Sembrador

    private val pacienteViewModel: PacienteViewModel by viewModels()
    private val diagnosticoViewModel: DiagnosticoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch { sembrador.sembrarSiHaceFalta() }

        enableEdgeToEdge()
        setContent {
            NexoSaludTheme {
                AppNavigation(pacienteViewModel, diagnosticoViewModel)
            }
        }
    }
}