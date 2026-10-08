package unap.epis.team.mybalance.data.model.request

import com.google.gson.annotations.SerializedName

data class CreateUserRequest(
    @SerializedName("nombre")           val name: String,
    @SerializedName("email")            val email: String,
    @SerializedName("password")         val password: String
)