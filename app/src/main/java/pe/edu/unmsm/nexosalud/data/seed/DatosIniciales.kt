package pe.edu.unmsm.nexosalud.data.seed

import pe.edu.unmsm.nexosalud.data.local.DiagnosticoCie10Entity
import pe.edu.unmsm.nexosalud.data.local.EstablecimientoEntity
import pe.edu.unmsm.nexosalud.data.local.PacienteEntity

object DatosIniciales {

    val establecimientos = listOf(
        EstablecimientoEntity(
            nombre = "CMI El Progreso",
            tipo = "CMI",
            urlApi = ""
        ),
        EstablecimientoEntity(
            nombre = "Hospital Nacional Sergio E. Bernales",
            tipo = "HOSPITAL",
            urlApi = "http://10.0.2.2:8080"
        )
    )

    val pacientes = (1..5).map { n ->
        PacienteEntity(
            codigoAnonimo = "PAC-" + n.toString().padStart(4, '0'),
            fechaRegistro = "2026-11-27T08:0$n:00"
        )
    }

    private fun dx(codigo: String, descripcion: String, noPertinente: Boolean = false) =
        DiagnosticoCie10Entity(
            codigo = codigo,
            descripcion = descripcion,
            noPertinente = noPertinente
        )

    // MUESTRA DE PRUEBA. Las marcas noPertinente son ilustrativas;
    // la lista oficial la entrega la DIRIS Lima Norte.
    val diagnosticos = listOf(
        dx("J00", "Rinofaringitis aguda (resfriado común)", noPertinente = true),
        dx("J02.9", "Faringitis aguda, no especificada", noPertinente = true),
        dx("J03.9", "Amigdalitis aguda, no especificada"),
        dx("J06.9", "Infección aguda de las vías respiratorias superiores, no especificada", noPertinente = true),
        dx("J18.9", "Neumonía, no especificada"),
        dx("J20.9", "Bronquitis aguda, no especificada"),
        dx("J45.9", "Asma, no especificada"),
        dx("I10", "Hipertensión esencial (primaria)"),
        dx("I21.9", "Infarto agudo de miocardio, sin otra especificación"),
        dx("E10.9", "Diabetes mellitus insulinodependiente, sin complicaciones"),
        dx("E11.9", "Diabetes mellitus no insulinodependiente, sin complicaciones"),
        dx("E66.9", "Obesidad, no especificada"),
        dx("E78.5", "Hiperlipidemia, no especificada"),
        dx("D50.9", "Anemia por deficiencia de hierro, sin otra especificación"),
        dx("F32.9", "Episodio depresivo, no especificado"),
        dx("G40.9", "Epilepsia, no especificada"),
        dx("K29.7", "Gastritis, no especificada"),
        dx("K30", "Dispepsia", noPertinente = true),
        dx("K35.8", "Apendicitis aguda, otra y la no especificada"),
        dx("K80.2", "Cálculo de la vesícula biliar sin colecistitis"),
        dx("A09", "Diarrea y gastroenteritis de presunto origen infeccioso"),
        dx("N20.0", "Cálculo del riñón"),
        dx("N39.0", "Infección de vías urinarias, sitio no especificado"),
        dx("M54.5", "Lumbago (dolor lumbar bajo)"),
        dx("R10.4", "Otros dolores abdominales y los no especificados"),
        dx("R51", "Cefalea"),
        dx("L30.9", "Dermatitis, no especificada"),
        dx("B35.9", "Dermatofitosis, no especificada"),
        dx("C50.9", "Tumor maligno de la mama, parte no especificada"),
        dx("Z00.0", "Examen médico general", noPertinente = true)
    )
}