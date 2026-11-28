package pe.edu.unmsm.nexosalud.data.seed

import androidx.room.withTransaction
import pe.edu.unmsm.nexosalud.data.local.AppDatabase
import javax.inject.Inject
import javax.inject.Singleton
@Singleton
class Sembrador @Inject constructor(private val db: AppDatabase) {

    suspend fun sembrarSiHaceFalta() {
        db.withTransaction {
            val establecimientoDao = db.establecimientoDao()
            if (establecimientoDao.contar() == 0) {
                DatosIniciales.establecimientos.forEach { establecimientoDao.insertar(it) }
            }

            val diagnosticoDao = db.diagnosticoCie10Dao()
            if (diagnosticoDao.contar() == 0) {
                diagnosticoDao.insertarTodos(DatosIniciales.diagnosticos)
            }

            val pacienteDao = db.pacienteDao()
            DatosIniciales.pacientes.forEach { paciente ->
                if (pacienteDao.buscarPorCodigo(paciente.codigoAnonimo) == null) {
                    pacienteDao.insertar(paciente)
                }
            }
        }
    }
}