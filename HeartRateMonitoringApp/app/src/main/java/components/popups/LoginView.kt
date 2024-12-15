package components.popups


import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.example.heartratemonitoringapp.R
import components.textFields.CustomTextField

@Composable
fun LoginView(
    isShowing: MutableState<Boolean>,
    isRecoverPasswordHidden: Boolean,
    onLogin: (String, String) -> Unit,
    onRecoverPassword: () -> Unit
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var yOffset by remember { mutableStateOf(1000f) }
    val animatedYOffset by animateFloatAsState(targetValue = yOffset)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f))
            .clickable {
                isShowing.value = false
            }
    ) {
        // The Box that holds the white Column, and the Column itself does not propagate touch events
        Box(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) { /* Do nothing on touch inside this Box */ },
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .offset(y = animatedYOffset.dp)
                    .width(350.dp)
                    .height(if (isRecoverPasswordHidden) 450.dp else 550.dp)
                    .padding(30.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.White)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() },
                        onClick = { },
                    ),
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                Icon(
                    painter = painterResource(id = R.drawable.heart_rate),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp),
                    tint = Color.Unspecified
                )
                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Login", // Replace with localized string
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(20.dp))

                CustomTextField(
                    searchText = username,
                    onSearchTextChange = { username = it },
                    placeholder = "Username",
                    isPrivateField = false
                )

                Spacer(modifier = Modifier.height(16.dp))

                CustomTextField(
                    searchText = password,
                    onSearchTextChange = { password = it },
                    placeholder = "Password",
                    isPrivateField = true
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        onLogin(username, password)
                        isShowing.value = false
                    },
                    modifier = Modifier
                        .width(150.dp),
                    enabled = username.isNotEmpty() && password.isNotEmpty(),
                    colors = ButtonDefaults.buttonColors(
                        if (username.isNotEmpty() && password.isNotEmpty()) Color.Red else Color.Gray
                    )
                ) {
                    Text(
                        text = "Login", // Replace with localized string
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }

                if (!isRecoverPasswordHidden) {
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Forgot Password?", // Replace with localized string
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            onRecoverPassword()
                            isShowing.value = false
                        },
                        modifier = Modifier
                            .width(150.dp),
                        colors = ButtonDefaults.buttonColors(
                            Color.Red
                        )
                    ) {
                        Text(
                            text = "Recover Password",
                            textAlign = TextAlign.Center,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }

    LaunchedEffect(Unit) {
        yOffset = 0f
    }
}
