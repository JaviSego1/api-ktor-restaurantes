package domain.usecase

import com.example.domain.models.restaurantes.Restaurante
import com.example.domain.repository.RestauranteInterface

class GetAllRestaurantesUseCase (val repository : RestauranteInterface) {

    suspend operator fun invoke(): List<Restaurante> {
        return repository.getAllRestaurantes()
    }

}