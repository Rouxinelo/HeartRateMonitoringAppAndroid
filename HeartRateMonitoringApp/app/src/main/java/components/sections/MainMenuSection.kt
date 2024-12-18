package components.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.heartratemonitoringapp.R

@Composable
fun MainMenuSection(
    sectionColor: Color,
    sectionIcon: Painter,
    sectionTitle: String,
    sectionDescription: String,
    isUnavailable: Boolean,
    sectionAction: () -> Unit
) {
    Box(
        modifier = Modifier
            .width(180.dp)
            .height(200.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(if (isUnavailable) Color.Gray else sectionColor)
            .clickable(
                enabled = !isUnavailable,
            ) { sectionAction() },
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Icon(
                painter = if (isUnavailable) painterResource(id = android.R.drawable.ic_dialog_info) else sectionIcon,
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .padding(bottom = 8.dp),
                tint = Color.White
            )
            Text(
                text = sectionTitle,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(start = 4.dp, top = 4.dp, end = 4.dp, bottom = 8.dp)
            )
            Text(
                text = if (isUnavailable) "Unavailable in guest mode" else sectionDescription,
                fontSize = 14.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainMenuSectionUnavailablePreview() {
    MainMenuSection(
        sectionColor = Color.Blue,
        sectionIcon = painterResource(id = android.R.drawable.ic_dialog_info),
        sectionTitle = "Unavailable Section",
        sectionDescription = "This is a sample description.",
        isUnavailable = false,
        sectionAction = {}
    )
}
