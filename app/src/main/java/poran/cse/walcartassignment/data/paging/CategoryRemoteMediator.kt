package poran.cse.walcartassignment.data.paging

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import poran.cse.walcartassignment.Constants.getQueryBody
import poran.cse.walcartassignment.data.api.CategoriesApi
import poran.cse.walcartassignment.data.db.CategoryDao
import poran.cse.walcartassignment.data.db.CategoryDatabase
import poran.cse.walcartassignment.data.db.CategoryRemoteKeyDao
import poran.cse.walcartassignment.di.DatabaseModule
import poran.cse.walcartassignment.domain.model.CategoriesRemoteKey
import poran.cse.walcartassignment.domain.model.Category
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class CategoryRemoteMediator(
    private val categoriesApi: CategoriesApi,
    private val categoryDao: CategoryDao,
    private val remoteKeyDao: CategoryRemoteKeyDao,
): RemoteMediator<Int, Category>() {

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Category>
    ): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }

            val response = try {
                categoriesApi.getCategories(body = getQueryBody(page * 10))
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }

            Log.e("API", "body ${response?.body()}")

            Log.e("API", "response ${response?.isSuccessful} size ${response?.body()?.data?.getCategories?.result?.categories?.size} code ${response?.code()}")
            var endOfPaginationReached = false
            if (response?.isSuccessful == true) {
                val responseData = response.body()

                endOfPaginationReached = responseData?.data?.getCategories?.result?.categories?.isEmpty() == true

                responseData?.data?.getCategories?.result?.categories?.let { categories ->
                    withContext(Dispatchers.IO) {
                        if (loadType == LoadType.REFRESH) {
                            categoryDao.deleteAllCategories()
                            remoteKeyDao.deleteAllCategoryRemoteKeys()
                        }

                        val prevPage = if (page <= 1) null else page - 1
                        val nextPage = if (endOfPaginationReached || categories.size < page * 10) null else page + 1


                        val keys = categories.map { category ->
                            CategoriesRemoteKey(
                                id = category.uid,
                                prevPage = prevPage,
                                nextPage = nextPage,
                                lastUpdated = System.currentTimeMillis()
                            )
                        }

                        remoteKeyDao.addAllCategoryRemoteKeys(keys)
                        categoryDao.addCategories(categories = categories)
                    }
                }

            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getKeyClosestToCurrentPosition(
        state: PagingState<Int, Category>,
    ): CategoriesRemoteKey? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.uid?.let { id ->
                remoteKeyDao.getCategoryRemoteKeys(uid = id)
            }
        }
    }

    private suspend fun getKeyForFirstItem(
        state: PagingState<Int, Category>,
    ): CategoriesRemoteKey? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { category ->
                remoteKeyDao.getCategoryRemoteKeys(uid = category.uid)
            }
    }

    private suspend fun getKeyForLastItem(
        state: PagingState<Int, Category>,
    ): CategoriesRemoteKey? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { category ->
                remoteKeyDao.getCategoryRemoteKeys(uid = category.uid)
            }
    }


}