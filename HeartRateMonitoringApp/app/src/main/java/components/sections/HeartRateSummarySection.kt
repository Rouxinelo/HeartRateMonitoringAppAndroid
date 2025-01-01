package components.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HeartRateSummarySection(
    sectionIcon: Int,
    sectionTitle: String,
    sectionDescription: String,
    sectionColor: Color,
    foregroundColor: Color = Color.White
) {
    Box(
        modifier = Modifier
            .size(width = 180.dp, height = 140.dp)
            .background(color = sectionColor, shape = RoundedCornerShape(20.dp))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                painter = painterResource(id = sectionIcon),
                contentDescription = null,
                modifier = Modifier.size(20.dp),
                tint = foregroundColor
            )
            Text(
                text = sectionTitle,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = foregroundColor
            )
            Text(
                text = sectionDescription,
                fontWeight = FontWeight.Bold,
                color = foregroundColor
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun preview() {
    HeartRateSummarySection(
        sectionIcon = android.R.drawable.ic_dialog_info,
        sectionTitle = "Title",
        sectionDescription = "Example description",
        sectionColor = Color.Red,
        foregroundColor = Color.White
    )
}