package dataModels

import kotlinx.serialization.Serializable

@Serializable
data class RecoveryCode(
    val user: User,
    val code: Int
)