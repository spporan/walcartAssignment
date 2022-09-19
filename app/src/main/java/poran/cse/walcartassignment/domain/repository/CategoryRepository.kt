package poran.cse.walcartassignment.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import poran.cse.walcartassignment.domain.model.Category

interface CategoryRepository {
    fun getCategories(): Flow<PagingData<Category>>
    fun getCategoryFromDB(uid: String): Flow<Category>
}