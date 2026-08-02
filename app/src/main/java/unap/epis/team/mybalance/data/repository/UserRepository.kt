package unap.epis.team.mybalance.data.repository

import org.json.JSONObject
import unap.epis.team.mybalance.data.api.RetrofitClient
import unap.epis.team.mybalance.data.api.UserApiService
import unap.epis.team.mybalance.data.model.request.LoginRequest
import unap.epis.team.mybalance.data.model.request.RegisterUserRequest
import unap.epis.team.mybalance.data.model.response.LoginResponse
import unap.epis.team.mybalance.data.model.response.RegisterUserResponse

class UserRepository() {

    private val apiService: UserApiService = RetrofitClient.api

    suspend fun login(email: String,password: String
    ): Result<LoginResponse> {

        return try {

            val request = LoginRequest(
                email = email,
                password = password
            )

            val response = apiService.login(request)

            if (response.isSuccessful) {

                val body = response.body()

                if (body != null) {
                    Result.success(body)
                } else {
                    Result.failure(Exception("La respuesta está vacía"))
                }

            } else {

                val errorJson = response.errorBody()?.string()
                val errorMessage = try {
                    JSONObject(errorJson ?: "").getString("error")
                } catch (e: Exception) {
                    "Error desconocido (${response.code()})"
                }
                Result.failure(Exception(errorMessage))

            }

        } catch (e: Exception) {

            Result.failure(
                Exception(e.message ?: "Error de conexión")
            )
        }
    }

    suspend fun registroUsuario(nombre: String, correo: String, contrasenia: String): Result<RegisterUserResponse> {

        return try {

            val request = RegisterUserRequest(nombre, correo, contrasenia)

            val response = apiService.registrarUsuario(request)

            if(response.isSuccessful) // Http codes 2XX 200, 201, 202, 203....
            {
                val body = response.body()

                if(body != null) {
                    Result.success(body)
                } else {
                    Result.failure(Exception("La respuesta está vacía"))
                }
            } else {
                Result.failure(Exception("Error al registrar usuario"))
            }

        } catch (e: Exception) {
            Result.failure(
                Exception(e.message ?: "Error de conexión")
            )
        }


    }
}