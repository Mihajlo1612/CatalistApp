package rs.raf.edu.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import rs.raf.edu.data.remote.api.BreedApiService
import rs.raf.edu.data.repository.BreedRepository
import rs.raf.edu.data.repository.BreedRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideBreedRepository(
        breedApiService: BreedApiService
    ) : BreedRepository {
        return BreedRepositoryImpl(breedApiService)
    }
}