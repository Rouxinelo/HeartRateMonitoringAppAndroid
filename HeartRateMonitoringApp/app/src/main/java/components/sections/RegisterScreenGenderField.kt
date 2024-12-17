package components.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
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
    ){
        CustomRadioButton(
            isSelected = isMaleSelected,
            onSelectionChange = {
                if (!isMaleSelected) onGenderChange(Gender.Male)
            },
            text = "M"
        )

        Spacer(modifier = Modifier.width(width = 16.dp))

        CustomRadioButton(
            isSelected = isFemaleSelected,
            onSelectionChange = {
                if (!isFemaleSelected) onGenderChange(Gender.Female)
            },
            text = "F"
        )
    }
}