package com.example.heartratemonitoringapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yourapp.components.buttons.HomeScreenButton
import com.yourapp.components.alerts.CustomToast
import components.popups.LoginView
import components.alerts.CustomAlert

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    val isToastVisible = remember { mutableStateOf(false) }
    val isLoginViewVisible = remember { mutableStateOf(false) }
    val isGuestAlertVisible = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center),
            verticalArrangement = Arrangement.spacedBy(30.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.heart_rate),
                contentDescription = "Heart Icon",
                modifier = Modifier
                    .size(150.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Text(
                text = "Example title",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    HomeScreenButton(
                        labelText = "Login",
                        onClick = {
                            isLoginViewVisible.value = true
                        },
                        painter = painterResource(id = R.drawable.person_fill)
                    )

                    HomeScreenButton(
                        labelText = "Register",
                        onClick = {
                            isToastVisible.value = true
                        },
                        painter = painterResource(id = R.drawable.person_add)
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    HomeScreenButton(
                        labelText = "Guest",
                        onClick = {
                            isGuestAlertVisible.value = true
                        },
                        painter = painterResource(id = R.drawable.person_question)
                    )

                    HomeScreenButton(
                        labelText = "Teacher",
                        onClick = {
                            isToastVisible.value = true
                        },
                        painter = painterResource(id = R.drawable.book_fill)
                    )
                }
            }
        }

        if (isToastVisible.value) {
            CustomToast(
                isShowing = isToastVisible,
                iconName = android.R.drawable.ic_dialog_info,
                message = "Coming Soon!"
            )
        }

        if (isLoginViewVisible.value) {
            LoginView(
                isShowing = isLoginViewVisible,
                isRecoverPasswordHidden = false,
                onLogin = { username, password ->
                    println("Username: $username")
                    println("Password: $password")
                },
                onRecoverPassword = {
                    isToastVisible.value = true
                }
            )
        }

        if (isGuestAlertVisible.value) {
            CustomAlert(
                isShowing = isGuestAlertVisible,
                iconName = android.R.drawable.ic_dialog_info,
                title = "Convidado?",
                leftButtonText = "Cancelar",
                rightButtonText = "Continuar",
                description = "Varias funcionalidades indisponiveis",
                leftButtonAction = {},
                rightButtonAction = {  isToastVisible.value = true },
                isSingleButton = false
            )
        }
    }
}
