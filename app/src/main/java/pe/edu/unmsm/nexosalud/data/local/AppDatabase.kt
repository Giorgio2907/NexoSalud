package pe.edu.unmsm.nexosalud.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        EstablecimientoEntity::class,
        PacienteEntity::class,
        DiagnosticoCie10Entity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun establecimientoDao(): EstablecimientoDao
    abstract fun pacienteDao(): PacienteDao
    abstract fun diagnosticoCie10Dao(): DiagnosticoCie10Dao
}