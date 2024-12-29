package modules.sessionDetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.heartratemonitoringapp.R
import com.yourapp.components.alerts.CustomToast
import components.others.CustomNavigationBar
import components.popups.LoadingView
import dataModels.SessionRepresentable
import kotlin.random.Random

@Composable
fun SessionDetailScreen(
    isGuest: Boolean,
    session: SessionRepresentable,
    username: String? = null,
    onBack: () -> Unit,
) {
    val imageName = remember { mutableStateOf(getRandomImage()) }
    val showSignInLoading = remember { mutableStateOf(false) }
    val showFailedToast = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CustomNavigationBar(
                title = "Session Details"
            ) { println("back") }
        }
    ) { paddingValues ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(top = paddingValues.calculateTopPadding())) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = getDrawableResourceId(imageName.value)),
                    contentDescription = null,
                    modifier = Modifier
                        .aspectRatio(16f / 9f)
                        .height(180.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color.Gray),
                    contentScale = ContentScale.Crop
                )

                Text(
                    text = session.name,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Start)
                )

                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row {
                            Icon(
                                painter = painterResource(id = R.drawable.book_fill),
                                contentDescription = null,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(Modifier.width(5.dp))
                            Text(
                                text = session.teacher,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Row {
                            Text(
                                text = session.date,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(Modifier.width(5.dp))
                            Icon(
                                painter = painterResource(id = R.drawable.calendar),
                                contentDescription = null,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(
                            text = session.hour,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(Modifier.width(5.dp))
                        Icon(
                            painter = painterResource(id = R.drawable.clock_fill),
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier.fillMaxWidth()
                )
                {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        Text(
                            text = "Description",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = session.description ?: "No description available.",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    Spacer(Modifier.width(1.dp))
                }

                Spacer(modifier = Modifier.weight(1f))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.person_fill),
                        contentDescription = null,
                        modifier = Modifier.size(25.dp)
                    )
                    Text(
                        text = "${session.filledSpots}/${session.totalSpots}",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Button(
                    onClick = {
                        username?.let {
                            showSignInLoading.value = true
                        }
                    },
                    enabled = session.filledSpots < session.totalSpots && !isGuest,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(
                        if (session.filledSpots < session.totalSpots && !isGuest) Color.Red else Color.Gray
                    )
                ) {
                    Text(
                        text = getSignInButtonText(isGuest, session),
                        color = Color.White
                    )
                }
            }

            if (showSignInLoading.value) {
                LoadingView(
                    isShowing = showSignInLoading,
                    title = "Loading",
                    description = "Please wait..."
                )
            }

            if (showFailedToast.value) {
                CustomToast(
                    isShowing = showFailedToast,
                    iconName = android.R.drawable.ic_dialog_info,
                    message = "Failed to join session"
                )
            }
        }
    }
}

fun getSignInButtonText(isGuest: Boolean, session: SessionRepresentable): String {
    return when {
        isGuest -> "Cant sign in as Guest"
        session.totalSpots <= session.filledSpots -> "Session Full"
        else -> "Sign In"
    }
}

fun getRandomImage(): String {
    val baseString = "exercise_cartoon_"
    val randomNumber = Random.nextInt(1, 6)
    return baseString + randomNumber
}

fun getDrawableResourceId(imageName: String): Int {
    return when (imageName) {
        "exercise_cartoon_1" -> R.drawable.exercise_cartoon_1
        "exercise_cartoon_2" -> R.drawable.exercise_cartoon_2
        "exercise_cartoon_3" -> R.drawable.exercise_cartoon_3
        "exercise_cartoon_4" -> R.drawable.exercise_cartoon_4
        "exercise_cartoon_5" -> R.drawable.exercise_cartoon_5
        else -> R.drawable.exercise_cartoon_1
    }
}

@Preview(showBackground = true)
@Composable
fun preview() {
    SessionDetailScreen(
        isGuest = true,
        session = SessionRepresentable(
            id = "1",
            name = "Test Name",
            date = "31/12",
            hour = "00H",
            teacher = "J.Saias",
            totalSpots = 10,
            filledSpots = 9,
            description = "This is just an example description"
        ),
        username = null
    ) { }
}