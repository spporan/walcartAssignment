package poran.cse.walcartassignment.domain.model

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity(tableName = "categories_remote_key")
data class CategoriesRemoteKey(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val prevPage: Int?,
    val nextPage: Int?,
    val lastUpdated: Long?,
)