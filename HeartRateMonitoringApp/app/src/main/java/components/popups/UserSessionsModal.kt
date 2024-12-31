package components.popups

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.heartratemonitoringapp.R
import components.alerts.CustomAlert
import components.sections.SessionSection
import dataModels.SessionRepresentable

enum class ModalType {
    JOIN,
    SIGN_OUT
}

@Composable
fun UserSessionsModal(
    isShowing: MutableState<Boolean>,
    sessions: List<SessionRepresentable>,
    title: String,
    modalType: ModalType = ModalType.JOIN,
    onSelectSession: (SessionRepresentable) -> Unit
) {
    var selectedSession by remember { mutableStateOf<SessionRepresentable?>(null) }
    val showSignoutAlert = remember { mutableStateOf(false) }
    var yOffset by remember { mutableStateOf(1000.dp) }

    val animatedYOffset = animateDpAsState(targetValue = yOffset)

    if (isShowing.value) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f))
                .pointerInput(Unit) {
                    detectVerticalDragGestures { _, dragAmount ->
                        if (dragAmount > 0) {
                            closeModal(isShowing, onDismiss = { yOffset = 1000.dp })
                        }
                    }
                }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Bottom
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(5.dp)
                        .background(Color.Gray)
                        .clip(RoundedCornerShape(20.dp))
                        .align(Alignment.CenterHorizontally)
                        .padding(horizontal = 50.dp, vertical = 20.dp)
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Color.White,
                            shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp)
                        )
                        .offset(y = animatedYOffset.value)
                        .padding(horizontal = 16.dp),
                ) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = title,
                        fontSize = 22.sp,
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        color = Color.Black
                    )
                    Text(
                        text = getSessionsText(sessions.size),
                        fontSize = 18.sp,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(getSessionsHeight(sessions.size).dp)
                    ) {
                        sessions.forEach { session ->
                            SessionSection(
                                title = session.name,
                                date = session.date,
                                hour = session.hour,
                                teacher = session.teacher,
                                occupation = "${session.filledSpots}/${session.totalSpots}"
                            ) {
                                handleSelectedSession(
                                    session = session,
                                    modalType = modalType,
                                    selectedSessionSetter = { selectedSession = it },
                                    showSignoutAlertSetter = { showSignoutAlert.value = it },
                                    onClose = { closeModal(isShowing) }
                                )
                            }
                        }
                    }
                }
            }
            if (showSignoutAlert.value && selectedSession != null) {
                CustomAlert(
                    isShowing = showSignoutAlert,
                    iconName = R.drawable.book_fill,
                    title = selectedSession!!.name,
                    leftButtonText = "Cancel",
                    rightButtonText = "Sign out",
                    description = selectedSession!!.description ?: "",
                    leftButtonAction = {},
                    rightButtonAction = {},
                    isSingleButton = false
                )
            }
        }
    }
}

fun getSessionsText(sessionCount: Int): String {
    return if (sessionCount == 1) {
        "Found 1 session"
    } else {
        "Found $sessionCount sessions"
    }
}

fun getSessionsHeight(sessionCount: Int): Int {
    return (sessionCount * 150).coerceAtMost(450)
}

fun handleSelectedSession(
    session: SessionRepresentable,
    modalType: ModalType,
    selectedSessionSetter: (SessionRepresentable?) -> Unit,
    showSignoutAlertSetter: (Boolean) -> Unit,
    onClose: () -> Unit
) {
    when (modalType) {
        ModalType.JOIN -> onClose()
        ModalType.SIGN_OUT -> {
            selectedSessionSetter(session)
            showSignoutAlertSetter(true)
        }
    }
}

fun closeModal(
    isShowing: MutableState<Boolean>,
    onDismiss: () -> Unit = {}
) {
    onDismiss()
    isShowing.value = false
}