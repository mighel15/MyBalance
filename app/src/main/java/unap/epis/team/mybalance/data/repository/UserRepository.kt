package unap.epis.team.mybalance.data.repository

import unap.epis.team.mybalance.data.model.response.LoginResponse
import unap.epis.team.mybalance.data.model.response.RegisterUserResponse

interface UserRepository {
    suspend fun login(email: String,password: String): Result<LoginResponse>
    suspend fun registroUsuario(nombre: String, correo: String, contrasenia: String): Result<RegisterUserResponse>
}