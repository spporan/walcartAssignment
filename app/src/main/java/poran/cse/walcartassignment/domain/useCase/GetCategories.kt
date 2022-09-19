package poran.cse.walcartassignment.domain.useCase

import poran.cse.walcartassignment.domain.repository.CategoryRepository

class GetCategories(
    private val categoryRepository: CategoryRepository
) {
    operator fun invoke() = categoryRepository.getCategories()
}