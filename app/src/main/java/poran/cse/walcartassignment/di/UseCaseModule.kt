package poran.cse.walcartassignment.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import poran.cse.walcartassignment.domain.repository.CategoryRepository
import poran.cse.walcartassignment.domain.useCase.CategoriesUseCases
import poran.cse.walcartassignment.domain.useCase.GetCategories
import poran.cse.walcartassignment.domain.useCase.GetCategoriesFromLocal

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideCategoriesUseCases(categoryRepository: CategoryRepository) = CategoriesUseCases(
        getCategories = GetCategories(categoryRepository),
        getCategoriesFromLocal = GetCategoriesFromLocal(categoryRepository)
    )
}