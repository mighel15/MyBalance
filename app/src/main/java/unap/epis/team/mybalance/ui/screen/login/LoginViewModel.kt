package unap.epis.team.mybalance.ui.screen.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import unap.epis.team.mybalance.data.local.SessionManager
import unap.epis.team.mybalance.data.repository.UserRepository

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: UserRepository = UserRepository()

    private val sessionManager = SessionManager(application)

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
                    //guardar los datos de la session
                    sessionManager.saveSession(
                        result.getOrNull()?.token!!,
                        result.getOrNull()?.user?.id!!,
                        result.getOrNull()?.user?.nombre!!)
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

    fun verificarSession()
    {
        viewModelScope.launch {
            val token = sessionManager.token.first()
            if(token != null)
            {
                _uiState.value = LoginUiState.Success(token)
            }
        }
    }

    fun registrarUsuario() {
        viewModelScope.launch {
            try {
                _uiState.value = LoginUiState.Loading
                val response = repository.registroUsuario(nombre.value, email.value, password.value)
                if(response.isSuccess)
                {
                    _uiState.value = LoginUiState.SuccessRegister(email.value)
                }
                else
                {
                    _uiState.value = LoginUiState.Error(response.exceptionOrNull()?.message!!)
                }


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