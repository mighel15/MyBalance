package unap.epis.team.mybalance.data.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import unap.epis.team.mybalance.data.model.request.LoginRequest
import unap.epis.team.mybalance.data.model.request.RegisterUserRequest
import unap.epis.team.mybalance.data.model.response.LoginResponse
import unap.epis.team.mybalance.data.model.response.RegisterUserResponse
import unap.epis.team.mybalance.data.model.response.UserResponse

interface UserApiService {

    //retrofit

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @GET("auth/me")
    suspend fun myInfo(): Response<UserResponse>

    @POST("auth/registro")
    suspend fun registrarUsuario(@Body request: RegisterUserRequest): Response<RegisterUserResponse>

}