package components.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import components.buttons.CustomRadioButton

enum class Gender {
    Male, Female
}

@Composable
fun RegisterScreenGenderField(
    isMaleSelected: Boolean,
    isFemaleSelected: Boolean,
    onGenderChange: (Gender) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        CustomRadioButton(
            isSelected = isMaleSelected,
            onSelectionChange = {
                if (!isMaleSelected) onGenderChange(Gender.Male)
            },
            text = "M"
        )

        CustomRadioButton(
            isSelected = isFemaleSelected,
            onSelectionChange = {
                if (!isFemaleSelected) onGenderChange(Gender.Female)
            },
            text = "F"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenGenderFieldPreview() {
    var isMaleSelected by remember { mutableStateOf(false) }
    var isFemaleSelected by remember { mutableStateOf(false) }

    RegisterScreenGenderField(
        isMaleSelected = isMaleSelected,
        isFemaleSelected = isFemaleSelected,
        onGenderChange = { gender ->
            when (gender) {
                Gender.Male -> {
                    isMaleSelected = true
                    isFemaleSelected = false
                }
                Gender.Female -> {
                    isFemaleSelected = true
                    isMaleSelected = false
                }
            }
        }
    )
}