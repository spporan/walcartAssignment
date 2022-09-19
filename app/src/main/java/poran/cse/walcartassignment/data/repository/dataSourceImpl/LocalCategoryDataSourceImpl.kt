package poran.cse.walcartassignment.data.repository.dataSourceImpl

import kotlinx.coroutines.flow.Flow
import poran.cse.walcartassignment.data.db.CategoryDatabase
import poran.cse.walcartassignment.data.repository.dataSource.LocalCategoryDataSource
import poran.cse.walcartassignment.domain.model.Category

class LocalCategoryDataSourceImpl(
    private val categoryDatabase: CategoryDatabase
): LocalCategoryDataSource {

    override fun getCategoryFromLocal(uid: String): Flow<Category> {
        return categoryDatabase.categoryDao().getCategory(uid)
    }
}