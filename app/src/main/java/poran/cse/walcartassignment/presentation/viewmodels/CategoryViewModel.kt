package poran.cse.walcartassignment.presentation.viewmodels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import poran.cse.walcartassignment.domain.useCase.CategoriesUseCases
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    categoryUseCases: CategoriesUseCases
): ViewModel() {
    val getCategories = categoryUseCases.getCategories()
}