package pe.edu.unmsm.nexosalud.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface EstablecimientoDao {

    @Insert
    suspend fun insertar(establecimiento: EstablecimientoEntity): Long

    @Query("SELECT * FROM establecimiento ORDER BY nombre")
    fun listar(): Flow<List<EstablecimientoEntity>>

    @Query("SELECT COUNT(*) FROM establecimiento")
    suspend fun contar(): Int
}