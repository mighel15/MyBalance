package unap.epis.team.mybalance.data.model.response

import com.google.gson.annotations.SerializedName

data class LoginResponse (
    val token: String,
    @SerializedName("usuario")
    val user: UserResponse
)

data class UserResponse(
    val id: Int,
    val nombre: String,
    val email: String
)
