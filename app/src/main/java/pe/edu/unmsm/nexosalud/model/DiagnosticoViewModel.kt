package pe.edu.unmsm.nexosalud.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import pe.edu.unmsm.nexosalud.data.repository.DiagnosticoRepository
import javax.inject.Inject

@HiltViewModel
class DiagnosticoViewModel @Inject constructor(
    repository: DiagnosticoRepository
) : ViewModel() {

    val diagnosticos = repository.listar().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )
}