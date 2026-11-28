package pe.edu.unmsm.nexosalud.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import pe.edu.unmsm.nexosalud.data.local.AppDatabase
import pe.edu.unmsm.nexosalud.data.local.DiagnosticoCie10Dao
import pe.edu.unmsm.nexosalud.data.local.EstablecimientoDao
import pe.edu.unmsm.nexosalud.data.local.PacienteDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "nexosalud.db").build()

    @Provides
    fun providePacienteDao(db: AppDatabase): PacienteDao = db.pacienteDao()

    @Provides
    fun provideEstablecimientoDao(db: AppDatabase): EstablecimientoDao = db.establecimientoDao()

    @Provides
    fun provideDiagnosticoDao(db: AppDatabase): DiagnosticoCie10Dao = db.diagnosticoCie10Dao()
}