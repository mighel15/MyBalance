package unap.epis.team.mybalance.ui.navigation

sealed class Screens(val route: String)
{
    object Home: Screens("home")
    object Main: Screens("main")
    object Login: Screens("login")

}
