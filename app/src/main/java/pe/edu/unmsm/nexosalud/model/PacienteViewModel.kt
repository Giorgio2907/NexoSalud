package pe.edu.unmsm.nexosalud.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import pe.edu.unmsm.nexosalud.data.repository.PacienteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class MensajeUi(val texto: String, val esError: Boolean)

@HiltViewModel
class PacienteViewModel @Inject constructor(
    private val repository: PacienteRepository
) : ViewModel() {
    val pacientes = repository.listar().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    private val _mensaje = MutableStateFlow<MensajeUi?>(null)
    val mensaje: StateFlow<MensajeUi?> = _mensaje.asStateFlow()

    fun registrar(codigo: String, alExito: () -> Unit = {}) {
        viewModelScope.launch {
            try {
                repository.registrar(codigo)
                _mensaje.value = MensajeUi("Paciente registrado.", esError = false)
                alExito()
            } catch (e: IllegalArgumentException) {
                _mensaje.value = MensajeUi(e.message ?: "Dato inválido.", esError = true)
            }
        }
    }

    fun mensajeMostrado() {
        _mensaje.value = null
    }
}
