package poran.cse.walcartassignment.data.repository.dataSource

import kotlinx.coroutines.flow.Flow
import poran.cse.walcartassignment.domain.model.Category

interface LocalCategoryDataSource {
    fun getCategoryFromLocal(uid : String): Flow<Category>
}