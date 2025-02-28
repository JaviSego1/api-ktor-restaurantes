package domain.usecase

import com.example.data.security.PasswordHash
import com.example.domain.models.usuarios.Usuario
import com.example.domain.repository.UsuarioInterface

class RegistroUseCase (private val repository: UsuarioInterface) {
    suspend operator fun invoke(usuario: Usuario): Usuario {
        val hashedPassword = PasswordHash.hash(usuario.password)
        val usuarioConHash = usuario.copy(password = hashedPassword)
        return repository.createUsuario(usuarioConHash)
    }
}