package rs.raf.edu.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import rs.raf.edu.data.remote.api.BreedApiService
import rs.raf.edu.data.remote.api.ImageResult
import rs.raf.edu.model.Breed
import javax.inject.Inject

class BreedRepositoryImpl @Inject constructor(
    private val apiService: BreedApiService
) : BreedRepository {

    override fun getAllBreeds(): Flow<List<Breed>> = flow {
        emit(apiService.getBreeds())
    }

    override suspend fun getImageForBreed(breedId: String): String? {
        val firstResult: ImageResult? =
            apiService.searchImages(breedId).firstOrNull()

        val detailedResult: ImageResult? = firstResult?.id
            ?.let { apiService.getImageById(it) }
            ?: firstResult

        return detailedResult?.url
    }

    override suspend fun getBreedById(breedId: String): Breed? {
        return try {
            apiService.getBreedById(breedId)
        } catch (_: Exception) {
            null
        }
    }

    override suspend fun getImageById(imageId: String): ImageResult? {
        return try {
            apiService.getImageById(imageId)
        } catch (_: Exception) {
            null
        }
    }
}