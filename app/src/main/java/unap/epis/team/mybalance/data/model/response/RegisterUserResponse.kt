package unap.epis.team.mybalance.data.model.response

import com.google.gson.annotations.SerializedName

data class RegisterUserResponse (
    val token: String,
    @SerializedName("usuario")
    val user: UserResponse
)
