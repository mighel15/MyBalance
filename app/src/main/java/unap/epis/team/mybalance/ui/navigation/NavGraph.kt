package unap.epis.team.mybalance.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import unap.epis.team.mybalance.ui.screen.home.HomeScreen
import unap.epis.team.mybalance.ui.screen.login.LoginScreen
import unap.epis.team.mybalance.ui.screen.login.LoginViewModel

@Composable
fun NavGraph() {

    var navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screens.Login.route,
    ){
        composable(Screens.Login.route) {
            LoginScreen(navController)
        }
        composable(Screens.Home.route) {
            HomeScreen(navController)
        }
//        composable(Screens.Main.route) {
//            MainScreen()
//        }
    }

}