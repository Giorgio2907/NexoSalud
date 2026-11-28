package pe.edu.unmsm.nexosalud.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
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

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "nexosalud.db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}