package components.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.heartratemonitoringapp.R

@Composable
fun SessionSection(
    title: String,
    date: String,
    hour: String,
    teacher: String,
    occupation: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .shadow(
                elevation = 10.dp,
                shape = RoundedCornerShape(8.dp),
                ambientColor = Color.Black.copy(alpha = 1f),
                clip = false
            )
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White)
            .clickable(onClick = onClick)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.calendar),
                            contentDescription = "Date",
                            tint = Color.Red,
                            modifier = Modifier.size(25.dp)
                        )

                        Spacer(Modifier.width(2.dp))

                        Text(text = date,
                            color = Color.Black,
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontSize = 16.sp
                            )
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.book_fill),
                            contentDescription = "Teacher",
                            tint = Color.Red,
                            modifier = Modifier.size(25.dp)
                        )

                        Spacer(Modifier.width(6.dp))

                        Text(text = teacher,
                            color = Color.Black,
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontSize = 16.sp
                            )
                        )
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.clock_fill),
                            contentDescription = "Hour",
                            tint = Color.Red,
                            modifier = Modifier.size(25.dp)
                        )

                        Spacer(Modifier.width(2.dp))

                        Text(text = hour,
                            color = Color.Black,
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontSize = 16.sp
                            )
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.person_fill),
                            contentDescription = "Occupation",
                            tint = Color.Red,
                            modifier = Modifier.size(25.dp)
                        )

                        Spacer(Modifier.width(2.dp))

                        Text(text = occupation,
                            color = Color.Black,
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontSize = 16.sp
                            )
                        )
                    }
                }
            }

            Spacer(Modifier.width(10.dp))

            Icon(
                painter = painterResource(id = R.drawable.chevron),
                contentDescription = "Chevron",
                modifier = Modifier.size(25.dp),
                tint = Color.Red
            )
        }
    }
}