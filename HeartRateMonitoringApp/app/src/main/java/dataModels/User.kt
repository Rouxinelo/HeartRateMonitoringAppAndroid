package dataModels

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val username: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val gender: String,
    val age: Int,
    val password: String
)