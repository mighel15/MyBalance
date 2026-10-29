package unap.epis.team.mybalance.data.repository

import unap.epis.team.mybalance.data.api.UserApiService
import unap.epis.team.mybalance.data.model.request.LoginRequest
import unap.epis.team.mybalance.data.model.request.CreateUserRequest
import unap.epis.team.mybalance.data.model.response.LoginResponse
import unap.epis.team.mybalance.data.model.response.RegisterUserResponse
import unap.epis.team.mybalance.util.getErrorMessage
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userApiService: UserApiService) : UserRepository {

    override suspend fun login(email: String,password: String): Result<LoginResponse> {

        return try {

            val request = LoginRequest(
                email = email,
                password = password
            )

            val response = userApiService.login(request)

            if (response.isSuccessful) {

                val body = response.body()

                if (body != null) {
                    Result.success(body)
                } else {
                    Result.failure(Exception("La respuesta está vacía"))
                }

            } else {
                Result.failure(Exception(response.getErrorMessage()))
            }

        } catch (e: Exception) {

            Result.failure(
                Exception(e.message ?: "Error de conexión")
            )
        }
    }

    override suspend fun registroUsuario(nombre: String, correo: String, contrasenia: String): Result<RegisterUserResponse> {

        return try {

            val request = CreateUserRequest(nombre, correo, contrasenia)

            val response = userApiService.registrarUsuario(request)

            if(response.isSuccessful) 
            {
                val body = response.body()

                if(body != null) {
                    Result.success(body)
                } else {
                    Result.failure(Exception("La respuesta está vacía"))
                }
            } else {
                Result.failure(Exception(response.getErrorMessage()))
            }

        } catch (e: Exception) {
            Result.failure(
                Exception(e.message ?: "Error de conexión")
            )
        }


    }
}