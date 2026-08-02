package unap.epis.team.mybalance.data.model.response

data class RegisterUserResponse (
    val token: String,
    val user: UserResponse
)
