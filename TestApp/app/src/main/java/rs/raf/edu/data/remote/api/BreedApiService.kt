package rs.raf.edu.data.remote.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rs.raf.edu.model.Breed

interface BreedApiService {
    @GET("breeds")
    suspend fun getBreeds(): List<Breed>

    @GET("breeds/{id}")
    suspend fun getBreedById(
        @Path("id") breedId: String
    ) : Breed

    @GET("images/search")
    suspend fun searchImages(
        @Query("breed_ids") breedId: String,
        @Query("limit") limit: Int = 1
    ) : List<ImageResult>

    @GET("images/{id}")
    suspend fun getImageById(
        @Path("id") imageId: String
    ) : ImageResult
}

@Serializable
data class ImageResult(
    val id: String,
    val url: String,
    @SerialName("thumbnail_url")
    val thumbnailUrl: String? = null
)