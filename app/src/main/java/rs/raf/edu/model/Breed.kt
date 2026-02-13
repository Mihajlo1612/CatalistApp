package rs.raf.edu.model

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonIgnoreUnknownKeys

@OptIn(ExperimentalSerializationApi::class)
@Serializable
@JsonIgnoreUnknownKeys
data class Breed(
    val id: String,
    val name: String,

    @SerialName("alt_names")
    val altNames: String? = null,

    val description: String,
    val temperament: String,

    @SerialName("origin")
    val origin: String? = null,

    @SerialName("life_span")
    val lifeSpan: String? = null,

    val weight: Weight? = null,
    val adaptability: Int? = null,

    @SerialName("affection_level")
    val affectionLevel: Int,
    @SerialName("dog_friendly")
    val dogFriendly: Int,
    @SerialName("energy_level")
    val energyLevel: Int,
    val grooming: Int,
    @SerialName("health_issues")
    val healthIssues: Int? = null,
    val intelligence: Int,
    @SerialName("social_needs")
    val socialNeeds: Int,
    @SerialName("stranger_friendly")
    val strangerFriendly: Int,
    val vocalisation: Int,
    val rare: Int,
    @SerialName("wikipedia_url")
    val wikipediaUrl: String? = null,
    val image: BreedImage? = null
)


@OptIn(ExperimentalSerializationApi::class)
@Serializable
@JsonIgnoreUnknownKeys
data class Weight(
    val metric: String
)

@OptIn(ExperimentalSerializationApi::class)
@Serializable
@JsonIgnoreUnknownKeys
data class BreedImage(
    val url: String
)