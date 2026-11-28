package pe.edu.unmsm.nexosalud.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "establecimiento")
data class EstablecimientoEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombre: String = "",
    val tipo: String = "",      // "CMI" o "HOSPITAL"
    val urlApi: String = ""     // dirección de su API (se usa en el Sprint 2)
)