package domain.usecase

import com.example.data.security.PasswordHash
import com.example.domain.models.usuarios.Usuario
import com.example.domain.repository.UsuarioInterface

class LoginUseCase (private val repository: UsuarioInterface) {

    suspend operator fun invoke(username: String, password: String): Usuario? {
        val usuario = repository.getUsuarioByUsername(username)
        return if (usuario != null && PasswordHash.verify(password, usuario.password)) {
            usuario
        } else {
            null
        }
    }

}