package poran.cse.walcartassignment.data.db

import androidx.paging.PagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import poran.cse.walcartassignment.data.repository.FakePageSource
import poran.cse.walcartassignment.domain.model.Category

class FakeCategoryDao(val categories: MutableList<Category> = mutableListOf()): CategoryDao {

    private val pagingSource = FakePageSource()

    override suspend fun addCategories(categories: List<Category>) {
        this.categories.addAll(categories)
        println("categories")
        updatePagingSource()
    }

    override fun getAllCategories(): PagingSource<Int, Category> {
        return pagingSource
    }

    override fun getCategory(uuid: String): Flow<Category> {
        val index = categories.indexOfFirst { it.uid == uuid }
        return if (index != -1) {
            flowOf(categories[index])
        } else {
            emptyFlow()
        }
    }

    override suspend fun deleteAllCategories(): Int {
        val size = categories.size
        categories.clear()
        updatePagingSource()
        return size
    }

    private fun updatePagingSource() {
        println("updatePagingSource")

        pagingSource.categoris = categories
    }
}