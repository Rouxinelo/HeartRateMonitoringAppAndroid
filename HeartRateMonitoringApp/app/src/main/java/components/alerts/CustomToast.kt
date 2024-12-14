package com.yourapp.components.alerts

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@Composable
fun CustomToast(
    isShowing: MutableState<Boolean>,
    iconName: Int,
    message: String
) {
    if (isShowing.value) {
        val yOffset = remember { Animatable(-200f) }
        val coroutineScope = rememberCoroutineScope()

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f))
                .clickable { closeToast(isShowing) }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .offset(y = yOffset.value.dp)
                        .background(Color.White, shape = RoundedCornerShape(8.dp))
                        .padding(start = 16.dp)
                        .fillMaxWidth(0.9f)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(
                            painter = painterResource(id = iconName),
                            contentDescription = "Icon",
                            tint = Color.Red,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = message,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            modifier = Modifier.weight(1f)
                        )
                        IconButton(
                            onClick = { closeToast(isShowing) }
                        ) {
                            Icon(
                                painter = painterResource(id = android.R.drawable.ic_menu_close_clear_cancel),
                                contentDescription = "Close",
                                tint = Color.Red
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
            }
            LaunchedEffect(Unit) {
                coroutineScope.launch {
                    yOffset.animateTo(
                        targetValue = 0f,
                        animationSpec = tween(durationMillis = 500)
                    )
                }
            }
        }
    }
}

private fun closeToast(isShowing: MutableState<Boolean>) {
    isShowing.value = false
}
