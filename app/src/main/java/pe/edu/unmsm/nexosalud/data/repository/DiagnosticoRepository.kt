package pe.edu.unmsm.nexosalud.data.repository

import kotlinx.coroutines.flow.Flow
import pe.edu.unmsm.nexosalud.data.local.DiagnosticoCie10Dao
import pe.edu.unmsm.nexosalud.data.local.DiagnosticoCie10Entity
import javax.inject.Inject

class DiagnosticoRepository @Inject constructor(private val dao: DiagnosticoCie10Dao) {

    fun listar(): Flow<List<DiagnosticoCie10Entity>> = dao.listar()

    suspend fun buscarPorCodigo(codigo: String): DiagnosticoCie10Entity? =
        dao.buscarPorCodigo(codigo.trim().uppercase())

    suspend fun contar(): Int = dao.contar()

    suspend fun cargar(diagnosticos: List<DiagnosticoCie10Entity>) =
        dao.insertarTodos(diagnosticos)
}