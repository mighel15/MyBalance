package unap.epis.team.mybalance.data.model.request

import com.google.gson.annotations.SerializedName

data class RegisterUserRequest(
    @SerializedName("nombre")           val nombre: String,
    @SerializedName("email")            val correo: String,
    @SerializedName("password")         val contrasenia: String
)