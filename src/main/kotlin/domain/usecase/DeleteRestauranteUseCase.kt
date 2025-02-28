package domain.usecase

import com.example.domain.repository.RestauranteInterface

class DeleteRestauranteUseCase (val repository : RestauranteInterface) {

    suspend operator fun invoke(id: Int): Boolean {
        return repository.deleteRestaurante(id)
    }

}