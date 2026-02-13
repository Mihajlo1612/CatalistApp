package rs.raf.edu.data.repository

import kotlinx.coroutines.flow.Flow
import rs.raf.edu.data.remote.api.ImageResult
import rs.raf.edu.model.Breed

interface BreedRepository {
    fun getAllBreeds(): Flow<List<Breed>>
    suspend fun getImageForBreed(breedId: String): String?
    suspend fun getBreedById(breedId: String): Breed?
    suspend fun getImageById(imageId: String): ImageResult?
}
