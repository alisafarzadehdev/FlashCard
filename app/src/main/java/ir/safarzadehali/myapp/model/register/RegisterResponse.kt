package ir.safarzadehali.myapp.model.register

data class RegisterResponse(
    val status: Boolean,
    val message: String,
    val user: UserResponse?
)