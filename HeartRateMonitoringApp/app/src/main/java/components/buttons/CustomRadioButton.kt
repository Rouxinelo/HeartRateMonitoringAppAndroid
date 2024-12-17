package components.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CustomRadioButton(
    isSelected: Boolean,
    onSelectionChange: (Boolean) -> Unit,
    text: String
) {
    Row(
        modifier = Modifier
            .clickable (
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = { onSelectionChange(!isSelected) },
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)

    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold)
        )

        Box(
            modifier = Modifier
                .size(35.dp)
                .padding(4.dp)
                .border(2.dp, Color.Red, RoundedCornerShape(4.dp))
                .background(if (isSelected) Color.Red else Color.White, RoundedCornerShape(4.dp))
        ) {
            if (isSelected) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Red, RoundedCornerShape(4.dp))
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSelectedCustomRadioButton() {
    var selectedOption by remember { mutableStateOf(true) }

    CustomRadioButton(
        isSelected = selectedOption,
        onSelectionChange = { newSelection -> selectedOption = newSelection },
        text = "M"
    )
}