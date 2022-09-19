package poran.cse.walcartassignment.data.db

import androidx.annotation.Keep
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import poran.cse.walcartassignment.domain.model.CategoriesRemoteKey

@Keep
@Dao
interface CategoryRemoteKeyDao {
    @Query("SELECT * FROM categories_remote_key WHERE id = :uid")
    suspend fun getCategoryRemoteKeys(uid: String?): CategoriesRemoteKey?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllCategoryRemoteKeys(categoryRemoteKeys : List<CategoriesRemoteKey?>)

    @Query("DELETE FROM categories_remote_key")
    suspend fun deleteAllCategoryRemoteKeys()
}