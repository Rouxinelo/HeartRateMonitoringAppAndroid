package modules.userDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dataModels.UserDetailType

data class UserDetail(
    val detailType: UserDetailType,
    val description: String
)

@Composable
fun UserDetailsScreen(details: List<UserDetail>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(50.dp)
    ) {
        // Title and avatar section
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(25.dp)
        ) {
            Text(
                text = "Details",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )
            CircularAvatar(
                backgroundColor = Color.Red,
                textColor = Color.White,
                text = getInitials(details)
            )
        }

        // Details section
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
        }

        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
fun CircularAvatar(
    backgroundColor: Color,
    textColor: Color,
    text: String
) {
    Box(
        modifier = Modifier
            .size(100.dp)
            .background(backgroundColor, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = textColor,
            style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold)
        )
    }
}

fun getInitials(details: List<UserDetail>): String {
    return details.joinToString("") { it.description.firstOrNull()?.toString() ?: "" }
}
