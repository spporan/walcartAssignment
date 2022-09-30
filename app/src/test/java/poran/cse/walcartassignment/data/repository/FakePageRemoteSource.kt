package poran.cse.walcartassignment.data.repository

import androidx.compose.ui.semantics.SemanticsProperties.Error
import androidx.paging.*
import poran.cse.walcartassignment.domain.model.Category

/*@OptIn(ExperimentalPagingApi::class)
class FakePageRemoteSource() : PagingSource<Int, Category>() {
    var triggerError = false
    var categoris: List<Category> = emptyList()
        set(value) {
            println("set")
            field = value
        }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Category>
    ): PagingSource {
        println("load")
        if (triggerError) {
            return MediatorResult.Error(Exception("A test error triggered"))
        }
        println("not error")
        return MediatorResult.Success(true)
    }

}*/

class FakePageSource() : PagingSource<Int, Category>() {
    var triggerError = false
    var categoris: List<Category> = emptyList()
        set(value) {
            println("set")
            field = value
            invalidate()
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Category> {
        println("load")
        if (triggerError) {
            return LoadResult.Error(Exception("A test error triggered"))
        }
        println("not error")

        return LoadResult.Page(
            data = categoris,
            prevKey = null,
            nextKey = null
        )
    }

    override fun getRefreshKey(state: PagingState<Int, Category>): Int? {
        println("refresh")

        return state.anchorPosition ?: 1
    }
}