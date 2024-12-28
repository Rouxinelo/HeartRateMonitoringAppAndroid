package modules.calendar

import android.os.PerformanceHintManager.Session
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yourapp.components.alerts.CustomToast
import components.sections.SessionSection

@Composable
fun CalendarScreen(
    searchText: String = "",
    didSignIn: Boolean = false,
    showEmptyAlert: Boolean = false,
    isGuest: Boolean,
    username: String?,
    sessions: List<Session>,
    sessionToSignIn: Session?,
    onSessionClicked: (Session) -> Unit,
    onBackPressed: () -> Unit
) {
    var search by remember { mutableStateOf(searchText) }
    var isSignedIn by remember { mutableStateOf(didSignIn) }
    var showAlert by remember { mutableStateOf(showEmptyAlert) }

    if (isSignedIn) {
        CustomToast(
            isShowing = isSignedIn,
            iconName = android.R.drawable.ic_dialog_info,
            message = "Signed In"
        )
    }

    // Main screen content
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Spacer(Modifier.height(16.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Found 1 session"
                )

                Spacer(modifier = Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(List<Session>()) { session ->
                    SessionSection(
                        title = session.name,
                        date = session.date,
                        hour = session.hour,
                        teacher = session.teacher,
                        occupation = getOccupationString(
                            totalSpots = session.totalSpots,
                            occupiedSpots = session.filledSpots
                        ),
                        onClick = { onSessionClicked(session) }
                    )
                }
            }
        }
    }
}
