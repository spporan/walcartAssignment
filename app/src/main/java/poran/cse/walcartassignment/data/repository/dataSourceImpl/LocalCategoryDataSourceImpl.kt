package poran.cse.walcartassignment.data.repository.dataSourceImpl

import kotlinx.coroutines.flow.Flow
import poran.cse.walcartassignment.data.db.CategoryDao
import poran.cse.walcartassignment.data.db.CategoryDatabase
import poran.cse.walcartassignment.data.repository.dataSource.LocalCategoryDataSource
import poran.cse.walcartassignment.domain.model.Category

class LocalCategoryDataSourceImpl(
    private val categoryDao: CategoryDao
): LocalCategoryDataSource {

    override fun getCategoryFromLocal(uid: String): Flow<Category> {
        return categoryDao.getCategory(uid)
    }
}