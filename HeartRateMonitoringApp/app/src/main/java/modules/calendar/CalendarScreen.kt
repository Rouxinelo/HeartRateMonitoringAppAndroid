package modules.calendar

import android.R
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yourapp.components.alerts.CustomToast
import components.alerts.CustomAlert
import components.others.CustomNavigationBar
import components.sections.SessionSection
import components.textFields.CustomTextField
import dataModels.SessionRepresentable

@Composable
fun CalendarScreen(
    searchText: MutableState<String>,
    didSignIn: MutableState<Boolean>,
    showEmptyAlert: MutableState<Boolean>,
    isGuest: Boolean,
    username: String?,
    sessions: List<SessionRepresentable>,
    sessionToSignIn: MutableState<SessionRepresentable?>,
    onBack: () -> Unit,
    onSessionClick: (SessionRepresentable) -> Unit
) {

    Scaffold(
        topBar = {
            CustomNavigationBar(
                title = "Available Sessions"
            ) { println("back") }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier
                .padding(16.dp)
                .padding(paddingValues)
            ) {
                CustomTextField(
                    searchText = searchText.value,
                    onSearchTextChange = { searchText.value = it },
                    isPrivateField = false,
                    placeholder = "Search (Name or Teacher)"
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Encontradas ${sessions.size} sessões",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.weight(1f))
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Sessions List
                if (sessions.isNotEmpty()) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                    ) {
                        sessions.forEach { session ->
                            SessionSection(
                                title = session.name,
                                date = session.date,
                                hour = session.hour,
                                teacher = session.teacher,
                                occupation = "${session.filledSpots}/${session.totalSpots}",
                                onClick = { onSessionClick(session) }
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }
            }

            if (didSignIn.value) {
                CustomToast(
                    isShowing = didSignIn,
                    iconName = R.drawable.ic_dialog_info,
                    message = "Signed in!"
                )
            }

            if (sessions.isEmpty()) {
                CustomAlert(
                    isShowing = showEmptyAlert,
                    iconName = R.drawable.ic_dialog_info,
                    title = "Erro!",
                    leftButtonText = "Sair",
                    rightButtonText = "",
                    description = "Não foram encontradas sessões",
                    leftButtonAction = onBack,
                    rightButtonAction = {},
                    isSingleButton = true
                )
            }
        }
    }
}