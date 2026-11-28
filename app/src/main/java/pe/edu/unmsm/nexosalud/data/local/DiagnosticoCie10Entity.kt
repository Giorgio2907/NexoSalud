package pe.edu.unmsm.nexosalud.data.local

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "diagnostico_cie10",
    indices = [Index(value = ["codigo"], unique = true)]
)
data class DiagnosticoCie10Entity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val codigo: String = "",              // ej: "J00"
    val descripcion: String = "",         // ej: "Rinofaringitis aguda"
    val noPertinente: Boolean = false     // true = figura en la lista de la DIRIS Lima Norte
)