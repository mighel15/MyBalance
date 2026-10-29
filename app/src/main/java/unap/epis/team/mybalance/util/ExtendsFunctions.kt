package unap.epis.team.mybalance.util

import org.json.JSONObject
import retrofit2.Response

fun Response<*>.getErrorMessage(): String {
    val errorJson = errorBody()?.string()
    return try {
        JSONObject(errorJson ?: "").getString("error")
    } catch (_: Exception) {
        "Error desconocido (${code()})"
    }
}
