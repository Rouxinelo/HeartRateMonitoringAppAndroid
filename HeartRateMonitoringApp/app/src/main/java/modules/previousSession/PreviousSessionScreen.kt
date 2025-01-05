package modules.previousSession

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.heartratemonitoringapp.R
import com.yourapp.components.alerts.CustomToast
import components.sections.HeartRateSummarySection
import components.sections.SessionInfoSection
import dataModels.PreviousSessionData
import dataModels.SessionRepresentable

@Composable
fun PreviousSessionScreen(
    previousSessionData: PreviousSessionData,
    onBack: () -> Unit,
) {
    var showSuccessToast = remember { mutableStateOf(false) }
    var showFailureToast = remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                // Column with the summary content
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Summary",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold
                    )

                    SessionInfoSection(
                        imageResId = R.drawable.book_fill,
                        text = "Name",
                        spacing = 8
                    )
                    SessionInfoSection(
                        imageResId = R.drawable.person_fill,
                        text = "Teacher",
                        spacing = 8
                    )
                    SessionInfoSection(
                        imageResId = R.drawable.calendar,
                        text = "Date",
                        spacing = 8
                    )
                    SessionInfoSection(
                        imageResId = R.drawable.clock_fill,
                        text = "Hour",
                        spacing = 8
                    )
                }

                Column(
                    modifier = Modifier
                        .clickable(onClick = { /* Handle close action */ })
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.x_mark),
                        contentDescription = "Close",
                        tint = Color.Red,
                        modifier = Modifier.size(24.dp)
                    )
                    Text(
                        text = "Close",
                        color = Color.Red,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }


            }


            Column (verticalArrangement = Arrangement.spacedBy(10.dp))
            {
                Text(
                    text = "Heart Rate Data:",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                )

                Row (horizontalArrangement = Arrangement.spacedBy(10.dp))
                {
                    HeartRateSummarySection(
                        sectionIcon = R.drawable.number_symbol,
                        sectionTitle = "Count",
                        sectionDescription = "Samples",
                        sectionColor = Color.Red,
                        foregroundColor = Color.White
                    )

                    HeartRateSummarySection(
                        sectionIcon = R.drawable.tilde,
                        sectionTitle = "Average",
                        sectionDescription = "50 BPM",
                        sectionColor = Color.Blue,
                        foregroundColor = Color.White
                    )
                }

                Row (horizontalArrangement = Arrangement.spacedBy(10.dp))
                {
                    HeartRateSummarySection(
                        sectionIcon = R.drawable.chevron_up,
                        sectionTitle = "Max",
                        sectionDescription = "100 BPM",
                        sectionColor = Color.Green,
                        foregroundColor = Color.White
                    )

                    HeartRateSummarySection(
                        sectionIcon = R.drawable.chevron_down,
                        sectionTitle = "Min",
                        sectionDescription = "10 BPM",
                        sectionColor = Color(0xFFFFCC00),
                        foregroundColor = Color.White
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                    shape = RoundedCornerShape(30.dp),
                    modifier = Modifier
                        .padding(8.dp)
                        .weight(1f)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "Share",
                            tint = Color.White
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Share",
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.titleLarge,
                            color = Color.White
                        )
                    }
                }

                Button(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                    shape = RoundedCornerShape(30.dp),
                    modifier = Modifier
                        .padding(8.dp)
                        .weight(1f)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "Save",
                            tint = Color.White
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Save",
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.titleLarge,
                            color = Color.White
                        )
                    }
                }
            }

        }

        if (showSuccessToast.value) {
            CustomToast(
                isShowing = showSuccessToast,
                iconName = android.R.drawable.ic_dialog_info,
                message = "Successfull"
            )
        }

        if (showFailureToast.value) {
            CustomToast(
                isShowing = showFailureToast,
                iconName = android.R.drawable.ic_dialog_info,
                message = "Failure"
            )
        }
    }
}