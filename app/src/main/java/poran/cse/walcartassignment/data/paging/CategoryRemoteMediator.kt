package poran.cse.walcartassignment.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import org.json.JSONObject
import poran.cse.walcartassignment.data.api.CategoriesApi
import poran.cse.walcartassignment.data.db.CategoryDatabase
import poran.cse.walcartassignment.domain.model.CategoriesRemoteKey
import poran.cse.walcartassignment.domain.model.Category

@OptIn(ExperimentalPagingApi::class)
class CategoryRemoteMediator(
    private val categoriesApi: CategoriesApi,
    private val categoryDatabase: CategoryDatabase
): RemoteMediator<Int, Category>() {

    private val categoryDao = categoryDatabase.categoryDao()
    private val remoteKeyDao = categoryDatabase.remoteKeyDao()

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
                categoriesApi.getCategories(body = getQueryBody(page))
            } catch (e: Exception) {
                null
            }
            var endOfPaginationReached = false
            if (response?.isSuccessful == true) {
                val responseData = response.body()
                endOfPaginationReached = responseData?.data?.getCategories?.result?.categories?.size == 100
                responseData?.data?.getCategories?.result?.categories?.let { categories ->
                    categoryDatabase.withTransaction {
                        if (loadType == LoadType.REFRESH) {
                            categoryDao.deleteAllCategories()
                            remoteKeyDao.deleteAllCategoryRemoteKeys()
                        }

                        val prevPage = if (page == 10) null else page - 1
                        val nextPage = if (endOfPaginationReached) null else page + 1


                        val keys = categories.map { category ->
                            category.uid?.let {
                                CategoriesRemoteKey(
                                    id = it,
                                    prevPage = prevPage,
                                    nextPage = nextPage,
                                    lastUpdated = System.currentTimeMillis()
                                )
                            }
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

    private fun getQueryBody(page: Int): String {
        val paramObject = JSONObject()
        paramObject.put("query", "query {\n" +
                "  getCategories(pagination: { limit: $page, skip: 0 }) {\n" +
                "    result {\n" +
                "      categories {\n" +
                "        uid\n" +
                "        enName\n" +
                "        bnName\n" +
                "        parent {\n" +
                "          uid\n" +
                "          enName\n" +
                "          bnName\n" +
                "        }\n" +
                "        parents {\n" +
                "          uid\n" +
                "          enName\n" +
                "          bnName\n" +
                "        }\n" +
                "        image {\n" +
                "          name\n" +
                "          url\n" +
                "          signedUrl\n" +
                "        }\n" +
                "        attributeSetUid\n" +
                "        isActive\n" +
                "        inActiveNote\n" +
                "        createdAt\n" +
                "        updatedAt\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "\n" +
                "}")
        return paramObject.toString()

    }

}