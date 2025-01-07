package modules.session

import android.se.omapi.Session
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.heartratemonitoringapp.R
import components.alerts.CustomAlert
import components.others.HeartAnimation
import components.others.LineChart
import components.sections.SessionInfoSection
import dataModels.DeviceRepresentable
import dataModels.SessionData
import dataModels.SessionRepresentable
import dataModels.SessionSimplified

@Composable
fun SessionScreen(
    sessionData: SessionData,
    maximumChartValues: Int = 5,
) {
    var showingCloseAlert = remember { mutableStateOf(false) }
    var shouldShowSummaryToast by remember { mutableStateOf(false) }

    val animationAmount by rememberInfiniteTransition().animateFloat(
        initialValue = 1f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 500, easing = LinearEasing)
        )
    )

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(25.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = sessionData.session.name,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                    SessionInfoSection(
                        imageResId = R.drawable.person_fill,
                        text = sessionData.session.teacher
                    )
                    SessionInfoSection(
                        imageResId = R.drawable.sensor,
                        text = sessionData.device.name
                    )
                    SessionInfoSection(
                        imageResId = R.drawable.battery_full,
                        text = "${sessionData.device.batteryPercentage ?: 100}%"
                    )
                }
                Column(
                    modifier = Modifier
                        .clickable(onClick = { /* Handle close action */ })
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

            Column {
                Text(
                    text = "Time",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "00h00m00s",
                    fontSize = 32.sp,
                    color = Color.Red,
                    fontWeight = FontWeight.Bold
                )
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                Text(
                    text = "Heart Rate",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(Modifier.height(10.dp))

                HeartAnimation()

                Text(
                    text = "Current: 100",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                LineChart(
                    measurements = listOf(100, 50, 75, 20, 100),
                    maximumChartValues = maximumChartValues,
                    maxMeasurement = 200
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(horizontalAlignment = Alignment.Start) {
                        Text(
                            text = "Min",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "50 BPM",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Red
                        )
                    }

                    Spacer(Modifier.width(40.dp))

                    Column(horizontalAlignment = Alignment.Start) {
                        Text(
                            text = "Max",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "100 BPM",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Red
                        )
                    }

                    Spacer(Modifier.width(40.dp))

                    Column(horizontalAlignment = Alignment.Start) {
                        Text(
                            text = "Avg",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "75 BPM",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Red
                        )
                    }
                }
            }
        }

        if (showingCloseAlert.value) {
            CustomAlert(
                isShowing = showingCloseAlert,
                iconName = android.R.drawable.ic_dialog_info,
                title = "Exit?",
                leftButtonText = "Stay",
                rightButtonText = "Exit",
                description = "You cannot return after",
                leftButtonAction = {},
                rightButtonAction = {},
                isSingleButton = false
            )
        }
    }
}