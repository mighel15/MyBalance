package unap.epis.team.mybalance.ui.screen.login

sealed class LoginUiState {
    object Initial : LoginUiState()
    object Loading : LoginUiState()
    data class Success(val token: String) : LoginUiState()
    data class Error(val mensaje: String) : LoginUiState()
}
