package modules.insertRecoveryCode

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
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
import components.alerts.CustomAlert
import components.others.CustomNavigationBar
import components.popups.LoadingView
import components.textFields.CustomTextField
import dataModels.RecoveryCode
import dataModels.User

@Composable
fun InsertRecoveryCodeScreen(
    recoveryCode: RecoveryCode,
    onBackClick: () -> Unit,
) {
    var codeString by remember { mutableStateOf("") }
    var passwordString by remember { mutableStateOf("") }
    var confirmPasswordString by remember { mutableStateOf("") }
    var isLoading = remember { mutableStateOf(false) }
    var showErrorAlert = remember { mutableStateOf(false) }
    var showSuccessAlert = remember { mutableStateOf(false) }
    var showNetworkErrorAlert = remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                CustomNavigationBar(
                    title = "Insert Recovery Code"
                ) { println("back") }
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .padding(top = paddingValues.calculateTopPadding()),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.heart_rate),
                    contentDescription = "Heart Rate",
                    modifier = Modifier
                        .size(100.dp)
                        .align(Alignment.CenterHorizontally)
                )

                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(text = "Recovery Code", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    CustomTextField(
                        searchText = codeString,
                        onSearchTextChange = { if (it.length <= 5) codeString = it },
                        isPrivateField = false,
                        placeholder = "Enter Code"
                    )
                    if (codeString.length == 5) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(
                                painter = if (codeString == recoveryCode.code.toString()) {
                                    painterResource(id = R.drawable.checkmark_fill)
                                } else {
                                    painterResource(id = R.drawable.cross_fill)
                                },
                                contentDescription = null,
                                tint = if (codeString == recoveryCode.code.toString()) Color.Green else Color.Red,
                                modifier = Modifier
                                    .size(20.dp)
                            )
                            Text(
                                text = if (codeString == recoveryCode.code.toString()) "Valid Code" else "Invalid Code",
                                color = if (codeString == recoveryCode.code.toString()) Color.Green else Color.Red
                            )
                        }
                    }
                }

                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(text = "New Password", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    CustomTextField(
                        searchText = passwordString,
                        onSearchTextChange = { passwordString = it },
                        isPrivateField = false,
                        placeholder = "Enter Password"
                    )
                    CustomTextField(
                        searchText = confirmPasswordString,
                        onSearchTextChange = { confirmPasswordString = it },
                        isPrivateField = false,
                        placeholder = "Confirm Password"
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                Button(
                    onClick = {
                        isLoading.value = true
                        if (codeString == recoveryCode.code.toString() &&
                            passwordString == confirmPasswordString &&
                            passwordString.length >= 8
                        ) {
                            isLoading.value = false
                            showSuccessAlert.value = true
                        } else {
                            isLoading.value = false
                            showErrorAlert.value = true
                        }
                    },
                    enabled = codeString == recoveryCode.code.toString() &&
                            passwordString == confirmPasswordString &&
                            passwordString.length >= 8,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        if (codeString == recoveryCode.code.toString() &&
                            passwordString == confirmPasswordString &&
                            passwordString.length >= 8
                        ) Color.Red else Color.Gray
                    )
                ) {
                    Text("Recover Password", color = Color.White, fontSize = 18.sp)
                }
            }

            // Loading View
            if (isLoading.value) {
                LoadingView(
                    isShowing = isLoading,
                    title = "Loading...",
                    description = "Please wait!"
                )
            }

            // Success Alert
            if (showSuccessAlert.value) {
                CustomAlert(
                    isShowing = showSuccessAlert,
                    iconName = R.drawable.checkmark_fill,
                    title = "Success",
                    leftButtonText = "OK",
                    rightButtonText = "",
                    description = "Password changed successfully!",
                    leftButtonAction = { showSuccessAlert.value = false },
                    rightButtonAction = {},
                    isSingleButton = true
                )
            }

            // Error Alert
            if (showErrorAlert.value) {
                CustomAlert(
                    isShowing = showErrorAlert,
                    iconName = R.drawable.cross_fill,
                    title = "Error",
                    leftButtonText = "Retry",
                    rightButtonText = "",
                    description = "Something went wrong. Please try again.",
                    leftButtonAction = { showErrorAlert.value = false },
                    rightButtonAction = {},
                    isSingleButton = true
                )
            }

            if (showNetworkErrorAlert.value) {
                CustomAlert(
                    isShowing = showNetworkErrorAlert,
                    iconName = R.drawable.cross_fill,
                    title = "Network Error",
                    leftButtonText = "Retry",
                    rightButtonText = "",
                    description = "Please check your connection and try again.",
                    leftButtonAction = { showNetworkErrorAlert.value = false },
                    rightButtonAction = {},
                    isSingleButton = true
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    InsertRecoveryCodeScreen(
        recoveryCode = RecoveryCode(
            user = User(
                username = "testUsername",
                firstName = "testUsername",
                lastName = "testUsername",
                email = "testUsername",
                gender = "M",
                age = 11,
                password = "testUsername"
            ),
            code = 11111
        ),
    ) { }
}
