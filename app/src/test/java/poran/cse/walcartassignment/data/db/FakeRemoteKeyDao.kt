package poran.cse.walcartassignment.data.db

import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import poran.cse.walcartassignment.domain.model.CategoriesRemoteKey
import poran.cse.walcartassignment.domain.model.Category

class FakeRemoteKeyDao(
    val categoriesRemoteKey: MutableList<CategoriesRemoteKey> = mutableListOf()
): CategoryRemoteKeyDao {



    override suspend fun getCategoryRemoteKeys(uid: String?): CategoriesRemoteKey? {
        val index = categoriesRemoteKey.indexOfFirst { it.id == uid }
        return if (index != -1) {
            categoriesRemoteKey[index]
        } else {
            null
        }
    }

    override suspend fun addAllCategoryRemoteKeys(categoryRemoteKeys: List<CategoriesRemoteKey?>) {
        categoriesRemoteKey.addAll(categoriesRemoteKey)
    }

    override suspend fun deleteAllCategoryRemoteKeys(): Int {
        val size = categoriesRemoteKey.size
        categoriesRemoteKey.clear()
        return size
    }
}