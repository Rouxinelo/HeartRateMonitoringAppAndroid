package components.textFields

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomNumericField(
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    placeholder: String,
    maxDigits: Int
) {
    var isTextFieldFocused by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(8.dp))
                .padding(8.dp)
        ) {
            TextField(
                value = searchText,
                onValueChange = { newText ->
                    if (newText.all { it.isDigit() } && newText.length <= maxDigits) {
                        onSearchTextChange(newText)
                    }
                },
                placeholder = {
                    Text(
                        text = placeholder,
                        style = TextStyle(fontSize = 16.sp, color = Color.Gray)
                    )
                },
                textStyle = TextStyle(fontSize = 16.sp, color = Color.Black),
                modifier = Modifier
                    .weight(1f)
                    .background(Color.Transparent)
                    .onFocusChanged { isTextFieldFocused = it.isFocused },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                keyboardActions = KeyboardActions(onDone = {
                    focusManager.clearFocus()
                }),
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.Red
                )
            )

            if (searchText.trim().isNotEmpty()) {
                IconButton(onClick = {
                    onSearchTextChange("")
                    focusManager.clearFocus()
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_menu_close_clear_cancel),
                        contentDescription = null,
                        tint = Color.Red
                    )
                }
            }
        }

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