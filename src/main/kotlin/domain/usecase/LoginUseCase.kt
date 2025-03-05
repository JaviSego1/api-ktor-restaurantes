package domain.usecase

import domain.security.JwtConfig
import com.example.data.security.PasswordHash
import com.example.domain.models.usuarios.Usuario
import com.example.domain.repository.UsuarioInterface

class LoginUseCase (private val repository: UsuarioInterface) {

    suspend operator fun invoke(username: String, password: String): String? {
        val usuario = repository.getUsuarioByUsername(username)
        return if (usuario != null && PasswordHash.verify(password, usuario.password)) {
            // Generar el token JWT
            val token = JwtConfig.generateToken(usuario.name)

            // Actualizar el token en la base de datos
            repository.updateUserToken(usuario.id, token)

            // Devolver el token generado
            token
        } else {
            null
        }
    }
}
