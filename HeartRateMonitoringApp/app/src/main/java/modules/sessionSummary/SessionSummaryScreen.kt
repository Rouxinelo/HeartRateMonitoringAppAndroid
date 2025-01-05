package modules.sessionSummary

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.heartratemonitoringapp.R
import com.yourapp.components.alerts.CustomToast
import components.alerts.CustomAlert
import components.sections.HeartRateSummarySection
import components.sections.SessionInfoSection
import dataModels.DeviceRepresentable
import dataModels.SessionSimplified
import dataModels.SessionSummaryData

@Composable
fun SessionSummaryScreen(
    showingToast: MutableState<Boolean>,
    sessionSummary: SessionSummaryData,
) {
    val showingAlert = remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "Summary",
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold
                        )
                        SessionInfoSection(
                            imageResId = R.drawable.book_fill,
                            text = sessionSummary.session.name,
                            spacing = 9
                        )
                        SessionInfoSection(
                            imageResId = R.drawable.person_fill,
                            text = sessionSummary.session.teacher,
                            spacing = 15
                        )
                        SessionInfoSection(
                            imageResId = R.drawable.sensor,
                            text = sessionSummary.sensor.name
                        )
                    }
                    Column(
                        modifier = Modifier
                            .clickable(onClick = {})
                            .padding(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.x_mark),
                            contentDescription = "Close",
                            tint = Color.Red,
                            modifier = Modifier.size(24.dp)
                        )
                        Text(
                            text = "Close",
                            color = Color.Red,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    }
                }

                Text(
                    text = "Session details:",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                )

                Column {
                    Text(
                        text = "Session Time:",
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "${getFormattedHours(sessionSummary.sessionTime)}h " +
                                "${getFormattedMinutes(sessionSummary.sessionTime % 3600)}m " +
                                "${getFormattedSeconds(sessionSummary.sessionTime % 60)}s",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Red
                    )
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = "Heart Rate Data:",
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        HeartRateSummarySection(
                            sectionIcon = R.drawable.number_symbol,
                            sectionTitle = "Count",
                            sectionDescription = "${sessionSummary.measurements.size} Samples",
                            sectionColor = Color.Red
                        )
                        HeartRateSummarySection(
                            sectionIcon = R.drawable.tilde,
                            sectionTitle = "Average",
                            sectionDescription = "${getAverage(sessionSummary.measurements)} BPM",
                            sectionColor = Color.Blue
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        HeartRateSummarySection(
                            sectionIcon = R.drawable.chevron_up,
                            sectionTitle = "Max",
                            sectionDescription = "${sessionSummary.measurements.maxOrNull() ?: 0} BPM",
                            sectionColor = Color.Green
                        )
                        HeartRateSummarySection(
                            sectionIcon = R.drawable.chevron_down,
                            sectionTitle = "Min",
                            sectionDescription = "${sessionSummary.measurements.minOrNull() ?: 0} BPM",
                            sectionColor = Color(0xFFFFCC00)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        if (showingAlert.value) {
            CustomAlert(
                isShowing = showingAlert,
                iconName =android.R.drawable.ic_dialog_info,
                title = "Exit?",
                leftButtonText = "Cancel",
                rightButtonText = "Leave",
                description = "Are you sure?",
                leftButtonAction = {
                    showingAlert.value = false
                },
                rightButtonAction = {
                    showingAlert.value = false
                },
                isSingleButton = false
            )
        }

        if (showingToast.value) {
            CustomToast(
                isShowing = showingToast,
                iconName = android.R.drawable.ic_dialog_info,
                message = "Could not send summary"
            )
        }
    }
}

fun getFormattedHours(time: Int): String {
    val hours = time / 3600
    return if (hours >= 10) "$hours" else "0$hours"
}

fun getFormattedMinutes(time: Int): String {
    val minutes = time / 60
    return if (minutes >= 10) "$minutes" else "0$minutes"
}

fun getFormattedSeconds(time: Int): String {
    return if (time >= 10) "$time" else "0$time"
}

fun getAverage(array: List<Int>): Int {
    return if (array.isEmpty()) 0 else array.sum() / array.size
}

@Preview(showBackground = true)
@Composable
fun preview() {
    SessionSummaryScreen(
        showingToast =  remember { mutableStateOf(false) },
        sessionSummary = SessionSummaryData(
            sensor = DeviceRepresentable(
                name = "Movesense 1234",
                batteryPercentage = 1
            ),
            username = "TestUsername",
            session = SessionSimplified(
                id = "1",
                name = "test session name",
                teacher = "rouxinol"
            ),
            measurements = listOf(10, 20, 30, 40, 50),
            sessionTime = 11000
        )
    )
}
