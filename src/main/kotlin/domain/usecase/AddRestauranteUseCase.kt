package domain.usecase

import com.example.domain.models.restaurantes.Restaurante
import com.example.domain.repository.RestauranteInterface

class AddRestauranteUseCase (val repository : RestauranteInterface) {

    suspend operator fun invoke(titulo: String, descripcion: String): Restaurante? {
        val restaurante = Restaurante(
            id = 0,
            titulo = titulo,
            descripcion = descripcion
        )

        return repository.addRestaurante(restaurante)
    }
}