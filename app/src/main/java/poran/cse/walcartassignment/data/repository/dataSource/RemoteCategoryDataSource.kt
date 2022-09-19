package poran.cse.walcartassignment.data.repository.dataSource

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import poran.cse.walcartassignment.domain.model.Category

interface RemoteCategoryDataSource {
    fun getCategories(): Flow<PagingData<Category>>
}