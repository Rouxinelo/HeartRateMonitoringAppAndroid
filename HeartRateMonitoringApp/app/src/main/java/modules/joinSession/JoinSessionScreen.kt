package modules.joinSession

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.heartratemonitoringapp.R
import components.alerts.CustomAlert
import components.buttons.MultipleTextButton
import components.others.CustomNavigationBar
import components.popups.ConnectSensorModal
import components.popups.LoadingView
import dataModels.DeviceRepresentable
import dataModels.PreSessionData
import dataModels.SessionRepresentable
import dataModels.User

@Composable
fun JoinSessionScreen(
    preSessionData: PreSessionData,
) {
    var showEnterSessionLoading = remember { mutableStateOf(false) }
    var showFailedEnterAlert = remember { mutableStateOf(false) }
    var showBluetoothProblemsAlert = remember { mutableStateOf(false) }
    var showConnectionModal = remember { mutableStateOf(false) }
    var devices by remember { mutableStateOf(listOf<DeviceRepresentable>()) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            CustomNavigationBar(
                title = "Join Session"
            ) { }

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.heart_rate), // Replace with heart-rate image resource
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                        .aspectRatio(1f)
                )
                Text(
                    text = "Session details",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(16.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .shadow(
                            elevation = 10.dp,
                            shape = RoundedCornerShape(8.dp),
                            ambientColor = Color.Black.copy(alpha = 1f),
                            clip = false
                        )
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color.White),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Spacer(Modifier.height(10.dp))
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = preSessionData.session.name,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center)

                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = preSessionData.session.description ?: "No description available",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center)

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth())
                    {
                        Icon(
                            painter = painterResource(R.drawable.book_fill),
                            contentDescription = null,
                            tint = Color.Red,
                            modifier = Modifier.size(24.dp))
                        Spacer(Modifier.width(8.dp))
                        Text(
                            text = preSessionData.session.teacher,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold)
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth())
                    {
                        Icon(
                            painter = painterResource(R.drawable.clock_fill),
                            contentDescription = null,
                            tint = Color.Red,
                            modifier = Modifier.size(24.dp))
                        Spacer(Modifier.width(8.dp))
                        Text(
                            text = preSessionData.session.hour,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold)
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()) {
                        Icon(painter = painterResource(R.drawable.person_fill),
                            contentDescription = null,
                            tint = Color.Red,
                            modifier = Modifier.size(24.dp))
                        Spacer(Modifier.width(8.dp))
                        Text(
                            text = "${preSessionData.session.filledSpots}/${preSessionData.session.totalSpots}",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(Modifier.height(10.dp))
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            MultipleTextButton(
                title = "Join session",
                description = "Connect sensor and start"
            ) {
                showConnectionModal.value = true
            }
        }

        if (showConnectionModal.value) {
            ConnectSensorModal(
                isShowing = showConnectionModal,
                devices = devices,
                title = "Connect Sensor"
            ) { }
        }

        if (showEnterSessionLoading.value) {
            LoadingView(
                isShowing = showEnterSessionLoading,
                title = "Loading",
                description = "Please wait"
            )
        }

        if (showFailedEnterAlert.value) {
            CustomAlert(
                isShowing = showFailedEnterAlert,
                iconName = android.R.drawable.ic_dialog_info,
                title = "Error!",
                leftButtonText = "Ok",
                rightButtonText = "TODO()",
                description = "Failed connection",
                leftButtonAction = {},
                rightButtonAction = {},
                isSingleButton = true
            )
        }
    }

    fun startScanningForDevices() {
        showConnectionModal.value = true
    }

    fun goToSession() {
    }

    fun handleConnectionError() {
        showConnectionModal.value = false
        showFailedEnterAlert.value = true
    }
}

@Preview(showBackground = true)
@Composable
fun preview() {
    JoinSessionScreen(
        preSessionData = PreSessionData(
            session = SessionRepresentable(
                id = "1",
                name = "Test name",
                date = "11/11/2020",
                hour = "10h",
                teacher = "Rouxinol",
                totalSpots = 100,
                filledSpots = 100,
                description = "example description"
            ),
            user = User(
                username = "testUsername",
                firstName = "test",
                lastName = "test",
                email = "test",
                gender = "M",
                age = 11,
                password = "test"
            )
        )
    )
}
