package dataModels

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.example.heartratemonitoringapp.R

data class UserDetailType(val type: Type) {
    enum class Type { NAME, EMAIL, GENDER, AGE }

    val image: Painter
        @Composable
        get() = when (type) {
            Type.NAME -> painterResource(R.drawable.person_fill)
            Type.EMAIL -> painterResource(R.drawable.envelope)
            Type.GENDER -> painterResource(R.drawable.person_question)
            Type.AGE -> painterResource(R.drawable.calendar)
        }

    val sectionTitle: String
        get() = when (type) {
            Type.NAME -> "Name"
            Type.EMAIL -> "Email"
            Type.GENDER -> "Gender"
            Type.AGE -> "Age"
        }
}