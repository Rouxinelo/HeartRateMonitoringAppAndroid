package dataModels

data class SessionData(
    val session: SessionSimplified,
    val username: String,
    val device: DeviceRepresentable
)