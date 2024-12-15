package components.textFields

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    isPrivateField: Boolean,
    placeholder: String
) {
    var isTextFieldFocused by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(8.dp)) // White background for the Row
                .padding(8.dp)
        ) {
            TextField(
                value = searchText,
                onValueChange = onSearchTextChange,
                placeholder = {
                    Text(
                        text = placeholder,
                        style = TextStyle(fontSize = 16.sp, color = Color.Gray)
                    )
                },
                textStyle = TextStyle(fontSize = 16.sp, color = Color.Black),
                modifier = Modifier
                    .weight(1f)
                    .background(Color.Transparent) // Remove any background
                    .onFocusChanged { isTextFieldFocused = it.isFocused },
                visualTransformation = if (isPrivateField) PasswordVisualTransformation() else VisualTransformation.None,
                keyboardOptions = KeyboardOptions(keyboardType = if (isPrivateField) KeyboardType.Password else KeyboardType.Text),
                keyboardActions = KeyboardActions(onDone = {
                    focusManager.clearFocus()
                }),
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent, // Removes the grayish background
                    unfocusedContainerColor = Color.Transparent, // Removes the grayish background when not focused
                    focusedIndicatorColor = Color.Transparent, // Removes underline when focused
                    unfocusedIndicatorColor = Color.Transparent, // Removes underline when unfocused
                    cursorColor = Color.Red // Customize cursor color
                )
            )

            if (searchText.trim().isNotEmpty()) {
                IconButton(onClick = {
                    onSearchTextChange("")
                    focusManager.clearFocus()
                }) {
                    Icon(
                        painter = painterResource(id = android.R.drawable.ic_menu_close_clear_cancel),
                        contentDescription = null,
                        tint = Color.Red
                    )
                }
            }
        }

        // Border to indicate focus
        Box(
            modifier = Modifier
                .matchParentSize()
                .clip(RoundedCornerShape(8.dp))
                .border(
                    width = 2.dp,
                    color = if (isTextFieldFocused) Color.Red else Color.Gray, // Custom border colors
                    shape = RoundedCornerShape(8.dp)
                )
        )
    }
}
