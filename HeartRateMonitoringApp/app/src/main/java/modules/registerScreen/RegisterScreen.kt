package modules.registerScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import components.sections.Gender
import components.sections.RegisterScreenField
import components.sections.RegisterScreenGenderField
import components.textFields.CustomNumericField

@Composable
fun RegisterUserScreen(
    userName: MutableState<String>,
    firstName: MutableState<String>,
    lastName: MutableState<String>,
    email: MutableState<String>,
    password: MutableState<String>,
    birthDay: MutableState<String>,
    birthMonth: MutableState<String>,
    birthYear: MutableState<String>,
    isMaleSelected: MutableState<Boolean>,
    isFemaleSelected: MutableState<Boolean>,
    onBack: () -> Unit,
    onRegister: () -> Unit,
    verifyFields: Boolean = true
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth() // Change to fillMaxWidth instead of fillMaxSize
                .padding(horizontal = 16.dp)
        ) {
            // Top App Bar with Title
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // CustomBackButton(onClick = { onBack() }) // Custom back button
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "Register",
                    style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                Spacer(modifier = Modifier.weight(1f))
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                RegisterScreenField(
                    searchText = userName.value,
                    isHiddenField = false,
                    placeholder = "",
                    title = "Username",
                    description = "The name for login",
                    onSearchTextChange = { userName.value = it }
                )

                RegisterScreenField(
                    searchText = firstName.value,
                    isHiddenField = false,
                    placeholder = "",
                    title = "First Name",
                    description = "Your first name",
                    onSearchTextChange = { firstName.value = it },
                )

                RegisterScreenField(
                    searchText = lastName.value,
                    isHiddenField = false,
                    placeholder = "",
                    title = "Last Name",
                    description = "Your last name",
                    onSearchTextChange = { lastName.value = it },
                )

                RegisterScreenField(
                    searchText = email.value,
                    isHiddenField = false,
                    placeholder = "",
                    title = "Email",
                    description = "Your email",
                    onSearchTextChange = { email.value = it }
                )

                RegisterScreenField(
                    searchText = password.value,
                    isHiddenField = true,
                    placeholder = "",
                    title = "Password",
                    description = "Should be at least 8 characters long",
                    onSearchTextChange = { password.value = it }
                )

                Text(
                    text = "Date of birth",
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ){
                        CustomNumericField(
                            searchText = birthDay.value,
                            onSearchTextChange = { birthDay.value = it },
                            placeholder = "",
                            maxDigits = 2
                        )

                        Text(
                            text = "Day",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Gray,
                        )
                    }

                    Column(
                        modifier = Modifier.weight(1f)
                    ){
                        CustomNumericField(
                            searchText = birthMonth.value,
                            onSearchTextChange = { birthMonth.value = it },
                            placeholder = "",
                            maxDigits = 2
                        )
                        Text(
                            text = "Month",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Gray,
                        )
                    }

                    Column(
                        modifier = Modifier.weight(1f)
                    ){
                        CustomNumericField(
                            searchText = birthYear.value,
                            onSearchTextChange = { birthYear.value = it },
                            placeholder = "",
                            maxDigits = 4,
                        )
                        Text(
                            text = "Year",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Gray,
                        )
                    }
                }

                Column (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Gender",
                        style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(top = 8.dp)
                    )

                    RegisterScreenGenderField(
                        isMaleSelected = isMaleSelected.value,
                        isFemaleSelected = isFemaleSelected.value,
                        onGenderChange = { gender ->
                            if (gender == Gender.Male) {
                                isMaleSelected.value = true
                                isFemaleSelected.value = false
                            } else {
                                isMaleSelected.value = false
                                isFemaleSelected.value = true
                            }
                        }
                    )
                    Text(
                        text = "Your gender",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray,
                    )
                }

                Button(
                    onClick = { onRegister() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (verifyFields) Color.Red else Color.Gray,
                        contentColor = Color.White
                    ),
                    enabled = verifyFields
                ) {
                    Text(
                        text = "Register",
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun RegisterScreenPreview() {
    val userName = remember { mutableStateOf("") }
    val firstName = remember { mutableStateOf("") }
    val lastName = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val birthDay = remember { mutableStateOf("") }
    val birthMonth = remember { mutableStateOf("") }
    val birthYear = remember { mutableStateOf("") }
    val isMaleSelected = remember { mutableStateOf(true) }
    val isFemaleSelected = remember { mutableStateOf(false) }

    RegisterUserScreen(
        userName = userName,
        firstName = firstName,
        lastName = lastName,
        email = email,
        password = password,
        birthDay = birthDay,
        birthMonth = birthMonth,
        birthYear = birthYear,
        isMaleSelected = isMaleSelected,
        isFemaleSelected = isFemaleSelected,
        onBack = {},
        onRegister = {}
    )
}