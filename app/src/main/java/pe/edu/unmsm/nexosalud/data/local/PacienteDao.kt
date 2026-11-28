package pe.edu.unmsm.nexosalud.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PacienteDao {

    @Insert
    suspend fun insertar(paciente: PacienteEntity): Long

    @Query("SELECT * FROM paciente ORDER BY id DESC")
    fun listar(): Flow<List<PacienteEntity>>

    @Query("SELECT * FROM paciente WHERE codigoAnonimo = :codigo")
    suspend fun buscarPorCodigo(codigo: String): PacienteEntity?
}