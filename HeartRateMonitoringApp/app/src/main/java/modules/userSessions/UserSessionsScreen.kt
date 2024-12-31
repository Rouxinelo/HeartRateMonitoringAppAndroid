package modules.userSessions

import androidx.compose.foundation.Image
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
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.heartratemonitoringapp.R
import com.yourapp.components.alerts.CustomToast
import components.alerts.CustomAlert
import components.buttons.MultipleTextButton
import components.others.CustomNavigationBar
import components.popups.LoadingView
import components.popups.ModalType
import components.popups.UserSessionsModal
import dataModels.SessionRepresentable
import dataModels.User

@Composable
fun UserSessionsScreen(
    user: User,
) {
    val showJoinableSessionsModal = remember { mutableStateOf(false) }
    val showSignedSessionsModal = remember { mutableStateOf(false) }
    val showPreviousSessionsModal = remember { mutableStateOf(false) }
    val showSignedOutToast = remember { mutableStateOf(false) }
    val showFailedSignOutToast = remember { mutableStateOf(false) }
    val showErrorSessionAlert = remember { mutableStateOf(false) }
    val showLoading = remember { mutableStateOf(false) }
    val showNetworkErrorToast = remember { mutableStateOf(false) }

    val joinableSessions = remember { mutableStateOf(listOf<SessionRepresentable>()) }
    val signedSessions = remember { mutableStateOf(listOf<SessionRepresentable>()) }
    val previousSessions = remember { mutableStateOf(listOf<SessionRepresentable>()) }

    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                CustomNavigationBar(
                    title = "Available Sessions"
                ) { println("back") }
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(top = paddingValues.calculateTopPadding()),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.heart_rate),
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp),
                    contentScale = ContentScale.Fit
                )

                MultipleTextButton(
                    title = "Ready to join",
                    description = "Enter in a session"
                ) {
                    showLoading.value = true
                }

                MultipleTextButton(
                    title = "Signed Sessions",
                    description = "cancel sign ins"
                ) {
                    showLoading.value = true
                }


                MultipleTextButton(
                    title = "Previous Sessions",
                    description = "See past sessions"
                ) {
                    showLoading.value = true
                }
            }
        }
        if (showJoinableSessionsModal.value) {
            UserSessionsModal(
                isShowing = showJoinableSessionsModal,
                sessions = joinableSessions.value,
                title = "Joinable Sessions",
                modalType = ModalType.JOIN
            ) { }
        }

        if (showSignedSessionsModal.value) {
            UserSessionsModal(
                isShowing = showSignedSessionsModal,
                sessions = signedSessions.value,
                title = "Signable Sessions",
                modalType = ModalType.SIGN_OUT
            ) { }
        }

        if (showPreviousSessionsModal.value) {
            UserSessionsModal(
                isShowing = showPreviousSessionsModal,
                sessions = previousSessions.value,
                title = "Previous Sessions",
                modalType = ModalType.SIGN_OUT
            ) { }
        }

        if (showSignedOutToast.value) {
            CustomToast(
                isShowing = showSignedOutToast,
                iconName = android.R.drawable.ic_dialog_info,
                message = "Signed out successfuly!"
            )
        }
        if (showFailedSignOutToast.value) {
            CustomToast(
                isShowing = showFailedSignOutToast,
                iconName = android.R.drawable.ic_dialog_info,
                message = "Signed out failed!"
            )
        }
        if (showNetworkErrorToast.value) {
            CustomToast(
                isShowing = showNetworkErrorToast,
                iconName = android.R.drawable.ic_dialog_info,
                message = "Network error!"
            )
        }

        if (showLoading.value) {
            LoadingView(
                isShowing = showLoading,
                title = "Loading",
                description = "Please wait"
            )
        }

        if (showErrorSessionAlert.value) {
            CustomAlert(
                isShowing = showErrorSessionAlert,
                iconName = TODO(),
                title = "Error!",
                leftButtonText = "Ok",
                rightButtonText = "",
                description = "Error joining session",
                leftButtonAction = {},
                rightButtonAction = {},
                isSingleButton = true
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun preview() {
    UserSessionsScreen(
        user = User(
            username = "testUsername",
            firstName = "testName",
            lastName = "testName",
            email = "testEmail",
            gender = "M",
            age = 11,
            password = "testPassword"
        )
    )
}
