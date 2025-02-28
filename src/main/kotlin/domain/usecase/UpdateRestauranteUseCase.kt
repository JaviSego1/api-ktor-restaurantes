package domain.usecase

import com.example.domain.models.restaurantes.Restaurante
import com.example.domain.repository.RestauranteInterface

class UpdateRestauranteUseCase (val repository : RestauranteInterface) {
    suspend operator fun invoke(id: Int, restaurante: Restaurante): Boolean {
        return repository.updateRestaurante(id, restaurante)
    }
}