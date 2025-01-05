package components.sections

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SessionInfoSection(
    imageResId: Int,
    text: String,
    spacing: Int = 8
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(spacing.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            painter = painterResource(id = imageResId),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.size(16.dp)
        )
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
