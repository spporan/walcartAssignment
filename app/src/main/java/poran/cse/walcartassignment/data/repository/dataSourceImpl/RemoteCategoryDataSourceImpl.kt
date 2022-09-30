package poran.cse.walcartassignment.data.repository.dataSourceImpl

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import poran.cse.walcartassignment.data.api.CategoriesApi
import poran.cse.walcartassignment.data.db.CategoryDao
import poran.cse.walcartassignment.data.db.CategoryDatabase
import poran.cse.walcartassignment.data.db.CategoryRemoteKeyDao
import poran.cse.walcartassignment.data.paging.CategoryRemoteMediator
import poran.cse.walcartassignment.data.repository.dataSource.RemoteCategoryDataSource
import poran.cse.walcartassignment.domain.model.Category

class RemoteCategoryDataSourceImpl(
    private val categoriesApi: CategoriesApi,
    private val categoryDao: CategoryDao,
    private val remoteKeyDao: CategoryRemoteKeyDao
): RemoteCategoryDataSource {

    @OptIn(ExperimentalPagingApi::class)
    override fun getCategories(): Flow<PagingData<Category>> {
        val pagingSourceFactory = { categoryDao.getAllCategories() }
        return Pager(
            config = PagingConfig(pageSize = 10),
            remoteMediator = CategoryRemoteMediator(
                categoriesApi,
                categoryDao,
                remoteKeyDao
            ),
            pagingSourceFactory = pagingSourceFactory,
        ).flow
    }

}