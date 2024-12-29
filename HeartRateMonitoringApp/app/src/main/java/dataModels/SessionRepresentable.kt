package dataModels

import kotlinx.serialization.Serializable

@Serializable
data class SessionRepresentable (
    val id: String,
    val name: String,
    val date: String,
    val hour: String,
    val teacher: String,
    val totalSpots: Int,
    val filledSpots: Int,
    val description: String? = null
)