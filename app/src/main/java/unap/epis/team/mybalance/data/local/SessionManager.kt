package unap.epis.team.mybalance.data.local

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


val Context.dataStore by preferencesDataStore(name = "my_balance")

class SessionManager(private val context: Context) {

    companion object {

        private val TOKEN = stringPreferencesKey("token")
        private val USER_ID = intPreferencesKey("user_id")
        private val USER_NAME = stringPreferencesKey("user_name")
        private val IS_LOGIN = booleanPreferencesKey("is_login")
    }

    suspend fun saveSession(
        token: String,
        userId: Int,
        userName: String
    ) {

        context.dataStore.edit {

            it[TOKEN] = token
            it[USER_ID] = userId
            it[USER_NAME] = userName
            it[IS_LOGIN] = true
        }

    }

    val token: Flow<String?> =
        context.dataStore.data.map {
            it[TOKEN]
        }

    val userId: Flow<Int> =
        context.dataStore.data.map {
            it[USER_ID] ?: 0
        }

    val userName: Flow<String> =
        context.dataStore.data.map {
            it[USER_NAME] ?: ""
        }

    val isLogged: Flow<Boolean> =
        context.dataStore.data.map {
            it[IS_LOGIN] ?: false
        }

    suspend fun logout() {
        context.dataStore.edit {
            it.clear()
        }
    }
}