package pe.edu.unmsm.nexosalud.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import pe.edu.unmsm.nexosalud.data.repository.PacienteRepository

class PacienteViewModel(private val repository: PacienteRepository) : ViewModel() {

    val pacientes = repository.listar().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    private val _mensaje = MutableStateFlow<String?>(null)
    val mensaje: StateFlow<String?> = _mensaje.asStateFlow()

    fun registrar(codigo: String) {
        viewModelScope.launch {
            try {
                repository.registrar(codigo)
                _mensaje.value = "Paciente registrado."
            } catch (e: IllegalArgumentException) {
                _mensaje.value = e.message
            }
        }
    }

    fun mensajeMostrado() {
        _mensaje.value = null
    }
}

class PacienteViewModelFactory(
    private val repository: PacienteRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass.isAssignableFrom(PacienteViewModel::class.java))
        return PacienteViewModel(repository) as T
    }
}