package pe.edu.unmsm.nexosalud.data.repository

import kotlinx.coroutines.flow.Flow
import pe.edu.unmsm.nexosalud.data.local.PacienteDao
import pe.edu.unmsm.nexosalud.data.local.PacienteEntity
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class PacienteRepository @Inject constructor(private val dao: PacienteDao) {

    fun listar(): Flow<List<PacienteEntity>> = dao.listar()

    suspend fun registrar(codigo: String): Long {
        val limpio = codigo.trim().uppercase()

        require(limpio.matches(Regex("^[A-Z0-9-]{4,20}$"))) {
            "El código debe tener de 4 a 20 caracteres: letras, números o guion."
        }
        require(dao.buscarPorCodigo(limpio) == null) {
            "Ya existe un paciente con ese código."
        }

        return dao.insertar(
            PacienteEntity(codigoAnonimo = limpio, fechaRegistro = ahoraIso())
        )
    }

    private fun ahoraIso(): String =
        LocalDateTime.now().withNano(0).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
}