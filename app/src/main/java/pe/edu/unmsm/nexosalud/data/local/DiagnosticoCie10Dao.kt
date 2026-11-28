package pe.edu.unmsm.nexosalud.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DiagnosticoCie10Dao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertarTodos(diagnosticos: List<DiagnosticoCie10Entity>)

    @Query("SELECT * FROM diagnostico_cie10 ORDER BY codigo")
    fun listar(): Flow<List<DiagnosticoCie10Entity>>

    @Query("SELECT * FROM diagnostico_cie10 WHERE codigo = :codigo")
    suspend fun buscarPorCodigo(codigo: String): DiagnosticoCie10Entity?

    @Query("SELECT COUNT(*) FROM diagnostico_cie10")
    suspend fun contar(): Int
}