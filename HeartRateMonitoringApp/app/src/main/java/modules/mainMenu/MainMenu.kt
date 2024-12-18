package modules.mainMenu

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.heartratemonitoringapp.R
import com.yourapp.components.alerts.CustomToast
import components.sections.MainMenuSection

@Composable
fun MainScreen() {
    var isToastVisible = remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Ola, Exemplo",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(10.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.heart_rate),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
            )

            Text(
                text = "O que fazer?",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                MainMenuSection(
                    sectionColor = Color.Red,
                    sectionIcon = painterResource(id = R.drawable.book_fill),
                    sectionTitle = "Sessões",
                    sectionDescription = "Veja as sessões disponíveis",
                    isUnavailable = false,
                    sectionAction = { isToastVisible.value = true }
                )

                MainMenuSection(
                    sectionColor = Color.Green,
                    sectionIcon = painterResource(id = R.drawable.calendar),
                    sectionTitle = "Calendário",
                    sectionDescription = "Junte-se a sessões disponíveis",
                    isUnavailable = false,
                    sectionAction = { isToastVisible.value = true }
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                MainMenuSection(
                    sectionColor = Color(0xFFFFCC00),
                    sectionIcon = painterResource(id = R.drawable.person_fill),
                    sectionTitle = "Detalhes",
                    sectionDescription = "Veja os seus detalhes de utilizador",
                    isUnavailable = false,
                    sectionAction = { isToastVisible.value = true }
                )

                MainMenuSection(
                    sectionColor = Color.Blue,
                    sectionIcon = painterResource(id = R.drawable.power_off),
                    sectionTitle = "Logout",
                    sectionDescription = "Sair para o menu",
                    isUnavailable = false,
                    sectionAction = { isToastVisible.value = true }
                )
            }
        }

        if (isToastVisible.value) {
            CustomToast(
                isShowing = isToastVisible,
                iconName = android.R.drawable.ic_dialog_info,
                message = "Coming Soon!"
            )
        }
    }
}