package unap.epis.team.mybalance.ui.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import unap.epis.team.mybalance.data.repository.UserRepository
import kotlin.time.Duration.Companion.milliseconds

class LoginViewModel : ViewModel() {

    private val repository: UserRepository = UserRepository()

    private val _uiState = MutableStateFlow<LoginUiState> (LoginUiState.Initial)
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    private val _nombre = MutableStateFlow<String> ("")
    val nombre: StateFlow<String> = _nombre.asStateFlow()

    private val _email = MutableStateFlow<String> ("")
    val email: StateFlow<String> = _email.asStateFlow()

    private val _password = MutableStateFlow<String> ("")
    val password: StateFlow<String> = _password.asStateFlow()

    fun doLogin() {
        viewModelScope.launch {
            try {
                _uiState.value = LoginUiState.Loading
                // do the login
                val result = repository.login(email.value, password.value)

                if(result.isSuccess)
                {
                    _uiState.value = LoginUiState.Success("Welcome")
                }
                else
                {
                    _uiState.value = LoginUiState.Error(result.exceptionOrNull()?.message!!)
                }

            } catch (e: Exception) {
                _uiState.value = LoginUiState.Error(e.message ?: "Error desconocido")
            }
        }
    }

    fun registrar() {
        viewModelScope.launch {
            try {
                _uiState.value = LoginUiState.Loading
                // do the register
                delay(5000.milliseconds)
                _uiState.value = LoginUiState.Success("Welcome")

            } catch (e: Exception) {
                _uiState.value = LoginUiState.Error(e.message ?: "Error desconocido")
            }
        }
    }

    fun onEmailChange(newEmail: String) {
        _email.value = newEmail
    }

    fun onPasswordChange(newPassword: String) {
        _password.value = newPassword
    }

    fun onNameChange(newName: String) {
        _nombre.value = newName
    }

}