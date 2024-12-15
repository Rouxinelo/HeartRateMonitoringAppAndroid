package components.alerts

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun CustomAlert(
    isShowing: MutableState<Boolean>,
    iconName: Int,
    title: String,
    leftButtonText: String,
    rightButtonText: String,
    description: String,
    leftButtonAction: () -> Unit,
    rightButtonAction: () -> Unit,
    isSingleButton: Boolean
) {
    if (isShowing.value) {
        val yOffset = remember { Animatable(1000f) }

        LaunchedEffect(Unit) {
            yOffset.animateTo(0f, animationSpec = spring())
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f))
                .padding(20.dp),
            contentAlignment = Alignment.Center

        ) {
            Column(
                modifier = Modifier
                    .background(Color.White, shape = RoundedCornerShape(20.dp))
                    .padding(30.dp)
                    .offset(y = yOffset.value.dp),
                horizontalAlignment = Alignment.CenterHorizontally
                ) {
                Icon(
                    painter = painterResource(id = iconName),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp),
                    tint = Color.Red
                    )

                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                    color = Color.Black,
                    modifier = Modifier.padding(top = 16.dp),
                    textAlign = TextAlign.Center
                    )

                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    color = Color.Black,
                    modifier = Modifier.padding(vertical = 16.dp),
                    textAlign = TextAlign.Center
                    )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = {
                            leftButtonAction()
                            close(isShowing)
                        },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            Color.Red
                        )
                    ) {
                        Text(text = leftButtonText, fontWeight = FontWeight.Bold)
                    }

                    if (!isSingleButton) {
                        Button(
                            onClick = {
                                rightButtonAction()
                                close(isShowing)
                            },
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.buttonColors(
                                Color.Red
                            )
                        ) {
                            Text(text = rightButtonText, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}

fun close(isShowing: MutableState<Boolean>) {
    isShowing.value = false
}
