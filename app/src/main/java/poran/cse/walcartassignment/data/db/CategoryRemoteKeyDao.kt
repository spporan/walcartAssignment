package poran.cse.walcartassignment.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import poran.cse.walcartassignment.domain.model.CategoriesRemoteKey

@Dao
interface CategoryRemoteKeyDao {
    @Query("SELECT * FROM categories_remote_key WHERE id = :uid")
    suspend fun getCategoryRemoteKeys(uid: Int): CategoriesRemoteKey?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllCategoryRemoteKeys(categoryRemoteKeys : List<CategoriesRemoteKey>)

    @Query("DELETE FROM categories_remote_key")
    suspend fun deleteAllCategoryRemoteKeys()
}