package unap.epis.team.mybalance.data.model.response

data class LoginResponse (
    val token: String,
    val user: UserResponse
)

data class UserResponse(
    val id: Int,
    val nombre: String,
    val email: String
)
