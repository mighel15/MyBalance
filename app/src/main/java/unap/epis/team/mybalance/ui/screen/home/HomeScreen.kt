package unap.epis.team.mybalance.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import unap.epis.team.mybalance.ui.screen.login.LoginViewModel

@Composable
fun HomeScreen(
    navController: NavController? = null,
    viewModel: HomeViewModel = viewModel()
) {

    val userName = viewModel.userName.collectAsState()

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Yellow), contentAlignment = Alignment.Center)
    {
        Column() {
            Text("Bienvenido: ${userName.value}")
            Spacer(modifier = Modifier.height(32.dp))
            Button(onClick = {
                viewModel.cerrarSession()
                navController?.navigate("login")
            }) {
                Text("Cerrar sesion")
            }
        }

    }
}