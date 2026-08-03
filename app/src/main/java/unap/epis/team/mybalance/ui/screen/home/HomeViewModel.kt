package unap.epis.team.mybalance.ui.screen.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import unap.epis.team.mybalance.data.local.SessionManager

class HomeViewModel(application: Application): AndroidViewModel(application)
{
    private val sessionManager = SessionManager(application)

    private val _userName = MutableStateFlow("")
    val userName: StateFlow<String> = _userName.asStateFlow()

    init {
        viewModelScope.launch {
            _userName.value = sessionManager.userName.first()
        }
    }

    fun cerrarSession()
    {
        viewModelScope.launch {
            sessionManager.logout()
        }
    }

}