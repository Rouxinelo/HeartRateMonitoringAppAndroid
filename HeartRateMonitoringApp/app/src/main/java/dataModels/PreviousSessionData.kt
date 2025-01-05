package dataModels

data class PreviousSessionData(
    val session: SessionRepresentable,
    val count: Int,
    val average: Int,
    val maximum: Int,
    val minimum: Int
)