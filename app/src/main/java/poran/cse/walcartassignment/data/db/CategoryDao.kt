package poran.cse.walcartassignment.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import poran.cse.walcartassignment.domain.model.Category

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insetQuestion(category: Category)

    @Query("SELECT * FROM categories where id = :id")
    fun getQuestion(id: String): Category
}