package pe.edu.unmsm.nexosalud.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import pe.edu.unmsm.nexosalud.data.repository.DiagnosticoRepository

class DiagnosticoViewModel(repository: DiagnosticoRepository) : ViewModel() {

    val diagnosticos = repository.listar().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )
}

class DiagnosticoViewModelFactory(
    private val repository: DiagnosticoRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass.isAssignableFrom(DiagnosticoViewModel::class.java))
        return DiagnosticoViewModel(repository) as T
    }
}