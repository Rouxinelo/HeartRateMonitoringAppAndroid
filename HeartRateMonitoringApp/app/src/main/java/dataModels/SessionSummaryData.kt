package dataModels

data class SessionSummaryData(
    val sensor: DeviceRepresentable,
    val username: String,
    val session: SessionSimplified,
    val measurements: List<Int>,
    val sessionTime: Int
)