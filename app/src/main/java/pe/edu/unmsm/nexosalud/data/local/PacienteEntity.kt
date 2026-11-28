package pe.edu.unmsm.nexosalud.data.local

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "paciente",
    indices = [Index(value = ["codigoAnonimo"], unique = true)]
)
data class PacienteEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val codigoAnonimo: String = "",
    val fechaRegistro: String = ""   // texto ISO 8601, ej: 2026-11-27T08:30:00
)