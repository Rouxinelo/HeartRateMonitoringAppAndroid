package com.yourapp.components.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight

@Composable
fun HomeScreenButton(
    modifier: Modifier = Modifier,
    labelText: String,
    onClick: () -> Unit,
    painter: Painter
) {
    Column(
        modifier = modifier
            .width(150.dp)
            .height(100.dp)
            .background(Color.Red, RoundedCornerShape(20.dp))
            .clickable(onClick = onClick),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painter,
            contentDescription = "Info Icon",
            tint = Color.White,
            modifier = Modifier
                .size(24.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = labelText,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}
