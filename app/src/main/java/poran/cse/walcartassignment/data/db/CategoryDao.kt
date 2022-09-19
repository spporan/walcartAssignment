package poran.cse.walcartassignment.data.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import poran.cse.walcartassignment.domain.model.Category

@Dao
interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCategories(categories: List<Category>)

    @Query("SELECT * FROM categories")
    fun getAllCategories(): PagingSource<Int, Category>

    @Query("SELECT * FROM categories WHERE id = :uuid")
    fun getCategory(uuid: String): Flow<Category>

    @Query("DELETE FROM categories")
    suspend fun deleteAllCategories()

}