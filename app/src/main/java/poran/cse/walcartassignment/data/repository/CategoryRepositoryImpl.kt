package poran.cse.walcartassignment.data.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import poran.cse.walcartassignment.data.repository.dataSource.LocalCategoryDataSource
import poran.cse.walcartassignment.data.repository.dataSource.RemoteCategoryDataSource
import poran.cse.walcartassignment.domain.model.Category
import poran.cse.walcartassignment.domain.repository.CategoryRepository

class CategoryRepositoryImpl(
    private val remoteDataSource: RemoteCategoryDataSource,
    private val localDataSource: LocalCategoryDataSource,
): CategoryRepository {
    override fun getCategories(): Flow<PagingData<Category>> {
        return remoteDataSource.getCategories()
    }

    override fun getCategoryFromDB(uid: String): Flow<Category> {
        return localDataSource.getCategoryFromLocal(uid)
    }

}