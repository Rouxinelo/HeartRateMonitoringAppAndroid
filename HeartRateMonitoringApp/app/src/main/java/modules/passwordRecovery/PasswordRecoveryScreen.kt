package modules.passwordRecovery

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.heartratemonitoringapp.R
import components.alerts.CustomAlert
import components.others.CustomNavigationBar
import components.popups.LoadingView
import components.textFields.CustomTextField

@Composable
fun PasswordRecoveryScreen() {
    var username by remember { mutableStateOf("") }
    val isLoadingActive = remember { mutableStateOf(false) }
    val showNoUserFoundAlert = remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                CustomNavigationBar(
                    title = "Details"
                ) { println("back") }
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .padding(top = paddingValues.calculateTopPadding()),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.heart_rate),
                        contentDescription = "Heart Rate",
                        modifier = Modifier
                            .size(100.dp)
                            .align(Alignment.CenterHorizontally)
                    )

                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(
                            text = "Enter your username",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )

                        CustomTextField(
                            searchText = username,
                            onSearchTextChange = { username = it },
                            isPrivateField = false,
                            placeholder = ""
                        )

                        Text(
                            text = "We'll check if this username exists in our system.",
                            color = Color.Gray
                        )
                    }
                }

                Button(
                    onClick = {
                        isLoadingActive.value = true
                    },
                    enabled = username.isNotEmpty(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    colors = ButtonDefaults.buttonColors(
                        if (username.isNotEmpty()) Color.Red else Color.Gray
                    )
                ) {
                    Text(
                        text = "Recover Password",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

            if (isLoadingActive.value) {
                LoadingView(
                    isShowing = isLoadingActive,
                    title = "Loading",
                    description = "Please wait while we process your request"
                )
            }

            if (showNoUserFoundAlert.value) {
                CustomAlert(
                    isShowing = showNoUserFoundAlert,
                    iconName = android.R.drawable.ic_dialog_info,
                    title = "No User Found",
                    leftButtonText = "Retry",
                    rightButtonText = "",
                    description = "We couldn't find a user with that username.",
                    leftButtonAction = { showNoUserFoundAlert.value = false },
                    rightButtonAction = {},
                    isSingleButton = true
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun preview() {
    PasswordRecoveryScreen()
}
