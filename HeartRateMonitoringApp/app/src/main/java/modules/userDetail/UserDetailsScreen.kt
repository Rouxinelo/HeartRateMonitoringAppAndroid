package modules.userDetail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dataModels.UserDetailType
import components.others.CircularAvatar
import components.others.CustomNavigationBar
import components.sections.UserDetailsSection

data class UserDetail(
    val detailType: UserDetailType,
    val description: String
)

@Composable
fun UserDetailsScreen(
    details: List<UserDetail>,
) {
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            CustomNavigationBar(
                title = "Details"
            ) { println("back") }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(top = paddingValues.calculateTopPadding()), // Make sure to account for scaffold's top padding
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Spacer(Modifier.height(25.dp))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                CircularAvatar(
                    backgroundColor = Color.Red,
                    textColor = Color.White,
                    text = getInitials(details)
                )
            }

            Spacer(Modifier.height(50.dp))

            Column(
                modifier = Modifier
                    .verticalScroll(scrollState)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                details.forEach { detail ->
                    UserDetailsSection(
                        image = detail.detailType.image,
                        title = detail.detailType.sectionTitle,
                        description = detail.description
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}


fun getInitials(details: List<UserDetail>): String {
    for (detail in details) {
        if (detail.detailType.type == UserDetailType.Type.NAME) {
            return detail.description
                .split(" ")
                .filter { it.isNotBlank() }
                .joinToString("") { it.take(1).uppercase() }
        }
    }
    return ""
}

@Preview(showBackground = true)
@Composable
fun UserDetailsScreenPreview() {
    val sampleDetails = listOf(
        UserDetail(
            detailType = UserDetailType(UserDetailType.Type.NAME),
            description = "John Doe"
        ),
        UserDetail(
            detailType = UserDetailType(UserDetailType.Type.EMAIL),
            description = "john.doe@example.com"
        ),
        UserDetail(
            detailType = UserDetailType(UserDetailType.Type.GENDER),
            description = "Male"
        ),
        UserDetail(
            detailType = UserDetailType(UserDetailType.Type.AGE),
            description = "29"
        )
    )

    MaterialTheme {
        UserDetailsScreen(details = sampleDetails)
    }
}